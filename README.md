# Spring Boot Customer API with Oracle Database

## 📌 Overview
Proyek ini adalah aplikasi Spring Boot yang diintegrasikan dengan Oracle Database. Aplikasi ini memiliki fitur CRUD (Create, Read, Update, Delete) untuk mengelola data pelanggan (Customer).

## 📁 Struktur Proyek
```
main
│── java
│   └── dev.codejar.springbooot_oracle
│       ├── controller
│       │   └── CustomerController.java
│       ├── models
│       │   ├── dto
│       │   │   └── CustomerDto.java
│       │   ├── entity
│       │   │   └── CustomerEntity.java
│       │   ├── payload
│       │   │   ├── request
│       │   │   │   └── CustomerRequest.java
│       │   │   ├── response
│       │   │   │   └── BaseResponse.java
│       ├── repository
│       │   └── CustomerRepository.java
│       ├── service
│       │   └── CustomerService.java
│       ├── Application.java
│── resources
│   ├── static
│   ├── templates
│   └── application.properties
```

## ⚙️ Konfigurasi Database (application.properties)
Pastikan Anda mengkonfigurasi database Oracle dengan benar di dalam `application.properties`:
```properties
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
spring.jpa.database-platform=org.hibernate.dialect.Oracle12cDialect
spring.jpa.hibernate.ddl-auto=update
```

## 🚀 API Endpoint CRUD
### 1️⃣ Create Customer
- **Endpoint:** `POST /api/customer`
- **Request Body:**
```json
{
  "customerId": 1,
  "name": "John Doe",
  "email": "john.doe@example.com",
  "phone": "1234567890"
}
```
- **Response:**
```json
{
  "success": true,
  "message": "success add customer",
  "data": {
    "customerId": 1,
    "name": "John Doe",
    "email": "john.doe@example.com",
    "phone": "1234567890"
  }
}
```

### 2️⃣ Get All Customers
- **Endpoint:** `GET /api/customer`
- **Response:**
```json
[
  {
    "customerId": 1,
    "name": "John Doe",
    "email": "john.doe@example.com",
    "phone": "1234567890"
  }
]
```

### 3️⃣ Update Customer
- **Endpoint:** `PUT /api/customer/{id}`
- **Request Body:**
```json
{
  "customerId": 1,
  "name": "John Smith",
  "email": "john.smith@example.com",
  "phone": "0987654321"
}
```
- **Response:**
```json
{
  "success": true,
  "message": "success update customer",
  "data": {
    "customerId": 1,
    "name": "John Smith",
    "email": "john.smith@example.com",
    "phone": "0987654321"
  }
}
```

### 4️⃣ Delete Customer
- **Endpoint:** `DELETE /api/customer/{name}`
- **Response:**
```json
"customer with name : John Smith already deleted"
```

## 🛠️ Cara Menjalankan Proyek
1. Pastikan Oracle Database sudah berjalan.
2. Sesuaikan `application.properties` dengan kredensial database Anda.
3. Jalankan perintah berikut di terminal:
   ```sh
   mvn spring-boot:run
   ```
4. Akses API menggunakan Postman atau tool lainnya.

## 📌 Teknologi yang Digunakan
- Java 11+
- Spring Boot 2.7+
- Spring Data JPA
- Hibernate
- Oracle Database

---


