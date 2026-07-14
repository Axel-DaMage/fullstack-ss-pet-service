# Guía de Presentación Profesional  Sanos y Salvos

## Estructura estándar (usada en empresas)

Toda presentación técnica profesional sigue el orden: **Contexto -> Problema -> Solución -> Demo -> Resultados**.

---

## ANEXO: Evaluación Final Transversal  Rúbrica completa (EFT126_DSY1106_ESTUDIANTE)

### Estructura de la nota

| Componente | % | Tipo |
|---|---|---|
| **Informe retrospectivo grupal** | 30% | Grupal (6 indicadores × 5% c/u) |
| **Defensa oral** | 70% | **Individual** (7 indicadores × 10% c/u) |
| **Total** | **100%** | |

> **Clave:** La defensa oral es **individual** y vale **70%**. Cada estudiante debe poder responder cualquier pregunta. El profesor evaluará a cada uno por separado.

### Dimensión 1: Informe (30% grupal)

| # | Indicador | % | ¿Qué evaluar? |
|---|---|---|---|
| 1 | Diseño de arquitectura de microservicios  cómo responde a necesidades del cliente, principios de ética, sostenibilidad y responsabilidad | 5% | Justificar con ejemplos concretos por qué microservicios, cómo cumple con privacidad de datos, seguridad, escalabilidad |
| 2 | Decisiones en desarrollo backend y frontend  reflexión retrospectiva | 5% | Aciertos, dificultades, mejoras. ¿Por qué React? ¿Por qué Spring Boot? ¿Qué cambiarían? |
| 3 | Aplicación de patrones de diseño  efectividad en la solución | 5% | Evaluar con ejemplos: Factory, Strategy, Observer, Circuit Breaker. ¿Funcionaron? ¿Alternativas? |
| 4 | Estrategia de branching y gestión de versiones | 5% | Git Flow, Conventional Commits, cómo organizó el equipo, PRs, code review |
| 5 | Integración backend, frontend y base de datos | 5% | Desafíos de integración, comunicación REST, JPA, cómo aseguraron cohesión |
| 6 | Pruebas unitarias y aseguramiento de calidad | 5% | Cobertura, casos de prueba, resultados, cómo impactó en mantenibilidad |

### Dimensión 2: Defensa Oral (70% individual)

| # | Indicador | % | Pregunta tipo del profesor | Cómo responder |
|---|---|---|---|---|
| 7 | Seguridad, privacidad, sostenibilidad y ética | 10% | *"¿Cómo garantizan la seguridad y privacidad de los datos en su solución?"* | Mencionar: JWT en API Gateway, CORS configurado, microservicios no expuestos, contraseñas hasheadas (si aplica), volúmenes Docker para persistencia, principios de responsabilidad en manejo de datos de mascotas |
| 8 | Adaptabilidad y mantenibilidad | 10% | *"¿Cómo su solución se adapta a cambios futuros?"* | Mencionar: patrones de diseño (Strategy permite cambiar algoritmo sin modificar código existente), Factory desacopla creación, microservicios independientes, DTO desacopla entidades de la API |
| 9 | Capacidad adaptativa con ejemplos de código | 10% | *"Muéstreme en el código cómo su solución es adaptable"* | Mostrar: `Strategy` (MatchingService  dos algoritmos intercambiables), `Factory` (PetFactory  crear lost/found sin repetir código), `DTO` (PetDto  cambiar entidad sin afectar frontend) |
| 10 | Arquetipos y patrones arquitectónicos | 10% | *"¿Qué arquetipos Maven usaron y qué patrones arquitectónicos aplicaron?"* | Mencionar: arquetipos `microservice` y `bff` (Maven archetypes), patrones arquitectónicos: Microservices, API Gateway, BFF/Aggregation, Database per Service |
| 11 | Estrategia de branching y organización del equipo | 10% | *"¿Cómo organizaron el trabajo en equipo con Git?"* | Explicar: Git Flow adaptado (main → develop → features), Conventional Commits (`feat:`, `fix:`, `chore:`), PRs con code review, 3 personas con repos asignados |
| 12 | Escalabilidad y funcionalidad de microservicios | 10% | *"¿Cómo escala su solución si crece la demanda?"* | Mencionar: microservicios independientes escalan horizontalmente, API Gateway con balanceo de carga (Eureka / Docker DNS), BD separadas sin contención, stateless services, Docker Compose para orquestación |
| 13 | Pruebas unitarias y aseguramiento de calidad | 10% | *"¿Qué resultados obtuvieron en las pruebas? ¿Cómo aseguran calidad?"* | Mostrar: número de tests por componente, cobertura JaCoCo ≥60%, ejemplos de test con Mockito, cómo las pruebas previenen regresiones |

### Niveles de logro por indicador

| Nivel | % | Descripción |
|---|---|---|
| Muy buen desempeño | 100% | Demuestra dominio destacado, ejemplos concretos, lenguaje técnico preciso |
| Buen desempeño | 80% | Alto desempeño con pequeñas omisiones o errores menores |
| Desempeño aceptable | 60% | Competente pero con omisiones notables, falta profundidad |
| Desempeño incipiente | 30% | Importantes omisiones, no demuestra los elementos básicos |
| No logrado | 0% | Ausencia de respuesta o respuesta incorrecta |

### Estrategia por indicador  qué decir exactamente

#### Indicador 7  Seguridad, privacidad, sostenibilidad (10%)

**Qué decir:** "La seguridad se maneja en 3 capas:
1. **API Gateway**  Filtro JWT que valida tokens antes de que cualquier request llegue a los microservicios. Sin token válido, el request es rechazado.
2. **CORS**  Configurado en el Gateway para aceptar solo orígenes conocidos.
3. **Aislamiento**  Los microservicios no tienen IP pública. Solo el Gateway es accesible desde fuera. Las BD tienen sus propios puertos internos en Docker.
En cuanto a privacidad de datos, solo se almacena información necesaria para contactar al dueño (nombre, teléfono, email). No hay datos sensibles. Sostenibilidad: la arquitectura de microservicios permite escalar solo los servicios que lo necesitan, optimizando recursos."

#### Indicador 8  Adaptabilidad y mantenibilidad (10%)

**Qué decir:** "El sistema es adaptable por varias razones:
- **Patrón Strategy** en Match Service: podemos agregar un nuevo algoritmo de matching (ej: por ubicación geográfica) sin modificar los existentes. Solo creamos un nuevo método.
- **Patrón Factory** en Pet Service: si agregamos un nuevo tipo de reporte (ej: avistamiento), solo agregamos un método en la fábrica.
- **DTO Pattern**: el frontend recibe solo los datos que necesita. Si la entidad JPA cambia, el DTO no cambia.
- **Microservicios**: cada servicio es independiente. Podemos actualizar pet-service sin tocar geo-service ni match-service."

#### Indicador 9  Ejemplos de código (10%)

**Mostrar en vivo:**
- `MatchingService.java`: los dos métodos `calculateMatch()` (ponderado) y `calculateSimpleMatch()` (simple)
- `PetFactory.java`: `createLostPet()` vs `createFoundPet()`
- `PetDto.java`: solo campos name, race, color, size, status  sin createdAt, sin relaciones JPA
- `EventEmitter.ts`: `on()` y `emit()`  mostrar suscripción en `App.tsx`

#### Indicador 10  Arquetipos y patrones arquitectónicos (10%)

**Qué decir:**
- **Arquetipos Maven**: Creamos 2 arquetipos: `microservice` (para pet, geo, match service) y `bff` (para el BFF). Esto nos permitió generar la estructura base de cada proyecto con un solo comando `mvn archetype:generate`, asegurando consistencia entre servicios.
- **Patrones arquitectónicos**: **Microservices** (cada dominio tiene su servicio y BD), **API Gateway** (punto único de entrada), **BFF / Aggregation** (capa que consolida datos de múltiples servicios), **Database per Service** (cada microservicio tiene su propia BD MySQL).

#### Indicador 11  Branching y organización (10%)

**Qué decir:** "Usamos Git Flow adaptado:
- `main` → producción
- `develop` → integración
- `feature/*` → funcionalidades nuevas
- `fix/*` → correcciones
- `chore/*` → documentación y tareas menores

Cada integrante trabajó en sus repositorios asignados. Todo cambio pasaba por **Pull Request con code review**. Usamos **Conventional Commits** (`feat:`, `fix:`, `chore:`) para mantener el historial limpio. Esto nos permitió trabajar en paralelo sin conflictos."

#### Indicador 12  Escalabilidad y funcionalidad (10%)

**Qué decir:** "La solución escala horizontalmente:
1. **Microservicios stateless**  Podemos ejecutar múltiples instancias de pet-service detrás del Gateway.
2. **BD separadas**  Cada servicio tiene su propia BD. Si el tráfico de mascotas crece, escalamos solo pet-service y su BD.
3. **Contenerizado**  Docker + Docker Compose permite levantar/derribar instancias rápido.
4. **Funcionalidad completa**  Cada servicio expone CRUD + endpoints de búsqueda + reportes. El BFF orquesta los datos para el frontend."

#### Indicador 13  Pruebas unitarias (10%)

**Qué decir:** "Tenemos 125+ tests distribuidos en 18 archivos a través de los 6 componentes. Usamos **JUnit 5 + Mockito** para backend y **Vitest** para frontend. Las pruebas se enfocan en:
- **Lógica de negocio**: algoritmo de matching, creación de mascotas, cálculo de dashboard
- **Controladores**: verificación de status codes HTTP, estructura JSON de respuesta
- **Clients REST**: simulación de llamadas entre servicios con Mockito
- **Casos borde**: mascota no encontrada (404), creación con datos inválidos (400)

Reportes de cobertura con **JaCoCo** generan métricas por componente, apuntando a ≥60% de cobertura."

---

### Slide por slide (15 min total)

---

## Slide 1  Portada (30 seg)

- Logo del proyecto
- Nombre: "Sanos y Salvos  Plataforma de Recuperación de Mascotas"
- Integrantes
- "Evaluación Parcial 3  DSY1106 Desarrollo Fullstack III"

---

## Slide 2  Contexto (1 min)

**Qué decir:**
"Somos Sanos y Salvos, una plataforma que conecta mascotas perdidas con sus dueños. Permitimos reportar mascotas perdidas y encontradas, geolocalizarlas en un mapa, y encontrar coincidencias mediante un algoritmo de similitud."

**Qué mostrar:**
- Descripción del proyecto en 1-2 líneas
- Imagen conceptual (mascota + mapa)

---

## Slide 3  Problema (1 min)

**Qué decir:**
"Sin nuestra solución, reportar una mascota perdida implica publicar en redes sociales sin coordinación, sin un registro centralizado y sin forma de cruzar datos. Un enfoque monolítico tradicional sería difícil de escalar y mantener."

**Qué mostrar:**
- 3-4 problemas con iconos (ej: "Sin registro centralizado", "Sin búsqueda automatizada", "Datos aislados")

---

## Slide 4  Arquitectura General (2 min)

**Qué decir:**
"Implementamos una arquitectura de microservicios con 6 componentes independientes."

**Qué mostrar:**
- Diagrama de arquitectura completo (Diagrama_Arquitectura.png)
- Señalar cada componente mientras lo mencionas

**Componentes:**
- Frontend (React + TypeScript + Nginx :80)
- API Gateway (Spring Cloud Gateway + JWT + Circuit Breaker :8080)
- BFF (Spring Boot + RestTemplate :8081)
- Pet Service (Spring Boot + JPA :3001)
- Geo Service (Spring Boot + JPA :3002)
- Match Service (Spring Boot + JPA + Strategy :3003)
- MySQL x3 (una por servicio :3306/3307/3308)

**Flujo:**
Usuario -> Frontend -> API Gateway -> BFF / MS -> MySQL

**Recursos de apoyo:**
- [Microservices Architecture  AWS](https://aws.amazon.com/microservices/)
- [Microservices Guide  Martin Fowler](https://martinfowler.com/microservices/)
- [Video: Microservices explained (IBM, 5 min)](https://www.youtube.com/watch?v=qYh6U1hRZ0s)
- [Video: Spring Cloud Gateway (Java Brains, 20 min)](https://www.youtube.com/watch?v=6NUoVOg4NbA)
- [Documentación oficial Spring Cloud Gateway](https://cloud.spring.io/spring-cloud-gateway/reference/html/)

---

## Slide 5  Decisiones Técnicas (2 min)

**Qué decir (pregunta/respuesta para cada tecnología):**

| Tecnología | Respuesta |
|---|---|
| ¿Por qué microservicios? | Cada dominio es independiente (mascotas, ubicaciones, coincidencias). Escalabilidad, despliegue y equipo separados |
| ¿Por qué React? | Ecosistema maduro, Vite rápido, TypeScript seguro, componentes reutilizables |
| ¿Por qué Spring Boot? | Production-ready, JPA nativo, Cloud Gateway, Resilience4j, comunidad enorme |
| ¿Por qué MySQL? | Datos relacionales, ACID, claves foráneas, consistencia fuerte (mascotas no pueden perderse) |
| ¿Por qué Docker? | Consistencia entornos, portabilidad, CI/CD automatizado |
| ¿Por qué BFF? | El frontend hace 1 llamada en vez de 3-4. Desacopla el frontend de los microservicios internos |
| ¿Por qué 3 BD separadas? | Cada microservicio tiene su propio contexto de datos. Si cae geo-service, pet-service sigue funcionando |
| ¿Por qué Liquibase? | Versionado de esquemas de BD. Los cambios se aplican automáticamente al deployar |

**Qué mostrar:**
- Tabla con logos: React, TypeScript, Spring Boot, MySQL, Docker, AWS

**Recursos de apoyo:**
- [React + TypeScript + Vite (Documentación oficial)](https://vitejs.dev/guide/)
- [Spring Boot  Documentación oficial](https://spring.io/projects/spring-boot)
- [Spring Cloud Gateway  Baeldung](https://www.baeldung.com/spring-cloud-gateway)
- [Liquibase  Documentación oficial](https://docs.liquibase.com/)
- [Video: Docker en 10 minutos](https://www.youtube.com/watch?v=_dfLOzuIg2o)
- [Video: ¿Qué es BFF? (Backend For Frontend)](https://www.youtube.com/watch?v=Ua_Z0l2I9_0)
- [Resilience4j Circuit Breaker  Baeldung](https://www.baeldung.com/spring-boot-resilience4j)

---

## Slide 6  Patrones de Diseño  Código (3 min)

**Qué mostrar:** Código real en VS Code o captura de pantalla con línea resaltada.

**Importante:** Menciona que Spring ya implementa Singleton, Factory, Proxy y Repository por defecto. Lo que hicimos fue usarlos explícitamente.

### Resumen de patrones implementados

| # | Patrón | Tipo | Servicio | Archivo clave |
|---|---|---|---|---|
| 1 | **Factory Method** | Creacional | Pet Service | `PetFactory.java:19-29` |
| 2 | **Strategy** | Comportamiento | Match Service | `MatchingService.java:71-174` |
| 3 | **Singleton** | Creacional | Pet, Geo, Match | `AppConfig.java:41` |
| 4 | **Template Method** | Comportamiento | Pet, Geo, Match | `Pet.java:47-56` |
| 5 | **Observer** | Comportamiento | Frontend | `EventEmitter.ts:3-20` |
| 6 | **Circuit Breaker** | Resiliencia | API Gateway + Match | `application.yml:85-116`, `PetServiceConsumer.java:22-30` |
| 7 | **Proxy** | Estructural | Pet, Match Service | `PetService.java:38` |
| 8 | **DTO** | Estructural | BFF | `PetDto.java:3` |
| 9 | **API Gateway** | Arquitectónico | API Gateway | `application.yml:15-55` |
| 10 | **BFF / Aggregation** | Arquitectónico | BFF | `AggregationService.java:116-137` |
| 11 | **Repository** | Persistencia | Pet, Geo, Match | `PetRepository.java:9-19` |

### Factory Method  `PetFactory.java` `:19-29`

** ¿En qué consiste?** Patrón creacional que define una interfaz para crear objetos, pero permite a las subclases decidir qué clase instanciar. En nuestro caso, encapsulamos la lógica de creación de mascotas (perdidas vs encontradas) en métodos específicos dentro de una fábrica. El Factory Method centraliza las reglas de negocio de inicialización (ej: asignar automáticamente el estado "PERDIDO" o "ENCONTRADO") y evita que el código cliente tenga que conocer los detalles de construcción del objeto.

** ¿Por qué lo seleccionamos?** Porque la creación de mascotas tiene reglas de negocio específicas: al crear una mascota perdida, el status debe ser "PERDIDO" automáticamente; al crear una encontrada, "ENCONTRADO". Sin Factory, cada controlador tendría que recordar asignar el status manualmente, aumentando errores y duplicación. Además, cumple el **principio Open/Closed**: si mañana agregamos "AVISTAMIENTO" como nuevo tipo, solo agregamos un método en la fábrica sin modificar los controladores existentes.

**Ubicación:** `fullstack-ss-pet-service/src/main/java/.../service/PetFactory.java:19-29`

```java
public Pet createLostPet(String name, String race, String color, String size) { // :19
    Pet pet = new Pet();
    pet.setName(name);
    pet.setRace(race);
    pet.setColor(color);
    pet.setSize(size);
    pet.setStatus("PERDIDO");                                                     // :23
    return pet;
}

public Pet createFoundPet(String name, String race, String color, String size) { // :25
    Pet pet = createPet(name, race, color, size, "ENCONTRADO");
    return pet;                                                                   // :29
}
```

**Decir:** "Centraliza la creación. Cada vez que alguien crea una mascota perdida, no tiene que acordarse de poner status=PERDIDO. El Factory lo hace. createLostPet línea 19, createFoundPet línea 25. También existe PetReportFactory para crear reportes desde instituciones."

**Recursos:**
- [Video: Factory Pattern (Programming with Mosh)](https://www.youtube.com/watch?v=EcFVTgRHJLM)
- [Video: Factory Method en Java (Código Facilito)](https://www.youtube.com/watch?v=Jk3o0qLmT9g)
- [Refactoring Guru — Factory Method](https://refactoring.guru/design-patterns/factory-method)
- [Baeldung — Factory Method en Spring](https://www.baeldung.com/creational-design-patterns#factory-method)
- [SourceMaking — Factory Method](https://sourcemaking.com/design_patterns/factory_method)

---

### Strategy  `MatchingService.java` `:71-174`

** ¿En qué consiste?** Patrón de comportamiento que define una familia de algoritmos intercambiables y los encapsula detrás de una interfaz común. Permite que el algoritmo varíe independientemente de los clientes que lo usan. En nuestro caso, tenemos dos algoritmos de matching: `calculateMatch()` (ponderado: raza pesa 40%, color 30%, tamaño 30%) y `calculateSimpleMatch()` (simple: cada atributo pesa 33.3%). Ambos reciben los mismos parámetros pero calculan el porcentaje de coincidencia de forma diferente.

** ¿Por qué lo seleccionamos?** Porque el negocio de matching de mascotas puede requerir diferentes estrategias de comparación según el contexto. Un refugio puede preferir el algoritmo ponderado (la raza es más importante), mientras que una búsqueda rápida puede usar el simple. El patrón Strategy permite intercambiar algoritmos sin modificar el código existente, cumpliendo **Open/Closed**. Además, si en el futuro queremos agregar un nuevo criterio (ej: ubicación geográfica, fecha de extravío), creamos un nuevo método sin tocar los existentes. También facilita las pruebas: podemos testear cada estrategia por separado.

**Ubicación:** `fullstack-ss-match-service/src/main/java/.../service/MatchingService.java:71-174`

```java
// Estrategia ponderada (:71-102)
public void calculateMatch(Match match, PetDto lost, PetDto found) {    // :71
    double score = 0;
    if (lost.getRace().equalsIgnoreCase(found.getRace())) score += 40;  // :75
    if (lost.getColor().equalsIgnoreCase(found.getColor())) score += 30;// :76
    if (lost.getSize().equalsIgnoreCase(found.getSize())) score += 30;  // :77
    match.setPorcentajeCoincidencia(score);                              // :78
}

// Estrategia simple (:154-174)
public void calculateSimpleMatch(Match match, PetDto lost, PetDto found) { // :154
    int matches = 0;
    if (lost.getRace().equalsIgnoreCase(found.getRace())) matches++;     // :156
    if (lost.getColor().equalsIgnoreCase(found.getColor())) matches++;   // :157
    if (lost.getSize().equalsIgnoreCase(found.getSize())) matches++;     // :158
    double score = (matches / 3.0) * 100;                                // :159
    match.setPorcentajeCoincidencia(score);
}
```

**Decir:** "Dos algoritmos encapsulados e intercambiables. El ponderado da más peso a la raza (40%). El simple da el mismo peso a todo (33.3%). Si mañana queremos agregar ubicación como criterio, agregamos otro método sin modificar los existentes. Esto cumple el principio Open/Closed de SOLID."

**Recursos:**
- [Video: Strategy Pattern (Programming with Mosh)](https://www.youtube.com/watch?v=v9ejT8FO-7I)
- [Video: Strategy Pattern en Java (Geekific)](https://www.youtube.com/watch?v=Nrwj3gZiuJU)
- [Refactoring Guru — Strategy](https://refactoring.guru/design-patterns/strategy)
- [Baeldung — Strategy Pattern en Spring](https://www.baeldung.com/strategy-pattern)
- [SourceMaking — Strategy](https://sourcemaking.com/design_patterns/strategy)

---



### Singleton  `AppConfig.java` `:41`

** ¿En qué consiste?** Patrón creacional que asegura que una clase tenga exactamente una única instancia en toda la aplicación y proporciona un punto de acceso global a ella. En nuestro caso, `AppConfig` se implementa como un bean de Spring (que por defecto es singleton) y adicionalmente expone la instancia estáticamente mediante `getInstance()` para acceder a propiedades de configuración desde clases no gestionadas por el contenedor de Spring.

** ¿Por qué lo seleccionamos?** Porque la configuración de cada microservicio (URLs de bases de datos, puertos, timeouts) debe ser consistente en toda la aplicación. Tener una única instancia de `AppConfig` evita cargar múltiples copias de configuración en memoria y garantiza que todos los componentes lean las mismas propiedades. Aunque Spring ya maneja sus beans como singleton por defecto, la exposición estática permite acceder a la configuración desde clases JPA o utilidades que no están bajo el control de Spring. Se implementó en los 3 microservicios.

**Ubicación:** `fullstack-ss-pet-service/src/main/java/.../config/AppConfig.java:41` (idéntico en geo-service `:41` y match-service `:47`)

```java
@Component
public class AppConfig {
    private static AppConfig instance;             // :7

    @PostConstruct                                  // :36
    public void init() {
        instance = this;                            // :38
    }

    public static AppConfig getInstance() {         // :41
        return instance;
    }
}
```

**Decir:** "Punto de acceso global a configuración. Se implementa en pet-service (:41), geo-service (:41) y match-service (:47). Cada servicio tiene sus propias propiedades. En realidad Spring Boot ya maneja sus beans como Singleton por defecto; esta implementación expone el bean estáticamente para accederlo desde contextos no gestionados por Spring."

**Recursos:**
- [Video: Singleton Pattern explicado](https://www.youtube.com/watch?v=NZaXM67H2ak)
- [Video: Patrón Singleton en Java (Geekific)](https://www.youtube.com/watch?v=6zQ-HERhLm0)
- [Refactoring Guru — Singleton](https://refactoring.guru/design-patterns/singleton)
- [Documentación Spring — Bean Scopes](https://docs.spring.io/spring-framework/reference/core/beans/factory-scopes.html)
- [Baeldung — Singleton en Spring](https://www.baeldung.com/spring-bean-scopes#singleton)

---

### Template Method  `Pet.java:47-56`, `Location.java:55-64`, `Match.java:37-43`

** ¿En qué consiste?** Patrón de comportamiento que define el esqueleto de un algoritmo en un método, delegando algunos pasos a las subclases. En nuestro caso, JPA actúa como el "template": define el ciclo de vida de las entidades (persistir, actualizar, eliminar) y nosotros implementamos los hooks `@PrePersist` y `@PreUpdate` para inyectar comportamiento en momentos específicos. El "algoritmo" de JPA (abrir conexión, ejecutar SQL, cerrar conexión) no se modifica, pero nosotros personalizamos los pasos intermedios.

** ¿Por qué lo seleccionamos?** Porque necesitamos que **todas** las entidades del sistema tengan timestamps `createdAt` y `updatedAt` sin excepción. Usar `@PrePersist`/`@PreUpdate` garantiza que cada vez que se guarde o actualice una entidad, los timestamps se asignen automáticamente, sin que el desarrollador tenga que acordarse de hacerlo manualmente en cada servicio. Esto elimina errores de olvido y asegura consistencia. Aplicamos el mismo patrón en Pet.java (:47-56), Location.java (:55-64) y Match.java (:37-43)  las 3 entidades principales del sistema.

**Ubicaciones:**
- `fullstack-ss-pet-service/src/main/java/.../model/Pet.java:47` (@PrePersist), `:53` (@PreUpdate)
- `fullstack-ss-geo-service/src/main/java/.../model/Location.java:55` (@PrePersist), `:64` (@PreUpdate)
- `fullstack-ss-match-service/src/main/java/.../model/Match.java:37` (@PrePersist), `:43` (@PreUpdate)

```java
@PrePersist                                              // Pet.java :47
protected void onCreate() {
    this.createdAt = LocalDateTime.now();                // :49
    this.updatedAt = LocalDateTime.now();                // :50
}

@PreUpdate                                               // Pet.java :53
protected void onUpdate() {
    this.updatedAt = LocalDateTime.now();                // :55
}
```

**Decir:** "JPA llama automáticamente @PrePersist antes de guardar y @PreUpdate antes de actualizar. Asignamos timestamps sin tener que hacerlo manualmente en cada servicio. Se repite en las 3 entidades principales: Pet (:47-56), Location (:55-64) y Match (:37-43). Es Template Method porque JPA define el 'esqueleto' del ciclo de vida y nosotros implementamos los hooks."

**Recursos:**
- [Video: Template Method Pattern explicado](https://www.youtube.com/watch?v=aR1B8MlzbRI)
- [Video: JPA Lifecycle Events (Java Guides)](https://www.youtube.com/watch?v=wPnZP5Q1p_Q)
- [Refactoring Guru — Template Method](https://refactoring.guru/design-patterns/template-method)
- [Baeldung — JPA Lifecycle Events](https://www.baeldung.com/jpa-entity-lifecycle-events)
- [SourceMaking — Template Method](https://sourcemaking.com/design_patterns/template_method)

---

### Observer  `EventEmitter.ts:3-20` + `events.ts`

** ¿En qué consiste?** Patrón de comportamiento que define una dependencia uno-a-muchos entre objetos, de modo que cuando un objeto cambia su estado, todos los dependientes son notificados automáticamente. En nuestro caso, implementamos un `EventEmitter` personalizado en TypeScript: los componentes pueden suscribirse a eventos (`on()`) y el emisor puede notificarles cuando algo ocurre (`emit()`). Los eventos siguen una convención namespaced (`pet:created`, `match:updated`) para evitar colisiones.

** ¿Por qué lo seleccionamos?** Porque el frontend de React necesita mantener sincronizadas múltiples vistas (Dashboard, lista de mascotas, lista de matches) cuando ocurren cambios. Por ejemplo, cuando el formulario crea una mascota, se emite `pet:created` y tanto el Dashboard (para actualizar el contador) como la lista de mascotas (para mostrar la nueva) se actualizan automáticamente. Sin Observer, tendríamos que pasar props complejas por múltiples niveles de componentes o usar una biblioteca externa de state management. Esta implementación es liviana, sin dependencias externas, y sigue el principio de **bajo acoplamiento**: los emisores no conocen a los suscriptores.

**Ubicación:** `fullstack-ss/frontend-components/src/lib/EventEmitter.ts:3` (clase), `:6` (on), `:18` (emit)
**Eventos:** `fullstack-ss/frontend-components/src/lib/events.ts`

```typescript
// EventEmitter.ts
class EventEmitter {
  private events: Map<string, Set<EventCallback>> = new Map();  // :3

  on<T = unknown>(event: string, callback: EventCallback<T>): () => void {  // :6
    if (!this.events.has(event)) {
      this.events.set(event, new Set());
    }
    this.events.get(event)!.add(callback as EventCallback);
    return () => this.off(event, callback);                       // :12
  }

  emit<T = unknown>(event: string, data: T): void {              // :18
    this.events.get(event)?.forEach(callback => callback(data)); // :19
  }
}

// events.ts  eventos namespaced
export const Events = {
  PET_CREATED: 'pet:created',
  PET_UPDATED: 'pet:updated',
  PET_DELETED: 'pet:deleted',
  MATCH_CREATED: 'match:created',
  MATCH_UPDATED: 'match:updated',
  SECTION_CHANGED: 'app:section-changed',
};
```

**Decir:** "Cuando el formulario guarda una mascota, emite el evento 'pet:created'. El Dashboard y la lista de mascotas están suscritos y se actualizan automáticamente. Sin recargar la página, sin props complejas. Usamos eventos namespaced (pet:created, match:updated) para evitar colisiones."

**Recursos:**
- [Video: Observer Pattern (Web Dev Simplified)](https://www.youtube.com/watch?v=3VhXSPRz_6c)
- [Video: Observer Pattern en TypeScript](https://www.youtube.com/watch?v=PrPv7KqB9mE)
- [Refactoring Guru — Observer](https://refactoring.guru/design-patterns/observer)
- [Documentación — Patrón Observer en JS/TS](https://www.patterns.dev/vanilla/observer-pattern/)
- [SourceMaking — Observer](https://sourcemaking.com/design_patterns/observer)

---

### Circuit Breaker  `application.yml:85-116` + `PetServiceConsumer.java:22-30`

** ¿En qué consiste?** Patrón de resiliencia que protege a un sistema de fallos en cascada. Funciona como un interruptor eléctrico: mientras las llamadas al servicio remoto son exitosas, el circuito permanece **cerrado**. Cuando el número de fallos supera un umbral (ej: 50% en una ventana de 10 llamadas), el circuito se **abre** y las llamadas fallan inmediatamente sin intentar la conexión real. Después de un tiempo de espera (5s), pasa a **half-open** y permite una llamada de prueba. Si esa llamada tiene éxito, el circuito se **cierra** nuevamente.

** ¿Por qué lo seleccionamos?** Porque el Match Service depende del Pet Service y Geo Service para obtener datos. Si Pet Service se cae (por fallo de BD, red, etc.), y Match Service sigue intentando llamarlo, se saturarán los hilos de conexión y Match Service también caerá  un **fallo en cascada** típico en microservicios. Con Circuit Breaker, si Pet Service falla 5 de 10 requests, el circuito se abre y Match Service devuelve una respuesta vacía graceful (fallback) sin esperar. Esto permite que el sistema **degrade parcialmente** (el matching no funciona, pero el resto del sistema sigue operativo) en lugar de colapsar completamente.

**Ubicaciones:**
- `fullstack-ss-api-gateway/src/main/resources/application.yml:85-116` (config Resilience4j)
- `fullstack-ss-match-service/src/main/java/.../service/PetServiceConsumer.java:22` (@CircuitBreaker), `:27` (fallback)

```yaml
# application.yml  configuración (:85-116)
resilience4j.circuitbreaker:
  instances:
    petServiceCircuitBreaker:
      slidingWindowSize: 10      # :88
      failureRateThreshold: 50   # :89
      waitDurationInOpenState: 5s # :90
```

```java
// PetServiceConsumer.java
@CircuitBreaker(name = "petServiceBreaker", fallbackMethod = "petServiceFallback")  // :22
public List<PetReport> getPetReports() { ... }

public List<PetReport> petServiceFallback(Exception e) {                            // :27
    return List.of(); // respuesta graceful                                         // :28
}
```

**Decir:** "Si pet-service falla 5 de 10 requests (50%), el circuito se abre. Durante 5 segundos no se hacen llamadas, se devuelve lista vacía como fallback. Después de 5s se prueba 1 request (Half-Open). Si funciona, el circuito vuelve a cerrarse. Esto evita fallos en cascada."

**Recursos:**
- [Video: Circuit Breaker Pattern explicado](https://www.youtube.com/watch?v=2GR3o4J0-d4)
- [Video: Resilience4j Circuit Breaker (Java Techie)](https://www.youtube.com/watch?v=71k7D-hvCxE)
- [Resilience4j — Documentación oficial](https://resilience4j.readme.io/docs/circuitbreaker)
- [Baeldung — Circuit Breaker con Resilience4j](https://www.baeldung.com/spring-boot-resilience4j)
- [Martin Fowler — Circuit Breaker](https://martinfowler.com/bliki/CircuitBreaker.html)

---

### Proxy  `@Transactional` en `PetService.java:38-44` y `MatchingService.java:43`

** ¿En qué consiste?** Patrón estructural que proporciona un sustituto o intermediario (proxy) de otro objeto para controlar el acceso a él. En Spring, `@Transactional` genera automáticamente un proxy AOP que envuelve el método: antes de ejecutarlo, abre una transacción; si el método se completa exitosamente, hace commit; si lanza una excepción, hace rollback. El desarrollador escribe solo la lógica de negocio y el proxy maneja la gestión transaccional.

** ¿Por qué lo seleccionamos?** Porque varias operaciones en el sistema afectan múltiples tablas. Por ejemplo, `createPetWithContact()` guarda un contacto (`contactRepository.save()`) y luego una mascota (`petRepository.save()`). Si la segunda operación falla (ej: restricción de base de datos), la primera quedaría persistida  datos huérfanos. `@Transactional` asegura que ambas operaciones sean **atómicas**: si alguna falla, todas se deshacen (ROLLBACK). Esto garantiza la consistencia ACID. Además, Spring maneja el proxy automáticamente: solo agregamos la anotación.

**Ubicaciones:**
- `fullstack-ss-pet-service/src/main/java/.../service/PetService.java:33,38,45,61` (createPet, createPetWithContact, updatePet, deletePet)
- `fullstack-ss-match-service/src/main/java/.../service/MatchingService.java:43,104,112` (createMatch, updateMatchStatus, deleteMatch)

```java
@Transactional                                              // PetService.java :38
public Pet createPetWithContact(Pet pet, Contact contact) {
    contact = contactRepository.save(contact);               // :40
    pet.setContact(contact);
    return petRepository.save(pet);                          // :42  si falla → ROLLBACK
}
```

**Decir:** "Spring crea un proxy AOP alrededor de este método. Si la segunda sentencia falla, la primera se deshace automáticamente (ROLLBACK). Sin esto, el contacto quedaría guardado sin mascota. ACID en acción. Está en PetService.java (:33, :38, :45, :61) y MatchingService.java (:43, :104, :112)."

**Recursos:**
- [Video: Spring AOP Proxy explicado](https://www.youtube.com/watch?v=5NFnCq2owKY)
- [Video: @Transactional en Spring (Java Brains)](https://www.youtube.com/watch?v=9S2kS5WMRPM)
- [Documentación Spring — Transaction Management](https://docs.spring.io/spring-framework/reference/data-access/transaction.html)
- [Baeldung — @Transactional en Spring](https://www.baeldung.com/transaction-configuration-with-jpa-and-spring)
- [Baeldung — Spring AOP Proxy](https://www.baeldung.com/spring-aop)

---

### DTO  `PetDto.java` (BFF :3)

** ¿En qué consiste?** Patrón estructural (también llamado Data Transfer Object) que transporta datos entre subsistemas sin exponer los detalles internos de las entidades. Un DTO es un objeto simple con solo campos y getters/setters, sin lógica de negocio ni anotaciones JPA. Actúa como un contrato entre capas: la entidad JPA puede tener 20 campos, pero el DTO solo expone los 6 que el frontend necesita.

** ¿Por qué lo seleccionamos?** Por dos razones fundamentales: **seguridad** y **desacoplamiento**. Si expusiéramos la entidad JPA `Pet` directamente al frontend, estaríamos mostrando campos internos como `contact.id`, relaciones Lazy Loading que podrían disparar consultas SQL no deseadas, y datos sensibles. Además, si la entidad cambia (ej: agregamos un campo `lastModifiedBy`), el frontend no debería verse afectado. El DTO actúa como capa de aislamiento. También reduce la cantidad de datos transferidos por la red.

**Ubicación:** `fullstack-ss-bff/src/main/java/.../model/PetDto.java:3`

```java
public class PetDto {
    private Long id;          // :11
    private String name;      // :13
    private String race;
    private String color;
    private String size;
    private String status;
    // Sin createdAt, sin relaciones JPA
}
```

**Decir:** "Desacoplamos las entidades JPA de lo que recibe el frontend. El DTO solo expone los campos que el frontend necesita. Si la entidad cambia internamente (ej: agregamos campos de auditoría), el DTO no cambia. Esto es una buena práctica de seguridad: nunca expongas entidades JPA directamente."

**Recursos:**
- [Video: DTO Pattern explicado](https://www.youtube.com/watch?v=PLqlA5XKdFM)
- [Video: DTOs con Spring Boot (Amigoscode)](https://www.youtube.com/watch?v=THLDX9bWcjU)
- [Baeldung — DTO Pattern](https://www.baeldung.com/java-dto-pattern)
- [MapStruct — Mapeo automático DTO-Entidad](https://mapstruct.org/)
- [Oracle — DTO Pattern (Java Blueprints)](https://www.oracle.com/java/technologies/dto.html)

---

### API Gateway (Patrón Arquitectónico)  `application.yml:15-55`

** ¿En qué consiste?** Patrón arquitectónico que establece un único punto de entrada para todas las solicitudes de los clientes. El API Gateway actúa como un **reverse proxy** que enruta las peticiones al microservicio correspondiente, aplica autenticación (JWT), tolerancia a fallos (Circuit Breaker), reintentos (Retry), y políticas de CORS. Los microservicios permanecen ocultos detrás del Gateway, sin exposición directa a internet.

** ¿Por qué lo seleccionamos?** Porque en una arquitectura de microservicios, el frontend no debería conocer la ubicación de cada servicio individual. Sin Gateway, el frontend tendría que hacer llamadas a `http://pet-service:3001`, `http://geo-service:3002`, etc., acoplando el cliente a la topología interna de la red. Con Gateway, el frontend solo conoce `http://api-gateway:8080/api/pets/**` y el Gateway decide internamente cómo rutear. Además, centralizamos la seguridad (JWT se valida una sola vez en el Gateway) y la resiliencia (Circuit Breaker configurado por ruta). Esto también facilita la **escalabilidad**: podemos tener múltiples instancias de un servicio y el Gateway balancea la carga.

**Ubicación:** `fullstack-ss-api-gateway/src/main/resources/application.yml:15-55`

```yaml
spring.cloud.gateway.routes:                              # :15
  - id: pet-service                                        # :16
    uri: lb://pet-service                                  # :17
    predicates: Path=/api/pets/**                          # :18
    filters:
      - CircuitBreaker=petServiceCircuitBreaker, /fallback/pet-service
  - id: geo-service                                        # :24
    uri: lb://geo-service
    predicates: Path=/api/locations/**
    filters:
      - CircuitBreaker=geoServiceCircuitBreaker, /fallback/geo-service
  - id: match-service                                      # :32
    uri: lb://match-service
    predicates: Path=/api/matching/**
    filters:
      - CircuitBreaker=matchServiceCircuitBreaker, /fallback/match-service
  - id: bff-service                                        # :40
    uri: lb://bff
    predicates: Path=/api/**
    filters:
      - CircuitBreaker=bffCircuitBreaker, /fallback/bff
```

**Decir:** "Centraliza ruteo, auth JWT, circuit breaker, retry, CORS. Los microservicios no se exponen al exterior. Todo entra por un solo puerto (8080). Tenemos 4 rutas: pet (:16), geo (:24), match (:32) y bff (:40). Si necesitamos cambiar la IP de un servicio, solo se cambia en el Gateway."

**Recursos:**
- [Video: Spring Cloud Gateway (Java Brains)](https://www.youtube.com/watch?v=6NUoVOg4NbA)
- [Video: API Gateway Pattern (IBM Cloud)](https://www.youtube.com/watch?v=6g1MvzM6XmM)
- [Spring Cloud Gateway — Reference](https://cloud.spring.io/spring-cloud-gateway/reference/html/)
- [Baeldung — Spring Cloud Gateway](https://www.baeldung.com/spring-cloud-gateway)
- [Microsoft — API Gateway Pattern](https://learn.microsoft.com/en-us/azure/architecture/patterns/gateway-routing)

---

### BFF / Aggregation (Patrón Arquitectónico)  `AggregationService.java:116-137`

** ¿En qué consiste?** Patrón arquitectónico (Backend For Frontend) que crea una capa intermedia específica para el frontend. El BFF es un servicio que agrega datos de múltiples microservicios y los combina en una respuesta unificada. En lugar de que el frontend haga 3-4 llamadas a distintos servicios, hace una sola llamada al BFF y este internamente orquesta las llamadas necesarias.

** ¿Por qué lo seleccionamos?** Porque el Dashboard del frontend necesita datos de Pet Service (cantidad de mascotas perdidas/encontradas), Match Service (matches pendientes) y Geo Service (ubicaciones por zona). Sin BFF, el frontend haría 3 llamadas paralelas y tendría que unir los datos en el cliente, aumentando la complejidad y el tiempo de carga. El BFF centraliza esta lógica de agregación, devuelve exactamente la estructura que el frontend necesita, y oculta la complejidad interna de los microservicios. También implementa el **patrón Facade** al proporcionar una interfaz simplificada.

**Ubicación:** `fullstack-ss-bff/src/main/java/.../service/AggregationService.java:116-137`

```java
public DashboardDto getDashboard() {                                    // :116
    long lostPets = countByStatus("PERDIDO");                            // :118  pet-service
    long foundPets = countByStatus("ENCONTRADO");                        // :119  pet-service
    long pending = matchServiceClient.getMatchesByStatus("PENDIENTE").size(); // :120  match
    Map<String, Long> zoneTotals = locationServiceClient.getTotalsByZone();  // :121  geo
    return new DashboardDto(lostPets, foundPets, pending, ..., zoneTotals);  // :124
}
```

**Decir:** "El frontend hace 1 llamada al BFF. El BFF internamente llama a 3 microservicios distintos, combina los datos, y devuelve una respuesta unificada. Sin BFF, el frontend haría 3-4 llamadas y tendría que unir los datos él mismo. También implementa el patrón Facade."

**Recursos:**
- [Video: ¿Qué es BFF? (Backend For Frontend)](https://www.youtube.com/watch?v=Ua_Z0l2I9_0)
- [Video: BFF Pattern explicado (Microsoft)](https://www.youtube.com/watch?v=8kCwIhznXUs)
- [Sam Newman — BFF Pattern](https://samnewman.io/patterns/architectural/bff/)
- [Microsoft — BFF Pattern](https://learn.microsoft.com/en-us/azure/architecture/patterns/backends-for-frontends)
- [Baeldung — BFF con Spring](https://www.baeldung.com/spring-boot-bff)

---

### Repository  `PetRepository.java:9-19`

** ¿En qué consiste?** Patrón de persistencia que actúa como mdeiador entre la capa de dominio y la capa de acceso a datos. El Repository abstrae la lógica de base de datos detrás de una interfaz limpia, permitiendo que el resto del código trabaje con objetos de dominio sin conocer los detalles de SQL, conexiones o dialectos de base de datos. Spring Data JPA implementa automáticamente las consultas a partir del nombre del método (Query Methods).

** ¿Por qué lo seleccionamos?** Porque Spring Data JPA lo proporciona de manera nativa y simplifica enormemente la capa de persistencia. Con solo declarar una interfaz que extiende `JpaRepository`, obtenemos operaciones CRUD completas sin escribir una línea de SQL. Métodos como `findByStatus()` o `countByStatus()` generan automáticamente las consultas SQL. Además, el patrón Repository facilita el **testing**: podemos mockear el repositorio en pruebas unitarias sin necesidad de una base de datos real. Si en el futuro cambiamos MySQL por PostgreSQL, solo cambiamos el driver; la interfaz Repository no se modifica. Aplicamos el mismo patrón en geo-service (`LocationRepository`) y match-service (`MatchRepository`, `MatchCriteriaRepository`).

**Ubicación:** `fullstack-ss-pet-service/src/main/java/.../repository/PetRepository.java:9-19` (y análogos en geo-service y match-service)

```java
public interface PetRepository extends JpaRepository<Pet, Long> {  // :9
    List<Pet> findByStatus(String status);                        // :11
    List<Pet> findByRaceContainingIgnoreCase(String race);         // :13
    List<Pet> findByColor(String color);                           // :15
    List<Pet> findBySize(String size);                             // :17
    long countByStatus(String status);                             // :19
}
```

**Decir:** "Spring Data JPA abstrae la interacción nativa a MySQL detrás de una interfaz. No escribimos SQL manual. Si cambiamos de MySQL a PostgreSQL, solo cambiamos el driver. Esto es el patrón Repository, que actúa como mediador entre la capa de dominio y el mapeo de datos. Tenemos 3 repositories similares: PetRepository (:9), LocationRepository y MatchRepository."

**Recursos:**
- [Video: Spring Data JPA (Amigoscode)](https://www.youtube.com/watch?v=8SGI_XS5OPw)
- [Video: JPA Repository Methods (Java Guides)](https://www.youtube.com/watch?v=0VYTx3tN5oE)
- [Documentación Spring Data JPA](https://docs.spring.io/spring-data/jpa/reference/)
- [Baeldung — Spring Data JPA Queries](https://www.baeldung.com/spring-data-jpa-query)
- [Baeldung — Repository Pattern en Spring](https://www.baeldung.com/spring-data-repositories)

---

## Slide 6.5 — Arquetipos Maven y Principios SOLID (1.5 min)

**Qué decir:** "Además de los patrones de diseño, aplicamos arquetipos Maven y principios SOLID en toda la solución."

### Arquetipos Maven

**¿En qué consisten?** Los arquetipos Maven son plantillas de proyectos que permiten generar la estructura base de un nuevo servicio con un solo comando. Creamos 2 arquetipos personalizados para garantizar consistencia entre todos los microservicios del proyecto.

**Arquetipo 1: `microservice`** — Plantilla para microservicios con JPA + Liquibase + MySQL
- **Ubicación:** `fullstack-ss/backend/archetypes/microservice/`
- **Tecnologías:** Spring Boot 3.1.2, Spring Data JPA, Liquibase, MySQL Connector, Validación
- **Estructura que genera:**
  ```
  {artifactId}/
  ├── pom.xml
  └── src/main/java/.../{package}/
      ├── Application.java
      └── resources/
          ├── application.properties
          └── db/changelog/db-changelog-master.xml
  ```
- **Comando para generar un nuevo servicio:**
  ```bash
  mvn archetype:generate \
    -DarchetypeGroupId=com.sanosysalvos \
    -DarchetypeArtifactId=microservice \
    -DarchetypeVersion=0.0.1-SNAPSHOT \
    -DgroupId=com.sanosysalvos \
    -DartifactId=nuevo-servicio \
    -Dpackage=com.sanosysalvos.nuevoservicio
  ```

**Arquetipo 2: `bff`** — Plantilla para Backend For Frontend con patrón Aggregation
- **Ubicación:** `fullstack-ss/backend/archetypes/bff/`
- **Tecnologías:** Spring Boot 3.1.2, OpenFeign, Actuator
- **Estructura que genera:**
  ```
  bff/
  ├── pom.xml
  └── src/main/java/.../bff/
      ├── BffApplication.java
      ├── service/AggregationService.java
      └── resources/application.yml
  ```
- **Comando para generar un nuevo BFF:**
  ```bash
  mvn archetype:generate \
    -DarchetypeGroupId=com.sanosysalvos \
    -DarchetypeArtifactId=bff \
    -DarchetypeVersion=0.0.1-SNAPSHOT \
    -DgroupId=com.sanosysalvos \
    -DartifactId=nuevo-bff
  ```

**Decir:** "Los arquetipos nos permitieron crear los 5 servicios backend en minutos, con la misma estructura, mismas dependencias y mismas convenciones. Si mañana agregamos un nuevo microservicio, usamos el arquetipo y ya tiene JPA, Liquibase, MySQL y tests listos."

**Recursos:**
- [Video: Maven Archetypes Tutorial](https://www.youtube.com/watch?v=H5H1EoY8vXw)
- [Video: Crear arquetipos Maven (Code Java)](https://www.youtube.com/watch?v=QBhJmF-R2VM)
- [Documentación Maven Archetype](https://maven.apache.org/guides/introduction/introduction-to-archetypes.html)
- [Baeldung — Maven Archetype](https://www.baeldung.com/maven-archetype)

---

### Principios SOLID aplicados en el código

**¿Qué son SOLID?** Son 5 principios de diseño orientado a objetos que guían la creación de código mantenible y extensible. Aquí está cómo los aplicamos:

| Principio | Descripción | Ejemplo en el proyecto |
|---|---|---|
| **S** — Single Responsibility | Cada clase tiene una única responsabilidad | `PetController` solo maneja HTTP; `PetService` solo lógica de negocio; `PetRepository` solo persistencia. Separación clara en capas Controller → Service → Repository |
| **O** — Open/Closed | Abierto a extensión, cerrado a modificación | **Strategy** en MatchingService: podemos agregar `calculateGeoMatch()` sin modificar `calculateMatch()` ni `calculateSimpleMatch()`. **Factory** en PetFactory: agregar `createSightingPet()` sin tocar los métodos existentes |
| **L** — Liskov Substitution | Las subclases deben poder reemplazar a sus padres | Todas las interfaces `*Repository` extienden `JpaRepository` y pueden sustituirse sin problemas. Las implementaciones de `RestTemplate` en los clients del BFF son intercambiables |
| **I** — Interface Segregation | Interfaces específicas en lugar de una general | Separamos `PetRepository`, `ContactRepository`, `PetReportRepository` en lugar de un solo `Repository` genérico. Cada interfaz expone solo los métodos que necesita |
| **D** — Dependency Inversion | Depender de abstracciones, no de implementaciones | Inyectamos `PetRepository` (interfaz) en lugar de `JpaRepositoryImpl` (implementación). Usamos constructor injection en todos los servicios. Los clients del BFF dependen de interfaces |

**Decir:** "SOLID no es un patrón, es una guía. El principio Open/Closed se ve claramente en nuestro Strategy (podemos agregar algoritmos sin modificar los existentes) y en Factory. Single Responsibility está en toda la arquitectura: cada microservicio, cada capa, cada clase tiene un propósito único. Dependency Inversion lo logramos con Spring IoC: nunca instanciamos dependencias con `new`, siempre las recibimos por constructor."

**Recursos:**
- [Video: SOLID Principles (Programming with Mosh)](https://www.youtube.com/watch?v=pTB30aRKEnA)
- [Video: SOLID en Java (Amigoscode)](https://www.youtube.com/watch?v=0_2U5i8J8so)
- [Refactoring Guru — SOLID](https://refactoring.guru/es/design-principles/solid)
- [Baeldung — SOLID Principles en Java](https://www.baeldung.com/solid-principles)

---

## Slide 7  Demo (3-4 min)

**Qué mostrar (en vivo o video backup):**

1. Abrir frontend en navegador
2. Dashboard con métricas (totales de mascotas perdidas, encontradas, matches pendientes, ubicaciones por zona)
3. Crear una mascota (formulario con validación)
4. Listar mascotas y buscar por estado
5. Mostrar coincidencias (matches) con porcentaje
6. Confirmar/rechazar match
7. Mapa de ubicaciones (integración geo)
8. Health check: abrir `/actuator/health` en el navegador
9. **Bonus:** Mostrar Postman con la colección de endpoints

**Backup:** Video grabado con OBS Studio (2-3 min) por si falla internet.

**Recursos:**
- [OBS Studio  Grabación gratuita](https://obsproject.com/)
- [Postman  Colecciones de API](https://learning.postman.com/docs/getting-started/creating-the-first-collection/)

---

## Slide 8  Pruebas Unitarias (1 min)

**Qué decir:**
"125 tests en 18 archivos distribuidos en los 6 componentes."

**Qué mostrar:**

| Componente | Tests | Tecnología |
|---|---|---|
| Frontend | 1 | Vitest |
| BFF | 15 | JUnit + Mockito |
| Pet Service | 32 | JUnit + Mockito |
| Geo Service | 24 | JUnit + Mockito |
| Match Service | 32 | JUnit + Mockito |
| API Gateway | 21 | JUnit + WebFlux |
| **Total** | **125** | |

**Comandos:**
```bash
mvn clean test       # backend (todos los servicios)
npx vitest run       # frontend
```

**Qué decir sobre cobertura:**
"Usamos Mockito para simular dependencias externas (llamadas REST entre servicios) y nos enfocamos en probar la lógica de negocio: creación de mascotas, algoritmo de matching, agregación de dashboard, circuit breaker fallback."

**Recursos:**
- [Documentación JUnit 5](https://junit.org/junit5/docs/current/user-guide/)
- [Documentación Mockito](https://site.mockito.org/)
- [Vitest  Documentación](https://vitest.dev/guide/)
- [Video: Unit Testing Spring Boot (Amigoscode)](https://www.youtube.com/watch?v=Geq60OVyBIM)
- [Video: Mockito Tutorial](https://www.youtube.com/watch?v=2D_bb3eyV1I)

---

## Slide 9  CI/CD Pipeline + Infraestructura (1.5 min)

**Qué decir:**
"Push a main -> GitHub Actions build y push a Docker Hub -> AWS EC2 pull y deploy automático."

**Qué mostrar:**
```
GitHub Repo -> GitHub Actions -> Build Image -> Docker Hub -> EC2 (docker-compose)
```

**Mencionar:**
- 6 workflows independientes (uno por repositorio)
- Session Manager para acceso a EC2
- `docker-compose pull && docker-compose up -d`
- Cada servicio tiene su propia imagen en Docker Hub:
  - `d4mag3/frontend`
  - `d4mag3/api-gateway`
  - `d4mag3/bff`
  - `d4mag3/pet-service`
  - `d4mag3/geo-service`
  - `d4mag3/match-service`

**Diagrama de flujo CI/CD:**
```
Desarrollador -> Push a main -> GitHub Actions (mvn test, mvn package)
  -> docker build -> docker push -> AWS EC2 (docker-compose pull && up)
```

**Recursos:**
- [GitHub Actions  Documentación](https://docs.github.com/en/actions)
- [Video: CI/CD con GitHub Actions + Docker](https://www.youtube.com/watch?v=R8_veQiYBjI)
- [Docker Hub](https://hub.docker.com/)
- [AWS EC2  Documentación](https://docs.aws.amazon.com/ec2/)
- [AWS Systems Manager Session Manager](https://docs.aws.amazon.com/systems-manager/latest/userguide/session-manager.html)

---

## Slide 10  Docker y Contenerización (1 min)

**Qué decir:**
"Cada componente tiene su Dockerfile. Usamos docker-compose para orquestar 9 contenedores: frontend, api-gateway, bff, pet-service, geo-service, match-service, y 3 bases de datos MySQL."

**Qué mostrar:**
- Lista de imágenes en Docker Hub
- Fragmento de docker-compose.yml

```yaml
services:
  pet-service:
    build: ./pet-service
    ports:
      - "3001:3001"
    depends_on:
      - pet-db
    restart: on-failure
  pet-db:
    image: mysql:8.0
    volumes:
      - pet-data:/var/lib/mysql
```

**Recursos:**
- [Documentación Docker Compose](https://docs.docker.com/compose/)
- [Video: Docker Compose en 20 minutos](https://www.youtube.com/watch?v=HG6y4Z2wU5I)
- [Docker Best Practices](https://docs.docker.com/develop/dev-best-practices/)
- [Video: Dockerizar una App Spring Boot](https://www.youtube.com/watch?v=H3Yq7emVtGM)

---

## Slide 11  Estrategia de Branching (1 min)

**Qué decir:**
"Usamos Git Flow adaptado: main (producción) -> develop -> features, fixes, chores. Nunca se trabaja directo en develop. Todo pasa por Pull Request con code review."

**Qué mostrar:**
```
main
└── develop
    ├── feature/observer-pattern
    ├── feature/pet-service-crud
    ├── chore/update-readme-bff
    └── hotfix/urgent-fix
```

| Integrante | Repositorios |
|---|---|
| Axel-DaMage | fullstack-ss-api-gateway, fullstack-ss-bff |
| xMvxyz | fullstack-ss-pet-service, fullstack-ss-geo-service |
| Dogameplays | fullstack-ss-frontend |

**Conventional Commits:**
- `feat:` nuevas funcionalidades
- `fix:` correcciones de bugs
- `chore:` tareas menores y documentación
- `refactor:` refactorización de código

**Recursos:**
- [Git Flow  Documentación](https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow)
- [Conventional Commits](https://www.conventionalcommits.org/)
- [Video: Git Flow explicado](https://www.youtube.com/watch?v=1SXpE08RkS8)
- [Video: Pull Requests en GitHub](https://www.youtube.com/watch?v=8lGpZkjnUt4)

---

## Glosario rápido de términos técnicos (para responder con precisión)

| Término | ¿Qué significa? | Cómo usarlo en la defensa |
|---|---|---|
| **ACID** | Atomicity, Consistency, Isolation, Durability. Propiedades que garantizan transacciones confiables | "Nuestras operaciones `@Transactional` garantizan ACID: si una operación falla, todo se deshace (Atomicity), los datos siempre son consistentes (Consistency), las transacciones no interfieren entre sí (Isolation), y los datos persisten en MySQL (Durability)." |
| **Idempotencia** | Propiedad de una operación que produce el mismo resultado sin importar cuántas veces se ejecute | "Los GET son idempotentes. Nuestros PUT también: actualizar una mascota con los mismos datos múltiples veces da el mismo resultado. Esto es importante para resiliencia." |
| **Stateless** | El servidor no almacena estado del cliente entre requests. Cada request contiene toda la info necesaria | "Nuestros microservicios son stateless. No guardan sesiones. Esto permite escalar horizontalmente: cualquier instancia puede atender cualquier request." |
| **Service Discovery** | Mecanismo que permite a los servicios encontrarse entre sí dinámicamente, sin IPs fijas | "Tenemos Eureka configurado para service discovery. Los servicios se registran al iniciar y el API Gateway los descubre por nombre (`lb://pet-service`) en lugar de IP hardcodeada." |
| **Circuit Breaker** (estados) | **Closed** (funcionando), **Open** (fallando), **Half-Open** (probando recuperación) | "Cuando el circuito está **Closed**, las llamadas pasan normalmente. Al superar 50% de fallos, pasa a **Open** y rechaza llamadas inmediatamente. Después de 5s pasa a **Half-Open** y prueba 1 llamada. Si funciona, vuelve a **Closed**." |
| **Degradación graceful** | El sistema sigue funcionando parcialmente cuando un componente falla | "Si el Match Service se cae, el resto del sistema (mascotas, ubicaciones) sigue funcionando. El usuario ve 'Matching no disponible' en lugar de una página de error." |
| **CORS** | Cross-Origin Resource Sharing. Mecanismo de seguridad del navegador para permitir requests entre dominios | "Configuramos CORS en el API Gateway para permitir requests desde el frontend (`*`). Sin esto, el navegador bloquearía las llamadas por política de mismo origen." |
| **JWT** | JSON Web Token. Token seguro y firmado para autenticación | "El frontend envía el JWT en el header `Authorization: Bearer <token>`. El API Gateway valida la firma y extrae la identidad del usuario. Los microservicios no validan JWT, confían en el Gateway." |
| **Liquibase** | Herramienta de versionado de esquemas de base de datos | "Los cambios en BD se versionan en XML changelogs. Al iniciar, Liquibase aplica los cambios pendientes automáticamente. Esto permite sincronizar BD entre desarrolladores y entornos." |
| **OpenFeign** | Cliente HTTP declarativo para Spring Boot | "En el BFF y arquetipo BFF usamos OpenFeign para declarar clients REST con interfaces Java. Solo definimos `@FeignClient(name = "pet-service")` y Spring implementa las llamadas HTTP." |
| **DTO** | Data Transfer Object. Objeto plano para transportar datos entre capas | "Nunca expongamos entidades JPA al frontend. Los DTOs filtran campos sensibles y desacoplan la API de la implementación interna." |
| **Proxy AOP** | Programación Orientada a Aspectos. Spring genera un proxy que envuelve métodos con anotaciones | "Cuando ponemos `@Transactional`, Spring crea un proxy que abre/cierra la transacción automáticamente. El desarrollador solo escribe la lógica de negocio." |
| **Inversión de Control (IoC)** | El framework controla el flujo y la creación de objetos, no el desarrollador | "Spring IoC maneja la creación de beans. Nosotros solo declaramos dependencias en el constructor y Spring las inyecta automáticamente." |
| **Escalabilidad horizontal** | Agregar más instancias de un servicio para manejar más carga | "Nuestros servicios son stateless, así que podemos ejecutar 3 instancias de pet-service detrás del Gateway. El balanceo de carga distribuye el tráfico." |
| **Database per Service** | Cada microservicio tiene su propia base de datos | "Pet Service tiene su BD `pet_service`, Geo Service tiene `geo_service`, Match Service tiene `match_service`. Esto evita contención y permite que cada servicio evolucione independientemente." |
| **Conventional Commits** | Formato estandarizado para mensajes de commit: `tipo(alcance): descripción` | "Usamos `feat:`, `fix:`, `chore:`, `refactor:` como tipos. Ejemplo: `feat(pet-service): add search by color endpoint`. Esto genera un changelog automático." |
| **Git Flow** | Estrategia de branching con ramas main, develop, feature, hotfix | "Nuestra adaptación: main (producción), develop (integración), feature/* (funcionalidades), fix/* (bugs), chore/* (docs). Todo pasa por PR." |

---

## Slide 12  Cierre + Preguntas (Q&A)

**Slide final con:**
- "Gracias"
- QR o enlace al repositorio
- Contacto

### Preguntas frecuentes preparadas:

**¿Por qué no usaron React Router?**
"State-based view switching. El estado `section` en `App.tsx` determina la vista. Sin dependencias extra."

**¿Eureka está implementado?**
"El servidor y los clientes existen, pero usamos Docker Compose DNS para descubrimiento."

**¿Qué pasa si se cae la base de datos?**
"Docker reinicia con `restart: on-failure`. Los datos persisten en volúmenes Docker."

**¿Por qué no usaron colas (RabbitMQ/Kafka)?**
"REST era suficiente para este alcance. Si escaláramos, lo haríamos asíncrono con colas."

**¿El algoritmo de matching es preciso?**
"Usa raza, color y tamaño con pesos configurables. Se puede extender fácilmente para considerar ubicación geográfica, fecha, o fotos."

**¿Cómo probaron la resiliencia?**
"Simulamos caídas de microservicios y verificamos que Circuit Breaker responde con 503 y funcionamiento parcial del sistema."

**¿Por qué 3 bases de datos y no una sola?**
"Principio de segregación de datos en microservicios. Cada servicio es dueño de sus datos. Si geo-service cae, pet-service sigue funcionando."

**¿Cómo se comunican los microservicios?**
"Por REST HTTP sincrónico. Match-service consume Pet Service y Geo Service mediante RestTemplate con Circuit Breaker."

**¿Qué patrones GRASP se aplican?**
"**Information Expert** (cada servicio gestiona sus datos), **Creator** (Factory crea mascotas), **Low Coupling** (comunicación vía DTOs y API Gateway), **Controller** (el Controller maneja requests y delega al Service)."

**¿Qué tan seguro es el sistema?**
"El API Gateway filtra con JWT, CORS configurado, y los microservicios no están expuestos al exterior directamente."

**¿Por qué Vitest y no Jest?**
"Vitest es nativo de Vite, más rápido, misma API que Jest, mejor integración con TypeScript."

**¿Usaron alguna metodología ágil?**
"Seguimos un cronograma semanal con objetivos por etapa (10 etapas en 12 semanas), similar a Scrum con sprints de 1-2 semanas."

**¿Podría funcionar en móvil?**
"React es responsive. El frontend se adapta a mobile. Una PWA o React Native sería el siguiente paso."

### Preguntas avanzadas (para destacar):

**¿Cómo manejaron la concurrencia?**
"`@Transactional` con aislamiento READ_COMMITTED. Cada transacción es atómica. Match Service no bloquea tablas porque las consultas son de solo lectura."

**¿Cómo escalarían horizontalmente?**
"Añadiendo instancias detrás del API Gateway con balanceo de carga (Spring Cloud LoadBalancer). Las BD serían réplica leader-follower."

**¿Qué métricas monitorean?**
"Actuator expone health, circuit breakers, métricas de JVM. En producción se integraría con Prometheus + Grafana."

### Recursos generales para profundizar:

**Libros recomendados:**
- "Clean Architecture"  Robert C. Martin (Uncle Bob)
- "Building Microservices"  Sam Newman
- "Design Patterns: Elements of Reusable Object-Oriented Software"  Gang of Four
- "Refactoring Guru  Patrones de Diseño" (libro + web: https://refactoring.guru/)

**Canales de YouTube recomendados:**
- [Amigoscode](https://www.youtube.com/c/amigoscode)  Spring Boot, Docker, Testing
- [Java Brains](https://www.youtube.com/c/JavaBrains)  Spring Cloud Gateway, Microservices
- [Baeldung](https://www.baeldung.com/)  Artículos técnicos Spring
- [Web Dev Simplified](https://www.youtube.com/c/WebDevSimplified)  Patrones JS/TS
- [Programming with Mosh](https://www.youtube.com/c/programmingwithmosh)  Patrones de diseño
- [Hussein Nasser](https://www.youtube.com/c/HusseinNasser-software-engineering)  Arquitectura de software

**Cursos recomendados:**
- [Coursera  Software Design and Architecture (University of Alberta)](https://www.coursera.org/specializations/software-design-architecture)
- [Udemy  Microservices with Spring Boot](https://www.udemy.com/course/microservices-with-spring-boot/)
- [FreeCodeCamp  Full Stack Development](https://www.freecodecamp.org/)

**Webs de referencia:**
- [Martin Fowler  Microservices](https://martinfowler.com/microservices/)
- [12 Factor App](https://12factor.net/)  Buenas prácticas para apps como servicio
- [Refactoring Guru](https://refactoring.guru/)  Catálogo completo de patrones
- [Docker Best Practices](https://docs.docker.com/develop/dev-best-practices/)
- [Spring Initializr](https://start.spring.io/)  Generador de proyectos Spring

---

## Checklist de preparación

- [ ] Slides completas (Google Slides o Canva)
- [ ] Diagrama de arquitectura en PNG
- [ ] Demo lista (frontend abierto)
- [ ] Video backup grabado (OBS Studio)
- [ ] Postman/Hoppscotch abierto (por si falla frontend)
- [ ] Código de cada patrón localizado con línea exacta
- [ ] Ensayada con cronómetro (15 min exactos)
- [ ] Saber responder sin leer las slides
- [ ] Probar la demo sin internet (caché local)
- [ ] Tener docker-compose listo por si necesitan ver despliegue
- [ ] Verificar que los 9 contenedores (6 servicios + 3 BD) se levantan correctamente
- [ ] Alternativa de plan B si la demo falla: mostrar Postman + código