from flask import Flask, request, jsonify
from flask_cors import CORS
import sqlite3
import os


app = Flask(__name__)
CORS(app)


# Initialize the database
def initialize_wine_app_database():
    db_path = os.path.abspath(os.path.join(os.path.dirname(__file__), 'wine_app.db'))
    conn = sqlite3.connect(db_path)
    cursor = conn.cursor()

    cursor.execute('''
    CREATE TABLE IF NOT EXISTS User (
        username TEXT PRIMARY KEY,
        fullname TEXT NOT NULL,
        email TEXT NOT NULL,
        phone TEXT NOT NULL,
        password TEXT NOT NULL
    )
    ''')

    cursor.execute('''
    CREATE TABLE IF NOT EXISTS Wines (
        title TEXT PRIMARY KEY,
        info TEXT,
        price REAL NOT NULL,
        photo_code TEXT,
        stock INTEGER DEFAULT 0,
        type INTEGER CHECK(type IN (1, 2, 3)) NOT NULL
    )
    ''')

    cursor.execute('''
    CREATE TABLE IF NOT EXISTS Orders (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        firstname TEXT NOT NULL,
        lastname TEXT NOT NULL,
        phone TEXT NOT NULL,
        email TEXT NOT NULL,
        address TEXT NOT NULL,
        pcode TEXT NOT NULL,
        city TEXT NOT NULL,
        username TEXT NOT NULL,
        FOREIGN KEY(username) REFERENCES User(username)
    )
    ''')

    cursor.execute('''
    CREATE TABLE IF NOT EXISTS Wishlist (
        username TEXT NOT NULL,
        title TEXT NOT NULL,
        FOREIGN KEY(username) REFERENCES User(username),
        FOREIGN KEY(title) REFERENCES Wines(title),
        UNIQUE(username, title)
    )
    ''')

    cursor.execute('''
    CREATE TABLE IF NOT EXISTS Cart (
        username TEXT NOT NULL,
        title TEXT NOT NULL,
        count INTEGER NOT NULL CHECK(count >= 0),
        FOREIGN KEY(username) REFERENCES User(username),
        FOREIGN KEY(title) REFERENCES Wines(title)
    )
    ''')

"""
def insert_initial_wines():
    # Wine data (title, info/characteristic, price €, code, stock, type)
    wines = [
        ("ΑΒΑΝΤΙΣ SAUVIGNON BLANC", "Ήπιος 0,75L", "16,52 €", "WH001", 9, 2),
        ("ΑΓΙΟΣ ΧΡΟΝΟΣ ΑΒΑΝΤΙΣ", "Ήπιος 0,75L", "20,56 €", "WH002", 8, 2),
        ("ΚΤΗΜΑ ΚΥΡ-ΓΙΑΝΝΗ ΝΤΡΟΥΜΟ", "Ήπιος 0,75L", "24,30 €", "WH003", 3, 2),
        ("ΚΤΗΜΑ ΛΑΝΤΙΔΗ SAUVIGNION BLANC", "Ήπιος 0,75L", "12,50 €", "WH004", 4, 2),
        ("ΚΤΗΜΑ ΑΡΓΥΡΟΣ CUVEE MONSIGNIORI", "Ήπιος 0,75L", "52,77 €", "WH005", 2, 2),
        ("THE FEDERALIST CHARDONNAY", "Ήπιος 0,75L", "25,92 €", "WH006", 0, 2),
        ("ΚΤΗΜΑ ΣΕΜΕΛΗ ΜΑΝΤΙΝΕΙΑ", "Ξηρός 0,75L", "12,50 €", "WH007", 7, 2),
        ("ΚΤΗΜΑ ΒΟΥΡΒΟΥΚΕΛΗ CHARDONNAY", "Ξηρός 0,75L", "17,84 €", "WH008", 5, 2),
        ("COMTE LAFOND SANCERRE", "Ξηρός 0,75L", "40,36 €", "WH009", 2, 2),
        ("ΚΑΛΛΙΣΤΗ RESERVE ΠΟΠ", "Ξηρός 0,75L", "37,76 €", "WH010", 4, 2),
        
        ("ΣΙΓΑΛΑ ΜΑΥΡΟΤΡΑΓΑΝΟ", "Ξηρός 0,75L", "71,18 €", "RE001", 4, 3),
        ("ΚΤΗΜΑ ΖΑΧΑΡΙΑ LEXIS ΜΑΥΡΟΔΑΦΝΗ", "Ήπιος 0,75L", "22,32 €", "RE002", 6, 3),
        ("ΑΒΑΝΤΙΣ SYRAH ΣΥΛΛΟΓΗ", "Ήπιος 0,75L", "81,84 €", "RE003", 8, 3),
        ("ΚΤΗΜΑ ΚΥΡ-ΓΙΑΝΝΗ ΜΠΛΕ ΑΛΕΠΟΥ", "Ξηρό 0,75L", "59,72 €", "RE004", 7, 3),
        ("MICHELE CHIARLO “PALAS”", "Ήπιος 0,75L", "47,13 €", "RE005", 0, 3),
        ("SASSICAIA LE DIFESE", "Ξηρό 0,75L", "33,73 €", "RE006", 10, 3),
        ("ΚΤΗΜΑ ΜΟΝΤΟΦΩΛΙ ΛΙΑΣΤΟ", "Γλυκό  0,5L", "40,36 €", "RE007", 6, 3),
        ("ΚΤΗΜΑ ΑΛΦΑ SYRAH", "Ξηρός 0,75L", "19,08 €", "RE008", 8, 3),
        ("ΑΙΓΑΙΑ ΕΡΥΘΡΟΣ ΟΙΝΟΣ", "Ξηρός 0,75L", "7,81 €", "RE009", 6, 3),
        ("CHATEAU JULIA ΑΓΙΩΡΓΙΤΙΚΟ", "Ξηρός 0,75L", "37,76 €", "RE010", 2, 3),
        
        ("WHISPERING ANGEL", "Ξηρός 0,75L", "31,67 €", "RO001", 5, 1),
        ("ULTIMATE PROVENCE UP ROZE", "Ήπιος 0,75L", "28,00 €", "RO002", 4, 1),
        ("MINUTI M", "Ήπιος 0,75L", "22,42 €", "RO003", 9, 1),
        ("REZINE ROSE", "Ξηρό 0,75L", "23,97 €", "RO004", 3, 1),
        ("ΚΤΗΜΑ ΣΚΟΥΡΑΣ PEPLO", "Ξηρό 0,75L", "19,53 €", "RO005", 2, 1),
        ("SNOB", "Ξηρό 0,75L", "19,52 €", "RO006", 1, 1),
        ("KYLIE MINOGUE ALCOHOL FREE SPARKLING", "Ξηρό 0,75L", "17,19 €", "RO007", 6, 1),
        ("ΑΜΕΘΥΣΤΟΣ ΡΟΖΕ LAZARIDI", "Ξηρός 0,75L", "14,98 €", "RO008", 0, 1),
        ("LENGA PINK ΑΒΑΝΤΙΣ", "Ξηρός 0,75L", "13,54 €", "RO009", 7, 1),
        ("MOI JE MEN FOUS", "Ξηρός 0,75L", "13,57 €", "RO010", 3, 1),
    ]

    conn = sqlite3.connect('wine_app.db')
    cursor = conn.cursor()
    

    for title, characteristic, price_str, code, stock, wine_type in wines:
        # Convert "12,50 €" → 12.50
        price = price_str.replace("€", "").replace(",", ".").strip()
        cursor.execute('''
            INSERT OR REPLACE INTO Wines (title, info, price, photo_code, stock, type)
            VALUES (?, ?, ?, ?, ?, ?)
        ''', (title, characteristic, price, code, stock, wine_type))

    conn.commit()
    conn.close()

"""


def print_all_tables():
    db_path = os.path.abspath(os.path.join(os.path.dirname(__file__), 'wine_app.db'))
    conn = sqlite3.connect(db_path)
    cursor = conn.cursor()

    print("\n===== USERS =====")
    for row in cursor.execute('SELECT * FROM User'):
        print(row)

    print("\n===== WINES =====")
    for row in cursor.execute('SELECT * FROM Wines'):
        print(row)

    print("\n===== ORDERS =====")
    for row in cursor.execute('SELECT * FROM Orders'):
        print(row)

    print("\n===== WISHLIST =====")
    for row in cursor.execute('SELECT * FROM Wishlist'):
        print(row)

    print("\n===== CART =====")
    for row in cursor.execute('SELECT * FROM Cart'):
        print(row)

    conn.close()



@app.route("/load", methods=["POST"])
def loadWines():
    db_path = os.path.abspath(os.path.join(os.path.dirname(__file__), 'wine_app.db'))
    conn = sqlite3.connect(db_path)
    cursor = conn.cursor()
    data = request.json

    username = data.get("username")
    number = data.get("number")

    if username is None or number is None:
        conn.close()
        return jsonify({"status": "Error", "message": "Missing username or number"}), 400

    if number in [0, 1, 2, 3, 4]:
        if number in [1, 2, 3]:
            cursor.execute('SELECT title, info, price, photo_code, stock FROM Wines WHERE type = ?', (number,))
            rows = cursor.fetchall()
            conn.close()

            wines_list = [["Title", "Info", "Price", "Photo_code", "Stock"]]
            for row in rows:
                wines_list.append([
                    row[0],
                    row[1],
                    f"{row[2]:.2f}",
                    str(row[3]),
                    str(row[4])
                ])
            print_all_tables()
            return jsonify(wines_list), 200

        elif number == 0:
            cursor.execute('''
                SELECT w.title, w.info, w.price, w.photo_code, w.stock
                FROM Wines w
                JOIN Wishlist wl ON w.title = wl.title
                WHERE wl.username = ?
            ''', (username,))
            
            rows = cursor.fetchall()
            conn.close()

            wines_list = [["Title", "Info", "Price", "Photo_code", "Stock"]]
            for row in rows:
                wines_list.append([
                    row[0],
                    row[1],
                    f"{row[2]:.2f}",
                    str(row[3]),
                    str(row[4])
                ])
            print_all_tables()
            return jsonify(wines_list), 200
        elif number == 4:
            cursor.execute('''
                SELECT w.title, w.info, w.price, w.photo_code, w.stock, c.count
                FROM Wines w
                JOIN Cart c ON w.title = c.title
                WHERE c.username = ?
            ''', (username,))
            
            rows = cursor.fetchall()
            conn.close()

            wines_list = [["Title", "Info", "Price", "Photo_code", "Stock", "Count"]]
            for row in rows:
                wines_list.append([
                    row[0],                   # title
                    row[1],                   # info
                    f"{row[2]:.2f}",          # price
                    str(row[3]),              # photo_code
                    str(row[4]),              # stock
                    str(row[5])               # count from Cart
                ])
            print_all_tables()
            return jsonify(wines_list), 200
    elif number == 5:
        cursor.execute('''
            SELECT username, fullname, email, phone
            FROM User
            WHERE username = ?
        ''', (username,))
        
        row = cursor.fetchone()
        conn.close()

        if row:
            user_info = [["Username", "Fullname", "Email", "Phone"], [
                row[0],  # username
                row[1],  # fullname
                row[2],  # email
                row[3]   # phone
            ]]
            print_all_tables()
            return jsonify(user_info), 200
        else:
            return jsonify({"status": "Error", "message": "User not found"}), 404

    else:
        conn.close()
        return jsonify({"status": "Error", "message": "Invalid number parameter"}), 400



@app.route("/update", methods=["POST"])
def update_cart_or_wishlist():
    db_path = os.path.abspath(os.path.join(os.path.dirname(__file__), 'wine_app.db'))
    conn = sqlite3.connect(db_path)

    cursor = conn.cursor()
    data = request.json

    username = data.get("username")
    title = data.get("Title")
    cartwish = data.get("CartWish")
    addmin = data.get("AddMin")

    if not username or not title or cartwish not in [1, 2] or addmin not in [1, 2]:
        conn.close()
        return jsonify({"status": "Error", "message": "Invalid parameters"}), 400

    try:
        if cartwish == 1:  # Cart
            if addmin == 1:  # Add to Cart
                # Check if already in cart
                cursor.execute('SELECT count FROM Cart WHERE username = ? AND title = ?', (username, title))
                row = cursor.fetchone()
                if row:
                    # Already exists → increment count
                    cursor.execute(
                        'UPDATE Cart SET count = count + 1 WHERE username = ? AND title = ?',
                        (username, title)
                    )
                else:
                    # New -> insert with count = 1
                    cursor.execute(
                        'INSERT INTO Cart (username, title, count) VALUES (?, ?, ?)',
                        (username, title, 1)
                    )
            elif addmin == 2:  # Remove from Cart
                cursor.execute(
                    'DELETE FROM Cart WHERE username = ? AND title = ?',
                    (username, title)
                )

        elif cartwish == 2:  # Wishlist
            if addmin == 1:  # Add to Wishlist
                cursor.execute(
                    'INSERT OR IGNORE INTO Wishlist (username, title) VALUES (?, ?)',
                    (username, title)
                )
            elif addmin == 2:  # Remove from Wishlist
                cursor.execute(
                    'DELETE FROM Wishlist WHERE username = ? AND title = ?',
                    (username, title)
                )

        conn.commit()
        conn.close()
        print_all_tables()
        return jsonify({"status": "Success"}), 200

    except Exception as e:
        conn.rollback()
        conn.close()
        print(e)
        return jsonify({"status": "Error", "message": str(e)}), 500



@app.route("/cartmanage", methods=["POST"])
def cart_manage():
    db_path = os.path.abspath(os.path.join(os.path.dirname(__file__), 'wine_app.db'))
    conn = sqlite3.connect(db_path)

    cursor = conn.cursor()
    data = request.json

    username = data.get("username")
    title = data.get("Title")
    addmin = data.get("AddMin")

    if not username or not title or addmin not in [1, 2]:
        conn.close()
        return jsonify({"status": "Error", "message": "Invalid parameters"}), 400

    try:
        cursor.execute('SELECT count FROM Cart WHERE username = ? AND title = ?', (username, title))
        row = cursor.fetchone()

        if not row:
            conn.close()
            return jsonify({"status": "Error", "message": "Item not found in cart"}), 404

        current_count = row[0]

        if addmin == 1:
            if current_count > 1:
                cursor.execute(
                    'UPDATE Cart SET count = count - 1 WHERE username = ? AND title = ?',
                    (username, title)
                )
            else:
                cursor.execute(
                    'DELETE FROM Cart WHERE username = ? AND title = ?',
                    (username, title)
                )
        elif addmin == 2:
            cursor.execute(
                'UPDATE Cart SET count = count + 1 WHERE username = ? AND title = ?',
                (username, title)
            )

        conn.commit()
        conn.close()
        print_all_tables()
        return jsonify({"status": "Success"}), 200

    except Exception as e:
        conn.rollback()
        conn.close()
        print(e)
        return jsonify({"status": "Error", "message": str(e)}), 500


@app.route("/register", methods=["POST"])
def register_user():
    db_path = os.path.abspath(os.path.join(os.path.dirname(__file__), 'wine_app.db'))
    conn = sqlite3.connect(db_path)

    cursor = conn.cursor()
    data = request.json

    username = data.get("username")
    fullname = data.get("fullname")
    email = data.get("email")
    phone = data.get("phone")
    password = data.get("password")

    if not username or not fullname or not email or not phone or not password:
        conn.close()
        return jsonify({"status": "Error", "message": "Missing fields"}), 400

    try:
        cursor.execute('''
            INSERT INTO User (username, fullname, email, phone, password)
            VALUES (?, ?, ?, ?, ?)
        ''', (username, fullname, email, phone, password))

        conn.commit()
        conn.close()
        print_all_tables()
        return jsonify({"status": "Success"}), 200

    except sqlite3.IntegrityError:
        conn.close()
        return jsonify({"status": "Error", "message": "Username already exists"}), 409
    except Exception as e:
        conn.rollback()
        conn.close()
        return jsonify({"status": "Error", "message": str(e)}), 500


@app.route("/login", methods=["POST"])
def login_user():
    db_path = os.path.abspath(os.path.join(os.path.dirname(__file__), 'wine_app.db'))
    conn = sqlite3.connect(db_path)

    cursor = conn.cursor()
    data = request.json

    username = data.get("username")
    password = data.get("password")

    if not username or not password:
        conn.close()
        return jsonify({"status": "Error", "message": "Missing fields"}), 400

    cursor.execute("SELECT * FROM User WHERE username = ? AND password = ?", (username, password))
    user = cursor.fetchone()

    conn.close()

    if user:
        return jsonify({"status": "Success"}), 200
    else:
        return jsonify({"status": "Error", "message": "Invalid username or password"}), 401



@app.route("/order", methods=["POST"])
def place_order():
    db_path = os.path.abspath(os.path.join(os.path.dirname(__file__), 'wine_app.db'))
    conn = sqlite3.connect(db_path)

    cursor = conn.cursor()
    data = request.json

    firstname = data.get("firstname")
    lastname = data.get("lastname")
    phone = data.get("phone")
    email = data.get("email")
    address = data.get("address")
    pcode = data.get("pcode")
    city = data.get("city")
    username = data.get("username")

    if not all([firstname, lastname, phone, email, address, pcode, city, username]):
        conn.close()
        return jsonify({"status": "Error", "message": "Missing order fields"}), 400

    try:
        cursor.execute('''
            INSERT INTO Orders (firstname, lastname, phone, email, address, pcode, city, username)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        ''', (firstname, lastname, phone, email, address, pcode, city, username))

        conn.commit()
        conn.close()
        print_all_tables()
        return jsonify({"status": "Success"}), 200

    except Exception as e:
        conn.rollback()
        conn.close()
        print(e)
        return jsonify({"status": "Error", "message": str(e)}), 500


if __name__ == "__main__":
    initialize_wine_app_database()
    import os, sqlite3
    db_path = os.path.abspath(os.path.join(os.path.dirname(__file__), 'wine_app.db'))
    print("DB:", db_path)
    with sqlite3.connect(db_path) as c:
        # optional: better concurrency on your EXISTING DB
        c.execute("PRAGMA journal_mode=WAL;")
        c.execute("PRAGMA synchronous=NORMAL;")
    app.run(debug=False, threaded=True)
    """insert_initial_wines()"""
    #app.run(debug=True)




