# Food Court Catalog API reference

## API endpoints

Base URL: `http://localhost:8091/foodcourt-api`. The running service exposes [Swagger UI](http://localhost:8091/foodcourt-api/swagger-ui.html) for request and response schemas.

| Method and path | Purpose |
| --- | --- |
| `POST /restaurants` | Register a restaurant after looking up the proposed owner in Identity; requires an administrator |
| `GET /restaurants` | List restaurants by name, with `page=0` and `size=10` by default |
| `GET /restaurants/{id}` | Read a restaurant by ID |
| `POST /dishes` | Create a dish for an owned restaurant and validate its category |
| `GET /dishes/restaurant/{idRestaurant}` | List a restaurant’s active dishes with `page=0` and `size=10` by default |
| `GET /dishes/{id}` | Read a dish by ID, including its availability |
| `PUT /dishes/{id}`, `PATCH /dishes/{id}` | Change a dish’s price or description through the same handler |
| `PUT /dishes/{id}/active` | Set a dish’s active flag |
| `PATCH /dishes/{id}/status` | Toggle the active flag through the compatibility route |

Catalog changes use bearer JWTs. The dish use case checks the caller owns the restaurant. The current registration adapter targets `/users/{id}` on the Identity host while Identity exposes `/user-api/user/{id}`; keep this distinction when diagnosing registration. The portfolio demonstration uses a pre-seeded restaurant.

## Data store and environment

Spring Data JPA stores restaurants, categories, dishes, prices, and active flags in PostgreSQL. The development database is `food_court_db`, and the [Compose file](../docker-compose.yml) publishes PostgreSQL 16 on port `5432`.

| Name | Purpose | Default with a host-run API |
| --- | --- | --- |
| `SPRING_DATASOURCE_URL` | Catalog database JDBC URL | `jdbc:postgresql://localhost:5432/food_court_db` |
| `SPRING_DATASOURCE_USERNAME`, `SPRING_DATASOURCE_PASSWORD` | Database credentials | Local values in Spring config and Compose; supply private overrides for other environments |
| `JWT_SECRET` | Verifies Food Court bearer JWTs | Local development value; the verification configuration must match Identity’s signing configuration |
| `clients.user-service.url` | Spring property for the Feign owner lookup base URL | `http://localhost:8090`; Compose points it at the host instead |

`docker compose up -d foodcourt-postgres` starts only the database. `docker compose up -d` starts this repository’s API and database; it does not start Identity or fix the lookup route. The context test uses H2, not the development PostgreSQL container.

## Ports and adapters

Domain models and use cases define the menu and ownership rules and do not depend on Spring. Application handlers, DTOs, and mappers adapt requests to domain inputs. Infrastructure contains Spring MVC inputs, security, JPA, and Feign outputs.

| Domain output port | Adapter | Dependency |
| --- | --- | --- |
| `IRestaurantPersistencePort` | `RestaurantJpaAdapter` | Spring Data JPA / PostgreSQL |
| `IDishPersistencePort` | `DishJpaAdapter` | Spring Data JPA / PostgreSQL |
| `IUserClientPort` | `UserFeignAdapter` | Identity owner lookup over HTTP |

The [web app](https://github.com/diegnghtmr/food-court-ui) reads and manages menus; [Order](https://github.com/diegnghtmr/order-microservice) reads restaurant and dish details to validate checkout.

## System diagrams

- [System context](https://github.com/diegnghtmr/food-court-ui/blob/main/docs/project-architecture.png) — UI, services, and data stores.
- [SQL entities](https://github.com/diegnghtmr/food-court-ui/blob/main/docs/sql-entities-diagram.png) — historical relational design; current orders are not stored in PostgreSQL.
- [Document entities](https://github.com/diegnghtmr/food-court-ui/blob/main/docs/nosql-entities-diagram.png) — the order and trace document model.
- [Order lifecycle](https://github.com/diegnghtmr/food-court-ui/blob/main/docs/order-flow-diagram.png) — status transitions through pickup or cancellation.
