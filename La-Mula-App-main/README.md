# La Mula APP (MVP)


## Descripción

La Mula es una app que permite gestionar y despachar pedidos a sus clientes de manera eficiente. Mejora la relación con los clientes y optimiza el proceso de ventas. Además, facilita una logística más sostenible y efectiva en la cadena de suministro del café.

## Pre-requisitos

Para ejecutar la app, es necesario tener instalada al menos la versión 27.2.0 de Docker.

## Inicializar el proyecto

Antes de ejecutar los contenedores, asegúrate de compilar el proyecto en la terminal con Gradle:

```
$ ./gradlew clean && ./gradlew bootJar
```

Luego, inicia Docker Compose con el siguiente comando en la terminal:


```
$ docker compose up --build
```
De este modo, se iniciará la aplicación La Mula.


## Ejecutar los Tests

Los tests están ubicados en la carpeta service, dentro de la ruta src/test/service. Para ejecutarlos, utiliza el siguiente comando en la terminal:

```
./gradlew test 
```
