# order-importer-api

## Tecnologies
- Java 21
- Maven
- SpringBoot 3
- JPA
- PostgreSQL

## Getting Started
### Prerequisites
- Install Java version 21.

### Running Locally
1. Start the PostgreSQL database using docker-compose up -d.
2. Run the Spring Boot application with `mvn spring-boot:run`.

## About the project
### Entity Relation Diagram
![image](https://github.com/user-attachments/assets/75496da3-0160-454b-a4d1-3a90b7601088)

### Documentation
Explore the API documentation using Swagger. Access the resources at `{api-path}/swagger-ui/index.html`.

Example: `http://localhost:8080/swagger-ui/index.html`

### Database
The project is configured to connect to a PostgreSQL database, version 16 (other versions should be validated). The Docker Compose file (docker-compose.yml) is provided for local API setup. The database settings are configured to connect to a database in a container (localhost), and you can adjust them in application.yml if needed.

#### Migration
This project uses [Liquibase](https://www.liquibase.org/) for database versioning and deployment. Migrations can be found in `resources/changelog`.

#### Running Tests
- `./mvn test` to run unit tests
