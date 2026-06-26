# Pet Service

[![Docker](https://github.com/Axel-DaMage/fullstack-ss-pet-service/actions/workflows/docker.yml/badge.svg)](https://github.com/Axel-DaMage/fullstack-ss-pet-service/actions/workflows/docker.yml)
![Java](https://img.shields.io/badge/java-17-orange)
![Spring Boot](https://img.shields.io/badge/spring%20boot-3.1.2-brightgreen)

Microservicio para la gestion de mascotas. CRUD, busqueda y reportes de mascotas perdidas y encontradas.

## Stack

- Java 17, Spring Boot 3.1.2
- Spring Data JPA, Hibernate, Liquibase, MySQL
- Eureka Discovery Client
- Maven, JaCoCo
- Docker multi-stage

## Patrones de Diseno

| Patron | Tipo | Donde |
|--------|------|-------|
| **Factory Method** | GoF | `PetFactory` — crea mascotas con estado predefinido (PERDIDO/ENCONTRADO); `PetReportFactory` — crea reportes desde datos planos |
| **Singleton** | GoF | `AppConfig` — punto de acceso global a configuracion del servicio |
| **Template Method** | GoF | Entidades JPA con `@PrePersist`/`@PreUpdate` para timestamps automaticos |
| **DTO** | GoF | Desacopla entidades JPA de la representacion API |
| **Proxy** | Spring AOP | `@Transactional` para manejo de transacciones |

## Endpoints

| Metodo | Ruta | Descripcion |
|--------|------|-------------|
| GET | `/api/pets` | Listar todas las mascotas |
| GET | `/api/pets/{id}` | Obtener mascota por ID |
| POST | `/api/pets` | Crear mascota |
| POST | `/api/pets/with-contact` | Crear mascota con contacto |
| PUT | `/api/pets/{id}` | Actualizar mascota |
| DELETE | `/api/pets/{id}` | Eliminar mascota |
| GET | `/api/pets/search/race/{race}` | Buscar por raza |
| GET | `/api/pets/search/status/{status}` | Buscar por estado |
| GET | `/api/pets/search/color/{color}` | Buscar por color |
| GET | `/api/pets/totals/status` | Totales por estado |
| GET | `/health` | Health check |

## Base de Datos

MySQL `pet_service` con tablas: `pets`, `contacts`, `pet_report`. Migraciones Liquibase en XML.

**Entidades:**
- `Pet` → `pets` (id, name, race, color, size, status, description, timestamps) — `@ManyToOne` → Contact
- `Contact` → `contacts` (id, name, phone, email) — `@OneToMany` → Pet
- `PetReport` → `pet_report` (id, title, description, generatedAt)

## Pruebas

```bash
mvn clean test
mvn clean verify
```

32 tests en 3 archivos: `PetServiceTest`, `PetControllerTest`, `PetFactoryTest`.

## Docker

```bash
docker build -t d4mag3/pet-service .
docker run -p 3001:3001 d4mag3/pet-service
```

Imagen disponible en: `d4mag3/pet-service:latest`

## Variables de Entorno

| Variable | Default | Descripcion |
|----------|---------|-------------|
| `SERVER_PORT` | 3001 | Puerto del servicio |
| `DB_URL` | `jdbc:mysql://db-pet:3306/pet_service` | URL de base de datos |
| `DB_USER` | user | Usuario MySQL |
| `DB_PASSWORD` | password | Password MySQL |
| `EUREKA_URL` | `http://eureka-server:8761/eureka/` | URL de Eureka |
