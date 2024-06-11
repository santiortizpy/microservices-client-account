# Microservices-Client-Account!

Microservicios creados con Springboot, api's  Cliente, cuenta y movimiento de un cliente.
El servicio de cliente se encuentra alojado en un microservicio, mientras que la cuenta y los movimientos del cliente se encuentra en el otro, los microservicios tendrán los siguientes nombres: client-microservice y account-microservice.

Para correr los servicios tener en cuenta los siguientes puntos


# Pasos a seguir

## Crear imagenes de los servicios

En las carpetas client-microservice y account-microservice se tiene un archivo dockerfile para generar la imagen para ambos microservicios para ello debemos seguir los siguientes pasos:

 - Ingresar a la carpeta de client-microservice
 - Correr el commando **docker build -t client-microservice  .**
 - Ingresar a la carpeta de account-microservice
 - Correr el comando **docker build -t account-microservice  .**

## Gestionar multicontenedores
Una vez Generadas las imagenes de los servicios, es necesario realizar  gestionar y levantar los contenedores tanto de la base de datos como de los servicios para ello corremos el siguiente comando
 
En el proyecto principal en la carpeta microservices-client-account
  **`docker-compose -f dock-microservice.yml up -d`**

## Correr script para poblar Base de datos

una vez tengamos los servicios corriendo, debemos crear las talbas y poblarla para ello desde la terminal de comando en la carpeta de microservices-client-account realizamos la siguiente ejecución:
 **`docker exec -i microservices-client-account-db-1 mysql -u account -paccount < BaseDeDatos.sql`**

## Descargar y ejecutar Colecciones de postman
Una vez tengamos todo corriendo es podemos realizar las pruebas de los servicios desde el POSTMAN, el cual se puede encontrar la colección dentro del proyecto principal.





