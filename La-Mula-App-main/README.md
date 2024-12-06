# La Mula APP (MVP)


## Descripción

La Mula es una app que permite gestionar y despachar pedidos a sus clientes de manera eficiente. Mejora la relación con los clientes y optimiza el proceso de ventas. Además, facilita una logística más sostenible y efectiva en la cadena de suministro del café.


## Tecnologías de este proyecto
### Lenguaje y Framework:

- Kotlin 1.9.25
- Spring Boot 3.3.4

### Entorno de Ejecución:

- JDK 21: Java Development Kit (Kit de Desarrollo de Java)

### Arquitectura y Patrones de Diseño:

- Arquitectura en capas: presentación, negocio y persistencia.
- Programación orientada a objetos (OOP).
- Principios SOLID.
- Estilo arquitectónico REST.

### Base de Datos:

- PostgreSQL.

### Dependencias de pruebas

- JUnit 5: para realizar pruebas unitarias.
- Mockk 1.13.13: para realizar pruebas con mocks.
- Konform 0.7.0: para realizar validaciones en Kotlin.

## Pre-requisitos

Para ejecutar la app, es necesario tener instalada al menos la versión 27.2.0 de Docker.

## Inicializar el proyecto

Antes de ejecutar los contenedores, asegúrate de compilar el proyecto en la terminal con Gradle:

```
$ ./gradlew clean && ./gradlew build
```

Luego, inicia Docker Compose con el siguiente comando en la terminal:


```
$ docker compose up --build
```
De este modo, se iniciará la aplicación La Mula.

Para verificar que la aplicación se encuentra arriba, puedes usar un navegador Web o Postman con el siguiete enlace:

```
[GET] http://localhost:8080/health
```
Te debe retornar el siguiete response:

```
OK
```

## Ejecutar los Tests

Los tests están ubicados en la carpeta service, dentro de la ruta src/test/service. Para ejecutarlos, utiliza el siguiente comando en la terminal:

```
./gradlew test 
```
