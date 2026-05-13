# Pet Service

<<<<<<< Updated upstream
Microservicio de gestión de mascotas para el proyecto Sanos y Salvos.
=======
Microservicio de gestión de mascotas para el proyecto **Sanos y Salvos**. Encargado de registrar, modificar y consultar información de mascotas reportadas como perdidas o encontradas.

## Objetivo

El Pet Service proporciona una API REST para la gestión del catálogo de mascotas del sistema. Permite crear registros de mascotas, asociar información de contacto, buscar por diferentes criterios y obtener estadísticas del estado de los registros.

## Arquitectura

### Componentes

- [PetController](src/main/java/com/sanosysalvos/petservice/controller/PetController.java): Endpoints REST principales para gestión de mascotas
- [PetReportController](src/main/java/com/sanosysalvos/petservice/controller/PetReportController.java): Endpoints para reportes de mascotas
- [PetService](src/main/java/com/sanosysalvos/petservice/service/PetService.java): Lógica de negocio para mascotas
- [PetFactory](src/main/java/com/sanosysalvos/petservice/service/PetFactory.java): Factory para creación de mascotas
- [PetRepository](src/main/java/com/sanosysalvos/petservice/repository/PetRepository.java): Repositorio JPA para mascotas
- [ContactRepository](src/main/java/com/sanosysalvos/petservice/repository/ContactRepository.java): Repositorio JPA para contactos
- [PetReportRepository](src/main/java/com/sanosysalvos/petservice/repository/PetReportRepository.java): Repositorio para reportes
- [Pet](src/main/java/com/sanosysalvos/petservice/model/Pet.java): Modelo de entidad mascota
- [Contact](src/main/java/com/sanosysalvos/petservice/model/Contact.java): Modelo de entidad contacto
- [PetReport](src/main/java/com/sanosysalvos/petservice/entity/PetReport.java): Entidad para reportes
- [PetReportFactory](src/main/java/com/sanosysalvos/petservice/factory/PetReportFactory.java): Factory para reportes

## Endpoints

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/pets` | Listar todas las mascotas |
| GET | `/api/pets/{id}` | Obtener mascota por ID |
| POST | `/api/pets` | Crear nueva mascota |
| POST | `/api/pets/with-contact` | Crear mascota con contacto |
| PUT | `/api/pets/{id}` | Actualizar mascota |
| DELETE | `/api/pets/{id}` | Eliminar mascota |
| GET | `/api/pets/search/race/{race}` | Buscar mascotas por raza |
| GET | `/api/pets/search/status/{status}` | Buscar mascotas por estado |
| GET | `/api/pets/search/color/{color}` | Buscar mascotas por color |
| GET | `/api/pets/totals/status` | Contar mascotas por estado |
| GET | `/` | Listar todos los reportes |
| POST | `/` | Crear nuevo reporte |
| GET | `/health` | Verificar estado del servicio |

## Tecnologías

- Java 17
- Spring Boot 3.1.2
- Spring Web (REST)
- Spring Data JPA
- Liquibase
- MySQL
- Maven

## Configuración

```properties
# Puerto del servicio
server.port=3001

# Base de datos
spring.datasource.url=jdbc:mysql://localhost:3306/pet_service
spring.datasource.username=root
spring.datasource.password=password

# Liquibase
spring.liquibase.enabled=true
```

## Instalación

```bash
mvn clean install
mvn spring-boot:run
```

## Pruebas

```bash
mvn test
```

## Notas

- El servicio utiliza el patrón Factory para la creación de reportes de mascotas.
- Implementa auditoría automática mediante @PrePersist y @PreUpdate.
- Soporta búsqueda por múltiples criterios: raza, estado, color.
- Proporcionaendpoint de salud para verificación del servicio.

---

## Despliegue en AWS EC2

### Arquitectura

| Instancia | Servicios | Tipo |
|-----------|-----------|------|
| Backend (t3.medium) | pet-service, geo-service, match-service | Backend Core |
| Edge (t3.small) | api-gateway, bff, frontend | Edge & UI |
| RDS (db.t3.micro) | 3 bases de datos | Database |

### Pre-requisitos

1. **RDS**: Crear las siguientes bases de datos:
   - `pet_service`
   - `geo_service`
   - `match_service`

2. **Instancias EC2**: 2 instancias con User Data:
   - Backend: ejecutar `scripts/userdata-backend.sh`
   - Edge: ejecutar `scripts/userdata-edge.sh`

### Configuración de GitHub Secrets

En cada repositorio, agregar en Settings > Secrets:

| Secret | Descripción |
|--------|-------------|
| `EC2_BACKEND_HOST` | IP pública instancia Backend |
| `EC2_EDGE_HOST` | IP pública instancia Edge |
| `EC2_USERNAME` | Usuario SSH (ubuntu) |
| `EC2_SSH_KEY` | Clave privada RSA |
| `DB_URL` | JDBC URL RDS (jdbc:mysql://...) |
| `DB_USER` | Usuario RDS |
| `DB_PASSWORD` | Password RDS |

### Puertos requeridos

**Security Group - Backend:**
- 22 (SSH)
- 3001 (pet-service)
- 3002 (geo-service)
- 3003 (match-service)

**Security Group - Edge:**
- 22 (SSH)
- 80 (frontend)
- 8080 (api-gateway)
- 8081 (bff)

### Despliegue automático

El deployment se ejecuta automáticamente al hacer push a la rama `main`:

1. **pet-service** dispara el deploy a instancia Backend
2. **api-gateway** dispara el deploy a instancia Edge

### Verificación

```bash
# Ver servicios en Backend
curl http://BACKEND_IP:3001/api/pets
curl http://BACKEND_IP:3002/api/locations
curl http://BACKEND_IP:3003/api/matching

# Ver servicios en Edge
curl http://EDGE_IP:8080/api/pets      # via gateway
curl http://EDGE_IP:80                 # frontend
```

### Scripts de configuración

Los scripts de User Data están disponibles en:
- `scripts/userdata-backend.sh`
- `scripts/userdata-edge.sh`
- `scripts/setup-rds.sh`
>>>>>>> Stashed changes
