# Proyecto Spring Boot - Programación III

Estudiante:Arlin Guisel Castillo Cermeño 
Curso: Programación III  
Universidad Mariano Gálvez de Guatemala  

##Descripción
Proyecto API REST desarrollado con Spring Boot como parte del laboratorio de Programación III. Incluye endpoints de saludos, validaciones, manejo de errores, documentación con Swagger/OpenAPI, un endpoint desafiante para simulación de préstamo y una nueva fase de persistencia con Spring Data JPA y PostgreSQL.

##Tecnologías utilizadas
Java 17
Spring Boot 3.3.5
Maven
Swagger / OpenAPI
JUnit 5
MockMvc
Spring Data JPA
PostgreSQL

##Funcionalidades implementadas
##API base
GET /api/v1
##Endpoints de saludos
GET /api/v1/saludos?nombre=Ana
POST /api/v1/saludos

##Validaciones
Validación de nombre obligatorio
Manejo de errores de validación
Manejo de reglas de negocio
##Endpoint desafiante
POST /api/v1/simulaciones/prestamo

##Ejemplo de request para préstamo
{
  "monto": 10000,
  "tasaAnual": 12,
  "meses": 12
}
##Ejemplo de respuesta para préstamo
{
  "cuotaMensual": 888.49,
  "interesTotal": 661.88,
  "totalPagar": 10661.88
}

##Incremento JPA con PostgreSQL
Como siguiente fase del proyecto, se integró persistencia con Spring Data JPA y PostgreSQL.

##Base de datos utilizada
Se utilizó PostgreSQL con una base de datos llamada:
workshop_jpa

##Configuración principal en application.properties
spring.application.name=springboot-api-demo
server.port=8080

spring.datasource.url=jdbc:postgresql://localhost:5432/workshop_jpa
spring.datasource.username=postgres
spring.datasource.password=Castillo
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

##Valor usado en ddl-auto
Se utilizó:
spring.jpa.hibernate.ddl-auto=update
Se eligió update porque el proyecto está en fase de desarrollo y permite que Hibernate sincronice automáticamente las tablas con las entidades JPA sin eliminar la información existente. Como alternativa para producción, se recomienda validate junto con herramientas de migración como Flyway o Liquibase.

##Entidades implementadas
Categoria
id
nombre
descripcion
creadoEn

##Producto
id
nombre
sku
precio
stock
creadoEn
Categoria

##CRUD implementado con JPA
##Categorías
POST /api/v1/categorias
GET /api/v1/categorias
GET /api/v1/categorias/{id}
PUT /api/v1/categorias/{id}
DELETE /api/v1/categorias/{id}

##Productos
POST /api/v1/productos
GET /api/v1/productos
GET /api/v1/productos/{id}
PUT /api/v1/productos/{id}
DELETE /api/v1/productos/{id}

##Validaciones implementadas en JPA
##CategoriaRequest
nombre obligatorio
máximo 100 caracteres
descripción obligatoria
máximo 255 caracteres

##ProductoRequest
nombre obligatorio
sku obligatorio
precio obligatorio y mayor a 0
stock obligatorio y no negativo
categoriaId obligatoria

##Manejo de errores
400 BAD REQUEST → errores de validación
400 BAD REQUEST → reglas de negocio
404 NOT FOUND → recurso no encontrado
500 INTERNAL ERROR → errores internos no controlados
Excepción personalizada agregada: ResourceNotFoundException

##Ejemplos del incremento JPA
##Ejemplo de categoría creada
{
  "nombre": "Bebidas Frias",
  "descripcion": "Jugos y Gaseosas"
}
##Ejemplo de producto creado
{
  "nombre": "Coca Cola 600ml",
  "sku": "SKU-001",
  "precio": 9.00,
  "stock": 25,
  "categoriaId": 1
}

##Documentación Swagger
http://localhost:8080/swagger-ui/index.html

##Ejecución del proyecto
##Compilar
mvn clean compile
##Ejecutar pruebas
mvn test
##Levantar aplicación
mvn spring-boot:run

##Pruebas realizadas
Compilación correcta con Maven
Ejecución de pruebas con BUILD SUCCESS
Pruebas de endpoints en navegador, CMD y Swagger
Validación de errores controlados
Simulación de préstamo funcionando correctamente
Creación y listado de categorías con PostgreSQL
Creación y listado de productos con PostgreSQL
Actualización de categorías y productos
Validación de errores 404 NOT FOUND
Validación de errores 400 BAD REQUEST
Persistencia correcta de datos en PostgreSQL

##Estado final del proyecto
API REST en Spring Boot
validaciones y manejo de errores
Swagger/OpenAPI
simulación de préstamo
integración con Spring Data JPA
conexión a PostgreSQL
entidades relacionadas
CRUD completo para categorías y productos
pruebas pasando correctamente