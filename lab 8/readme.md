# Customer Management REST API System

## Student Information
- **Name:** Ngô Đức Anh
- **Student ID:** ITDSIU24003
- **Class:** WEB APP LAB 8

## Technologies Used
- Spring Boot 3.3.5
- Spring Data JPA
- MySQL
- H2 In-Memory Database (Fallback)
- Spring HATEOAS (Richardson Maturity Level 3 links)
- Bucket4j (Token Bucket Rate Limiting)
- Jakarta Validation
- Maven & JDK 21

## Setup Instructions
1. Import the project folder `customer-api` into VS Code or IntelliJ IDEA.
2. Create database in MySQL: `customer_management`
3. Update `src/main/resources/application.properties` with your local MySQL credentials.
   *(Optional: You can uncomment the H2 Database fallback section to run the application immediately without local MySQL)*
4. Run the application:
   - In terminal, navigate to the `customer-api` folder and run: `./mvnw spring-boot:run` (or `mvnw spring-boot:run` on Windows).
5. Testing the API:
   - **REST Endpoints:** Base URL is `http://localhost:8080/api/v1/customers` (and version 2 at `/api/v2/customers`).
   - **Manual Testing Suite:** Open and run requests in `customer-api/testAPI.http` using the VS Code REST Client extension.
   - **Interactive Frontend Simulator:** Open `demo.html` in your web browser by double-clicking the file to interactively test the REST API operations, DTO changes, HATEOAS, and rate-limiting blocks.

## Completed Features
- [x] Full CRUD operations (GET, POST, PUT, PATCH, DELETE)
- [x] Search functionality by keyword (matching Code, Name, Email)
- [x] Advanced search with status filters (ACTIVE / INACTIVE)
- [x] Input Data Validation (Jakarta Validation with Regex pattern `^C\d{3,}$` for `customer_code`)
- [x] Sorting & Pagination support (customizable sorting fields, directions, page numbers, and size)
- [x] DTO Pattern application (separating Entities and API Response/Request payloads)
- [x] API Versioning (Bonus - V1 basic DTO & V2 expanded DTO using URL paths)
- [x] HATEOAS integration (Bonus - adding HAL self-navigating links to REST responses)
- [x] Rate Limiting protection (Bonus - Bucket4j throttling up to 100 requests/minute per IP, returning HTTP 429)
- [x] Interactive UI Simulator (Bonus - beautiful Glassmorphism dashboard demo app `demo.html` with real-time UI capture under `/img`)

## Project Structure
- `customer-api/` (Backend Spring Boot application)
  - `src/main/java/com/example/customerapi/`
    - `config/` - Rate Limiting (Bucket4j Filter config)
    - `controller/` - REST Controllers (`CustomerController` & `CustomerV2Controller`)
    - `dto/` - Data Transfer Objects (`CustomerDTO` & `CustomerV2DTO`)
    - `entity/` - JPA Domain Model (`Customer` Entity)
    - `exception/` - Custom Exceptions & Global Exception Handler
    - `repository/` - Spring Data JPA Repository (`CustomerRepository`)
    - `service/` - Business Logic Interfaces and Implementations
  - `src/main/resources/`
    - `application.properties` - Application config
  - `testAPI.http` - REST API manual client test suite
- `demo.html` - Interactive client-side single page app simulator
- `img/` - Generated graphical assets
  - `demo_screenshot.png` - Captures of the dashboard simulator interface