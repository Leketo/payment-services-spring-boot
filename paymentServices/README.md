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

## EDR
Con esta estructura se crea un flujo simpre para el pago de un servicio

![Alt text](/paymentServices/src/main/resources/static/erd.png?raw=true "ERd")

## Tablas

* Usuario: Es el que pueden crear, acceder al sistema
* Servicios: Son los diferentes tipos de servicios que estar disponible para pagar `(ANDE, Tigo, Colegio Privado)`
* Cuenta: Es la encargada de almacenar el saldo del usuario en donde puede tener mas de una cuenta asociado a un usuario. Cuando se realiza un pago verifica que haya saldo en el mismo y va descontanto que caso que si.
* Deuda Cliente: Esta tabla es la cuenta que cada cliente posee cn cada entidad, que en la practica esto se encuentra en las bases de datos de cada entidad y los provee por API pero a fines de practica lo cargamos aqui.
* Transaccion: Es esta tabla se almacena los pasos utilizados para realizar el pago `"REGISTRAR"` y `"CONFIRMAR"`

## Diseño App
Este es un diseño de ejemplo donde se tomo de ejemplo para realizar el flujo desde
![Alt text](/paymentServices/src/main/resources/static/clirnt.png?raw=true "Title")

## Contexto Auth
* 1- Registro de usuario: Registra un nuevo usuario
![Alt text](/paymentServices/src/main/resources/static/registrarse.png?raw=true "Title")

```
curl --location --request POST 'localhost:8080/api/v1/auth/register' \
--header 'Content-Type: application/json' \
--data-raw '{
  "email": "leketo@leketo.com",
  "numeroDocumento": "252222",
  "nombre": "Marcelo Pope",
  "password": "test_f2ccf0f4cb53",
  "pin": "7854"
}'
```

* 2- Login: Inicio de sesion al sistema mediante `email` y `password`
![Alt text](/paymentServices/src/main/resources/static/login.png?raw=true "Title")
```
curl --location --request POST 'localhost:8080/api/v1/auth/authenticate' \
--header 'Content-Type: application/json' \
--data-raw '{
  "email": "lindo@lekto.com",
  "password": "test_f2ccf0f4cb52"
}'

```
## Contexto Pago
En la imagen anterior veremos las pantallas que interactuan para realizar un pago 

* Pantalla 1: Se visualiza los diferentes servicios disponibles.

![Alt text](/paymentServices/src/main/resources/static/erd.png?raw=true "Title")
* Pantalla 2: Se visualiza el servicio filtrado por nombre. Tambien se puede filtrar por tipo, PUBLICO o PRIVADO.
* Pantalla 3: Una vez seleccionado el servicio que se va a pagar se procese a buscar el documento asociado a el.
* Pantalla 4: Si se encontro la deuda asociado el numero de referencia se selecciona y ve en la pantalla. Esta disponible para ingresar el monto. Valida que el usuario * posea saldo y crea la transaccion en estado 'PENDIENTE'.
* Pantalla 5: En este punto se tiene la transaccion se envia en conjunto con el PIN. Se procecede a 1- Confirmar la transacccion, 2- Actualizar el saldo de la cuenta del cliente, 3-Cancelar la deuda
* Pantalla 6: Mensaje de proceso exitoso
* Pantalla 7: Historial de transacciones

##Enpoint
* 1- Registrar Usuario
