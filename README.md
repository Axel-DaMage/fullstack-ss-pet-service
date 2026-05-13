# Pet Service

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