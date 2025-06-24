Here is a **complete and professional `README.md`** for your Spring Boot application that includes:

* Environment setup using Docker Compose
* Database configuration via pgAdmin
* Flyway schema & data initialization
* How to build and run the app
* Example API endpoints to test

---

### 📄 `README.md`

````markdown
# 🛠️ Product Management API (Spring Boot + PostgreSQL + Flyway)

This project is a simple Spring Boot RESTful API for managing products, using:

- **PostgreSQL** (via Docker)
- **Flyway** for database schema & data initialization
- **pgAdmin** for database management
- **Maven Wrapper (`./mvnw`)** for build/run tasks

---

## 🚀 Technologies Used

- Spring Boot 3
- PostgreSQL (via Docker)
- Flyway (for DB migrations)
- pgAdmin 4
- Maven Wrapper (`./mvnw`)
- Java 21

---

## 🧪 Features

- View all products
- Retrieve a product by ID
- Create, update, delete products
- View all users
- Retrieve a user by ID
- Create, update, delete users
- Initial product data inserted automatically via Flyway

---

## ⚙️ Environment Setup

### 1. Start PostgreSQL & pgAdmin via Docker

Make sure Docker is installed and running.

```bash
docker compose up -d
````

This starts:

* PostgreSQL on `localhost:54320`
* pgAdmin on `localhost:5050`

---

### 2. Connect to pgAdmin

1. Open browser at [http://localhost:5050]

2. Login using the default credentials:

    * **Email:** `user@admin.com`
    * **Password:** `password`

3. In the pgAdmin UI:

    * Create a **server group** (e.g. `product-server-group`)
    * Add a **server** with:

        * **Name:** `product-server`
        * **Host:** click right click on properties, then in connect, update your IP address
        * **Port:** `54320`
        * **Username:** `user`
        * **Password:** `password`

---

## 🧬 Flyway Database Initialization

On application startup, **Flyway** runs migration scripts found in:

```
src/main/resources/db/postgres/migration/
```

### Initial Schema (`V1_1__create_tables.sql)
    CREATE TABLE IF NOT EXISTS users (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    username VARCHAR(100) NOT NULL UNIQUE,
    email VARCHAR(150) NOT NULL UNIQUE,
    phone VARCHAR(20),
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
    );

    CREATE TABLE IF NOT EXISTS products (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    user_id BIGINT,
    name VARCHAR(200) NOT NULL,
    description VARCHAR(500),
    price NUMERIC(12, 2) NOT NULL,
    sku VARCHAR(50),
    category VARCHAR(100),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
    );
### Initial data (`V1_2__init_data.sql)
    INSERT INTO users (username, email, phone, first_name, last_name, created_at, updated_at) VALUES
    ('imad', 'imad@gmail.com', '0612345678', 'imad', 'mak', NOW(), NOW()),
    ('adam', 'adam@gmail.com', '0698765432', 'adam', 'mak', NOW(), NOW()),
    ('ronaldo', 'ronaldo@gmail.com', null, 'ronaldo', 'Bar', NOW(), NOW());
### Initial data (`V1_2__init_data.sql)
    INSERT INTO products (user_id, name, description, price, sku, category, created_at, updated_at) VALUES
    (1, 'Wireless Mouse', 'Ergonomic wireless mouse with 2.4 GHz connectivity and long battery life.', 24.99, 'WM-1001', 'Electronics', NOW(), NOW()),
    (1, 'Gaming Keyboard', 'Mechanical keyboard with RGB backlighting and anti-ghosting features.', 79.90, 'GK-2001', 'Electronics', NOW(), NOW()),
    (2, 'Running Shoes', 'Lightweight and breathable running shoes for daily training.', 59.95, 'RS-3001', 'Sportswear', NOW(), NOW()),
    (2, 'Fitness Tracker', 'Water-resistant fitness tracker with heart rate and sleep monitoring.', 49.99, 'FT-4002', 'Wearables', NOW(), NOW()),
    (3, 'Smartphone Case', 'Shockproof silicone case compatible with iPhone and Android.', 12.50, 'SC-5003', 'Accessories', NOW(), NOW()),
    (3, 'Bluetooth Speaker', 'Portable Bluetooth speaker with 360° sound and 12h battery life.', 39.99, 'BS-6004', 'Audio', NOW(), NOW());


### 1. Compile the project

```bash
./mvnw clean compile
```

### 2. Run the Spring Boot app

```bash
./mvnw spring-boot:run
```

---

## 🔗 API Endpoints

| Method | Endpoint                                | Description                  |
|--------|-----------------------------------------|------------------------------|
| GET    | `/products`                             | Retrieve all products        |
| GET    | `/products/{id}`                        | Retrieve product by ID       |
| POST   | `/products`                             | Create a new product         |
| PUT    | `/products/{id}`                        | Update product by ID         |
| DELETE | `/products/{id}`                        | Delete product by ID         |
| GET    | `/product/user/{userId}`                | Get products by user ID      |
| POST   | `/attach/{userId}/products/{productId}` | Attach product to user by ID |
| GET    | `users`                                 | Retrieve allusers            |
| GET    | `user/{id}`                             | Retrieve user by ID          |
| DELETE | `delete/user/{id}`                      | Delete user by ID            |
| DELETE | `/user`                                 | Create a new user            |
| DELETE | `/updateUser/{id}`                      | Update product by ID         |





---

### 1. Compile the project

```bash
./mvnw clean compile
```

### 2. Run the Spring Boot app

```bash
./mvnw spring-boot:run
```

The app will start on: (http://localhost:8080)


```http Examples
GET http://localhost:8080/products
GET http://localhost:8080/products/1
```.




