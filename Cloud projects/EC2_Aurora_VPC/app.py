from flask import Flask, request, jsonify
import mysql.connector

app = Flask(__name__)


DB_HOST = 'database-1-instance-1.c7gq6e4ocno7.us-east-1.rds.amazonaws.com'
DB_USER = 'admin'
DB_PASSWORD = 'admin123'
DB_DATABASE = 'test'

# Connect to MySQL database
def connect_to_database():
    return mysql.connector.connect(
        host=DB_HOST,
        user=DB_USER,
        password=DB_PASSWORD,
        database=DB_DATABASE
    )

# Initialize the database (drop and create the products table)
def initialize_database():
    try:
        # Connect to the database
        connection = connect_to_database()
        cursor = connection.cursor()

        # Drop and create the products table
        cursor.execute("DROP TABLE IF EXISTS products")
        create_products_table(cursor)

        # Commit the changes
        connection.commit()

        # Close the cursor and connection
        cursor.close()
        connection.close()

    except Exception as e:
        print(f"Error initializing database: {str(e)}")

# Create the products table if not exists
def create_products_table(cursor):
    cursor.execute("""
        CREATE TABLE IF NOT EXISTS products (
            name VARCHAR(100),
            price VARCHAR(100),
            availability BOOLEAN
        )
    """)

# Initialize the database on application startup
initialize_database()

# Route to handle storing products in the database
@app.route('/store-products', methods=['POST'])
def store_products():
    try:
        # Receive and parse JSON body
        data = request.get_json()

        # Connect to the database
        connection = connect_to_database()
        cursor = connection.cursor()

        # Create the products table if not exists
        create_products_table(cursor)

        # Insert records into the products table
        for product in data.get('products', []):
            cursor.execute("""
                INSERT INTO products (name, price, availability)
                VALUES (%s, %s, %s)
            """, (product['name'], product['price'], product['availability']))

        # Commit the changes
        connection.commit()

        # Close the cursor and connection
        cursor.close()
        connection.close()

        # Return a status 200 code if everything works
        return jsonify({"message": "Products stored successfully."}), 200

    except Exception as e:
        # Return the appropriate JSON response with an error message and status code
        return jsonify({'error': str(e)}), 400

# Route to handle listing products from the database
@app.route('/list-products', methods=['GET'])
def list_products():
    try:
        # Connect to the database
        connection = connect_to_database()
        cursor = connection.cursor()

        # Create the products table if not exists
        create_products_table(cursor)

        # Query the products table
        cursor.execute("SELECT * FROM products")
        products = cursor.fetchall()

        # Close the cursor and connection
        cursor.close()
        connection.close()

        # Convert TINYINT(1) to boolean in the JSON response
        converted_products = [
            {'name': name, 'price': price, 'availability': bool(availability)}
            for name, price, availability in products
        ]

        # Return a list of all products with a status 200 code
        return jsonify({'products': converted_products}), 200

    except Exception as e:
        # Return the appropriate JSON response with an error message and status code
        return jsonify({'error': str(e)}), 500

if __name__ == '__main__':
    app.run(debug=True)
