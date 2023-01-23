# Pago de Servicio, Spring Boot 3.0 Security y JWT
Este proyecto demuestra la implementaciónde  un proyecto de Pago de Servicios. Incluye las siguientes características:

## Características
* Registro de usuario e inicio de sesión con autenticación JWT
* Cifrado de contraseña usando BCrypt
* Creacion de deudas asociado a un cliente con un numero de referencia o numero de documento

## Tecnologías
* Spring Boot 3.0
* Spring Security
* JSON Web Tokens (JWT)
* BCrypt
* Maven
* Spring Data JPA
* Clean Architecture

## Inicio
Para comenzar con este proyecto, deberá tener instalado lo siguiente en su máquina local:
* JDK 8+
* Maven 3+


Para compilar y ejecutar el proyecto, siga estos pasos:

* Clone el repositorio: `git clone https://github.com/Leketo/paymentServices.git`
* Navegue al directorio del proyecto: `cd paymentServices/paymentServices`
* Crear base de datos en una base de datos postgres llamado `pago_servicios`, verificar dentro del proyecto el archivo `application.yml` los parametros de conexion esten apuntando de forma correcta
* Construir el proyecto: `mvn clean install`


-> La aplicación estará disponible en http://localhost:8080.

![Alt text](/paymentServices/paymentServices/src/main/resources/static/erd.png?raw=true "Title")
