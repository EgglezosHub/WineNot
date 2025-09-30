# locustfile.py
from locust import HttpUser, task, between
import random, string

def r(n=6): return ''.join(random.choices(string.ascii_lowercase, k=n))

class WineUser(HttpUser):
    wait_time = between(0.5, 2.0)
    titles = []

    def on_start(self):
        # register + login
        self.username = f"user_{r()}"
        self.password = "pass12345"
        self.email = f"{self.username}@example.com"

        self.client.post("/register", json={
            "username": self.username, "fullname": f"Full {r(4)}",
            "email": self.email, "phone": "6900000000", "password": self.password
        }, name="/register")

        self.client.post("/login", json={
            "username": self.username, "password": self.password
        }, name="/login")

        # fetch catalog (1=rose, 2=white, 3=red). Any that returns data is fine.
        self.titles = []
        for t in (1, 2, 3):
            with self.client.post("/load", json={"username": self.username, "number": t}, name=f"/load[type {t}]", catch_response=True) as rres:
                try:
                    data = rres.json()
                    # data is [["Title","Info","Price","Photo_code","Stock"], ...]
                    if isinstance(data, list) and len(data) > 1:
                        for row in data[1:]:
                            if row and len(row) >= 1:
                                self.titles.append(row[0])
                except Exception:
                    rres.failure("non-JSON or unexpected /load payload")
        # if still empty, we’ll just browse; cart/wishlist/order will no-op
        self.titles = list(set(self.titles))

    @task(3)
    def browse_catalog(self):
        t = random.choice([1, 2, 3])
        self.client.post("/load", json={"username": self.username, "number": t}, name="/load")

    @task(2)
    def wishlist_toggle(self):
        if not self.titles: return
        title = random.choice(self.titles)
        self.client.post("/update", json={
            "username": self.username, "Title": title, "CartWish": 2, "AddMin": 1
        }, name="/update[wishlist add]")
        self.client.post("/update", json={
            "username": self.username, "Title": title, "CartWish": 2, "AddMin": 2
        }, name="/update[wishlist remove]")

    @task(2)
    def cart_add_and_manage(self):
        if not self.titles: return
        title = random.choice(self.titles)
        self.client.post("/update", json={
            "username": self.username, "Title": title, "CartWish": 1, "AddMin": 1
        }, name="/update[cart add]")
        self.client.post("/cartmanage", json={
            "username": self.username, "Title": title, "AddMin": 2
        }, name="/cartmanage[++]")
        self.client.post("/cartmanage", json={
            "username": self.username, "Title": title, "AddMin": 1
        }, name="/cartmanage[--]")

    @task(1)
    def place_order(self):
        self.client.post("/order", json={
            "firstname":"John","lastname":"Doe","phone":"6900000000",
            "email": self.email, "address":"Test 1", "pcode":"11111",
            "city":"Athens", "username": self.username
        }, name="/order")
