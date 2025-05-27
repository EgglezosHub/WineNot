from flask import Flask, request, jsonify
from flask_cors import CORS
import sqlite3

app = Flask(__name__)
CORS(app)  # Allow requests from JavaFX


# Initialize the database
def initialize_database():
    conn = sqlite3.connect('wine_shop.db')
    cursor = conn.cursor()
    
    # Create User table
    cursor.execute('''
    CREATE TABLE IF NOT EXISTS User (
        username TEXT PRIMARY KEY,
        Fullname TEXT NOT NULL,
        email TEXT NOT NULL,
        phone TEXT NOT NULL,
        password TEXT NOT NULL
    )
    ''')

    # Create Wines table without ID
    cursor.execute('''
    CREATE TABLE IF NOT EXISTS Wines (
        Title TEXT PRIMARY KEY,
        info TEXT,
        price REAL NOT NULL,
        PhotoCode INTEGER,
        Stock INTEGER NOT NULL,
        wishlist_flag TEXT DEFAULT 'no' CHECK(wishlist_flag IN ('yes', 'no')),
        cart_flag TEXT DEFAULT 'no' CHECK(cart_flag IN ('yes', 'no')),
        type INTEGER CHECK(type IN (1, 2, 3))
    )
    ''')

    # Create Orders table without ID
    cursor.execute('''
    CREATE TABLE IF NOT EXISTS Orders (
        Firstname TEXT NOT NULL,
        Lastname TEXT NOT NULL,
        phone TEXT NOT NULL,
        email TEXT NOT NULL,
        address TEXT NOT NULL,
        P_code TEXT NOT NULL,
        City TEXT NOT NULL,
        UID TEXT NOT NULL,
        FOREIGN KEY(UID) REFERENCES User(username)
    )
    ''')

    conn.commit()
    conn.close()

