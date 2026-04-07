# Proyecto Spring Boot - Programación III

Estudiante:Arlin Guisel Castillo Cermeño 
Curso: Programación III  
Universidad Mariano Gálvez de Guatemala  

## Descripción
Proyecto API REST desarrollado con Spring Boot como parte del laboratorio de Programación III.  
Incluye endpoints de saludos, validaciones, manejo de errores, documentación con Swagger/OpenAPI y un endpoint desafiante para simulación de préstamo.

## Tecnologías utilizadas
- Java 17
- Spring Boot 3.3.5
- Maven
- Swagger / OpenAPI
- JUnit 5
- MockMvc

## Funcionalidades implementadas

### API base
- `GET /api/v1`

### Endpoints de saludos
- `GET /api/v1/saludos?nombre=Ana`
- `POST /api/v1/saludos`

### Validaciones
- Validación de nombre obligatorio
- Manejo de errores de validación
- Manejo de reglas de negocio

### Endpoint desafiante
- `POST /api/v1/simulaciones/prestamo`

## Ejemplo de request para Prestamo
{
  "monto": 10000,
  "tasaAnual": 12,
  "meses": 12
}

##Ejemplo de respuesta para prestamo:
{
  "cuotaMensual": 888.49,
  "interesTotal": 661.88,
  "totalPagar": 10661.88
}


## Documentacion Swagger:
http://localhost:8080/swagger-ui/index.html

Ejecucion del Proyecto
## Compilar
mvn clean compile

## Ejecutar pruebas:
mvn test

## Levantar Aplicacion:
mvn spring-boot:run

## Pruebas realizadas
Compilación correcta con Maven
Ejecución de pruebas con BUILD SUCCESS
Pruebas de endpoints en navegador, CMD y Swagger
Validación de errores controlados
Simulación de préstamo funcionando correctamente