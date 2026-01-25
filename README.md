# Food Court Microservice

Core business service for restaurants, dishes, and categories in the Food Court system. It owns the catalog and restaurant ownership rules used by other services and the UI.

## System Context
- Part of the Food Court microservices suite.
- Validates owners and employees by calling the User service via OpenFeign.
- Consumed by the Order service and the Food Court UI.

## Responsibilities
- Manage restaurants and categories.
- Manage dishes and availability.
- Enforce owner-level access on catalog operations.

## Architecture (Hexagonal / Ports and Adapters)
This service follows Hexagonal Architecture with explicit ports and adapters.
- `domain/`: core models, business rules, ports, and use cases.
- `application/`: DTOs, mappers, and handlers that orchestrate use cases.
- `infrastructure/`: REST controllers, JPA adapters, Feign clients, security configuration, and JWT implementation.

## Tech Stack
- Spring Boot 4, Spring Security, JWT
- Spring Data JPA + PostgreSQL
- Spring Cloud OpenFeign
- MapStruct, Springdoc OpenAPI

## Data Store
- PostgreSQL (local default: `jdbc:postgresql://localhost:5432/food_court_db`)

## API
- Base URL: `http://localhost:8091/foodcourt-api`
- Swagger UI: `http://localhost:8091/foodcourt-api/swagger-ui.html`

## Local Development
1. Start the database: `docker compose up -d` (uses `docker-compose.yml`)
2. Run the service: `./gradlew bootRun`
3. Run tests: `./gradlew test`

## System Diagrams
![Project Architecture](https://raw.githubusercontent.com/diegnghtmr/food-court-ui/main/docs/project-architecture.png)
![SQL Entities Diagram](https://raw.githubusercontent.com/diegnghtmr/food-court-ui/main/docs/sql-entities-diagram.png)
![Order Flow Diagram](https://raw.githubusercontent.com/diegnghtmr/food-court-ui/main/docs/order-flow-diagram.png)
![NoSQL Entities Diagram](https://raw.githubusercontent.com/diegnghtmr/food-court-ui/main/docs/nosql-entities-diagram.png)

## Related Repositories
- [user-microservice](https://github.com/diegnghtmr/user-microservice)
- [food-court-microservice](https://github.com/diegnghtmr/food-court-microservice)
- [order-microservice](https://github.com/diegnghtmr/order-microservice)
- [traceability-microservice](https://github.com/diegnghtmr/traceability-microservice)
- [food-court-ui](https://github.com/diegnghtmr/food-court-ui)
