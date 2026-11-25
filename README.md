<div align="center">

# 🛒 Product Service

**A RESTful Product Management API built with Spring Boot 4, Spring Data JPA and MySQL**

![Java](https://img.shields.io/badge/Java-25-orange?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.1-6DB33F?logo=springboot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.x-4479A1?logo=mysql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-Build-C71A36?logo=apachemaven&logoColor=white)

</div>

---

## 📖 Overview

**Product Service** is a backend microservice that exposes REST APIs to manage an e-commerce style product catalog. It follows a clean layered architecture (Controller → Service → Repository) with a dedicated DTO layer, request validation and a uniform response wrapper.

## ✨ Features

- Create, read, update and delete products (CRUD)
- Layered architecture: Controller, Service, Repository, Entity, DTO, Mapper
- Request validation using Jakarta Bean Validation
- Entity ↔ DTO conversion using **MapStruct**
- Uniform API response structure via a generic `ApiResponse<T>` wrapper
- Multiple images per product (`@ElementCollection`)
- Embedded product rating (rate and count)
- Auto-generated manufacturing timestamp (`@CreationTimestamp`)
- Auto-creation of database and tables on startup

## 🧰 Tech Stack

| Category        | Technology                                  |
|-----------------|---------------------------------------------|
| Language        | Java 25                                     |
| Framework       | Spring Boot 4.1.1 (Web MVC, Data JPA)       |
| Database        | MySQL                                       |
| ORM             | Hibernate / Spring Data JPA                 |
| Validation      | Jakarta Bean Validation                     |
| Mapping         | MapStruct 1.6.3                             |
| Boilerplate     | Lombok                                      |
| Build Tool      | Maven (Maven Wrapper included)              |

## 🏗️ Project Structure

```
product-service
├── src
│   ├── main
│   │   ├── java/com/training
│   │   │   ├── ProductServiceApplication.java
│   │   │   ├── controller    # REST controllers
│   │   │   ├── service       # Service interfaces
│   │   │   │   └── impl      # Business logic implementations
│   │   │   ├── repository    # Spring Data JPA repositories
│   │   │   ├── entity        # JPA entities (Product, Rating)
│   │   │   ├── dto           # Request / Response DTOs, ApiResponse
│   │   │   └── mapper        # MapStruct mappers
│   │   └── resources
│   │       ├── application.properties
│   │       ├── data.json     # Sample request payload
│   │       └── init.sql      # Optional sample data
│   └── test
├── pom.xml
└── README.md
```

## ✅ Prerequisites

Make sure the following are installed:

- **JDK 25**
- **MySQL** running on port `3306`
- **Git**
- Maven (optional, the included `mvnw` wrapper can be used)

## 🚀 Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/naveenrajput8823/product-service-springboot.git
cd product-service-springboot
```

### 2. Configure the database

Open `src/main/resources/application.properties` and set your MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/wd_3_30?createDatabaseIfNotExist=true
spring.datasource.username=<your_mysql_username>
spring.datasource.password=<your_mysql_password>
```

> The database `wd_3_30` and all required tables are created automatically on the first run.

### 3. Run the application

**Windows**
```powershell
.\mvnw.cmd spring-boot:run
```

**Linux / macOS**
```bash
./mvnw spring-boot:run
```

The application starts on **http://localhost:8082**

### 4. (Optional) Load sample data

After the application has started once and the tables exist, you can run the `INSERT` statements from `src/main/resources/init.sql` in your MySQL client to load sample products.

## 📡 API Reference

**Base URL:** `http://localhost:8082/api/v1/products`

| Method   | Endpoint                | Description          | Success Status |
|----------|-------------------------|----------------------|----------------|
| `POST`   | `/api/v1/products`      | Create a new product | `201 Created`  |
| `GET`    | `/api/v1/products`      | Get all products     | `200 OK`       |
| `PUT`    | `/api/v1/products/{id}` | Update a product     | `202 Accepted` |
| `DELETE` | `/api/v1/products/{id}` | Delete a product     | `202 Accepted` |

### Create Product

**Request**

```http
POST /api/v1/products
Content-Type: application/json
```

```json
{
  "title": "Levi's Men's 511 Slim Fit Mid Rise Jeans",
  "price": 2899.00,
  "description": "Slim fit jeans in solid black, suitable for casual wear.",
  "category": "Men's Clothing",
  "images": [
    "https://m.media-amazon.com/images/I/51nW98RxOLL._SX679_.jpg",
    "https://m.media-amazon.com/images/I/5144gSbtOnL._SX679_.jpg"
  ],
  "rating": {
    "rate": 4.0,
    "count": 100
  },
  "quantity": 10,
  "exp": "2028-01-01"
}
```

**Response** `201 Created`

```json
{
  "status": true,
  "data": {
    "id": 1,
    "title": "Levi's Men's 511 Slim Fit Mid Rise Jeans",
    "price": 2899.00,
    "description": "Slim fit jeans in solid black, suitable for casual wear.",
    "category": "Men's Clothing",
    "images": [
      "https://m.media-amazon.com/images/I/51nW98RxOLL._SX679_.jpg",
      "https://m.media-amazon.com/images/I/5144gSbtOnL._SX679_.jpg"
    ],
    "rate": 4.0,
    "count": 100,
    "quantity": 10,
    "mfg": "2026-09-22T16:30:00",
    "exp": "2028-01-01"
  },
  "message": "product saved!"
}
```

### Get All Products

```bash
curl http://localhost:8082/api/v1/products
```

### Update Product

```bash
curl -X PUT http://localhost:8082/api/v1/products/1 \
  -H "Content-Type: application/json" \
  -d @src/main/resources/data.json
```

### Delete Product

```bash
curl -X DELETE http://localhost:8082/api/v1/products/1
```

## 🔒 Validation Rules

| Field         | Rule                                                |
|---------------|-----------------------------------------------------|
| `title`       | Required, must not be blank                         |
| `price`       | Required, greater than 0, max 10 integer + 2 decimal digits |
| `description` | Required, must not be blank                         |
| `category`    | Required, max 100 characters                        |
| `images`      | Required, at least 1 image URL, each max 2048 characters |
| `quantity`    | Required, minimum 0                                 |
| `rating.rate` | Between 0.0 and 5.0 (1 decimal place)               |
| `rating.count`| Minimum 0                                           |

Invalid requests are rejected with `400 Bad Request`.

## 🗄️ Database Schema

**`products`**

| Column         | Type            | Notes                     |
|----------------|-----------------|---------------------------|
| `id`           | BIGINT          | Primary key, auto-increment |
| `product_title`| VARCHAR(255)    | Not null                  |
| `price`        | DECIMAL(12,2)   | Not null                  |
| `description`  | TEXT            | Not null                  |
| `category`     | VARCHAR(100)    | Not null                  |
| `rating_rate`  | DECIMAL(2,1)    | Embedded rating           |
| `rating_count` | INT             | Embedded rating           |
| `quantity`     | INT             | Not null                  |
| `mfg`          | DATETIME        | Auto-generated            |
| `expiry`       | DATE            | Optional                  |

**`product_images`**

| Column       | Type          | Notes                  |
|--------------|---------------|------------------------|
| `product_id` | BIGINT        | Foreign key → products |
| `image_url`  | VARCHAR(2048) | Not null               |

## 🧪 Running Tests

```bash
./mvnw test
```

## 🛣️ Roadmap

- [ ] Persist field changes in the update endpoint
- [ ] Global exception handling with proper HTTP status codes (`@RestControllerAdvice`)
- [ ] Get product by ID endpoint
- [ ] Pagination, sorting and filtering
- [ ] API documentation with Swagger / OpenAPI
- [ ] Unit and integration tests
- [ ] Docker and Docker Compose support
- [ ] Externalized configuration using environment variables

## 🤝 Contributing

Contributions, issues and feature requests are welcome.

1. Fork the repository
2. Create your feature branch: `git checkout -b feature/your-feature`
3. Commit your changes: `git commit -m "Add your feature"`
4. Push to the branch: `git push origin feature/your-feature`
5. Open a Pull Request

## 👨‍💻 Author

**Naveen Rajput**

- GitHub: [@naveenrajput8823](https://github.com/naveenrajput8823)

---

<div align="center">

⭐ If you found this project useful, consider giving it a star!

</div>
