# Pet Service

[![CI](https://github.com/Axel-DaMage/fullstack-ss-pet-service/actions/workflows/ci.yml/badge.svg)](https://github.com/Axel-DaMage/fullstack-ss-pet-service/actions/workflows/ci.yml)
[![Docker](https://github.com/Axel-DaMage/fullstack-ss-pet-service/actions/workflows/docker.yml/badge.svg)](https://github.com/Axel-DaMage/fullstack-ss-pet-service/actions/workflows/docker.yml)
![Java](https://img.shields.io/badge/java-17-orange)
![Spring Boot](https://img.shields.io/badge/spring%20boot-3.1.2-brightgreen)

Microservice for pet management. CRUD, search, and reports for lost and found pets.

## Stack

- Java 17, Spring Boot 3.1.2
- Spring Data JPA, Liquibase, MySQL
- Eureka Discovery Client
- Maven, JaCoCo

## Quick start

```bash
mvn clean install
mvn spring-boot:run
```

## Endpoints

| Method | Path | Description |
|--------|------|-------------|
| GET | `/api/pets` | List all |
| GET | `/api/pets/{id}` | Get by ID |
| POST | `/api/pets` | Create |
| POST | `/api/pets/with-contact` | Create with contact |
| PUT | `/api/pets/{id}` | Update |
| DELETE | `/api/pets/{id}` | Delete |
| GET | `/api/pets/search/race/{race}` | Search by race |
| GET | `/api/pets/search/status/{status}` | Search by status |
| GET | `/api/pets/search/color/{color}` | Search by color |
| GET | `/api/pets/totals/status` | Count by status |
| GET | `/health` | Health check |

## Tests

```bash
mvn test
mvn clean verify
```

## Database

MySQL `pet_service` with tables: `pets`, `contacts`, `pet_report`. Managed via Liquibase changelogs.
