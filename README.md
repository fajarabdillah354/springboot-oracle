﻿# Spring Boot & Oracle Database Integration

## 📌 Overview
This project is a Spring Boot application integrated with an Oracle database. It provides RESTful APIs for managing customer data, handling transactions, and calculating risk scores using stored procedures in Oracle.

## 📂 Project Structure
```
 dev.codejar.springbooot_oracle
 ├── controller
 │   ├── CsController.java
 │   ├── CustomerController.java
 │
 ├── models
 │   ├── dto
 │   │   ├── CustomerDto.java
 │   ├── entity
 │   │   ├── AccountEntity.java
 │   │   ├── CustomerEntity.java
 │   │   ├── TransactionEntity.java
 │   ├── payload
 │       ├── request
 │       │   ├── CsRequest.java
 │       ├── response
 │           ├── BaseResponse.java
 │
 ├── repository
 │   ├── CsRepository.java
 │
 ├── service
 │   ├── CsService.java
 │   ├── CustomerService.java
 │
 ├── resources
 │   ├── application.properties
 │
 ├── test
 │
 ├── Application.java
```

## ⚙️ Setup and Installation
### Prerequisites
- Java 17+
- Maven
- Oracle Database

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo.git
   cd springbooot-oracle-case
   ```
2. Configure the database connection in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:oracle:thin:@localhost:1521:orcl
   spring.datasource.username=your_user
   spring.datasource.password=your_password
   spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
   spring.jpa.database-platform=org.hibernate.dialect.Oracle12cDialect
   ```
3. Build and run the application:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

## 📌 API Endpoints
### Customer Management
| Method | Endpoint | Description |
|--------|---------|-------------|
| GET    | `/api/cs/{id}` | Get customer by ID |
| GET    | `/api/cs/all` | Get all customers |
| POST   | `/api/cs` | Create a new customer |
| PATCH  | `/api/cs/{id}/status` | Update customer status |
| PATCH  | `/api/cs/{id}/date` | Update birth date |
| GET    | `/api/cs/{id}/risk-score` | Calculate risk score |
| GET    | `/api/cs/{id}/level` | Get customer level |
| GET    | `/api/cs/{id}/account` | Get customer account info |
| POST   | `/api/cs/transfer` | Transfer funds between accounts |

### Example Request/Response
#### Create Customer
**Request:**
```json
{
  "name": "John Doe",
  "email": "johndoe@example.com",
  "birthDate": "1990-01-01"
}
```
**Response:**
```json
{
  "success": true,
  "message": "success add new customer",
  "data": {
    "id": 1,
    "name": "John Doe",
    "email": "johndoe@example.com",
    "birthDate": "1990-01-01"
  }
}
```

## 🛠️ Technologies Used
- **Spring Boot** (Spring MVC, Spring Data JPA)
- **Oracle Database**
- **Hibernate ORM**
- **Maven**
- **REST API**

## 🛠️ Cara Menjalankan Proyek
1. Pastikan Oracle Database sudah berjalan.
2. Sesuaikan `application.properties` dengan kredensial database Anda.
3. Jalankan perintah berikut di terminal:
   ```sh
   mvn spring-boot:run
   ```
4. Akses API menggunakan Postman atau tool lainnya.

## 🚀 Running Tests
Run unit tests with:
```bash
mvn test
```

## 📖 Additional Notes
- This project uses stored procedures for updating status and calculating risk score.
- Make sure the required PL/SQL procedures exist in your Oracle database before running the application.

## 📌 Author
Developed by Fajar Abdillah Ahmad. Feel free to contribute and improve this project!

