# Pago de Servicio, Spring Boot 3.0 Security y JWT
Este proyecto demuestra la implementación de un proceso de Pago de Servicios. Incluye las siguientes características:

## Características
* Registro de usuario e inicio de sesión con autenticación JWT
* Cifrado de contraseña usando BCrypt
* Alta, baja, modificacion de clientes
* Creacion de deudas asociado a un cliente con un numero de referencia o numero de documento

## Tecnologías
* Spring Boot 3.0
* Spring Security
* JSON Web Tokens (JWT)
* BCrypt
* Maven
* Spring Data JPA
* Clean Architecture

# Inicio
Para comenzar con este proyecto, deberá tener instalado lo siguiente en su máquina local:
* JDK 8+
* Maven 3+


Para compilar y ejecutar el proyecto, siga estos pasos:

* Clone el repositorio: `git clone https://github.com/Leketo/paymentServices.git`
* Navegue al directorio del proyecto: `cd paymentServices/paymentServices`
* Crear la base de datos en un db postgres llamado `pago_servicios`, verificar dentro del proyecto el archivo `application.yml` los parametros de conexion esten apuntando de forma correcta
* Construir el proyecto: `mvn clean install`


-> La aplicación estará disponible en http://localhost:8080.

# EDR
Con esta estructura se crea un flujo simpre para el pago de un servicio
<img src="/paymentServices/src/main/resources/static/erd.png" width="700"/>

# Tablas

* `Usuario:` Es el que pueden crear, acceder al sistema
* `Servicios:` Son los diferentes tipos de servicios que estar disponible para pagar `(ANDE, Tigo, Colegio Privado)`
* `Cuenta:` Es la encargada de almacenar el saldo del usuario en donde puede tener mas de una cuenta asociado a un usuario. Cuando se realiza un pago verifica que haya saldo en el mismo y va descontanto que caso que si.
* `Deuda Cliente:` Esta tabla es la cuenta que cada cliente posee cn cada entidad, que en la practica esto se encuentra en las bases de datos de cada entidad y los provee por API pero a fines de practica lo cargamos aqui.
* `Transaccion:` Es esta tabla se almacena los pasos utilizados para realizar el pago `"REGISTRAR"` y `"CONFIRMAR"`

# Flujo
<img src="/paymentServices/src/main/resources/static/secuence.png" width="800"/>

# Diseño App Client
Este es un diseño de ejemplo donde se toma para realizar los pasos de la operativa de un pago de servicio

<img src="/paymentServices/src/main/resources/static/client.png" width="800"/>



# Contexto Auth
 ### 1- Registro de usuario: Crea un nuevo usuario
<img src="/paymentServices/src/main/resources/static/registrarse.png" width="300"/>

#### Endpoint
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

### 2- Login: Inicio de sesion al sistema mediante `email` y `password` de un usuario determinado. Retorna el JWT relacionado al cliente donde se debe setear en cada llamada a los endpoint que los requiera
<img src="/paymentServices/src/main/resources/static/login.png" width="300"/>

#### Endpoint
```
curl --location --request POST 'localhost:8080/api/v1/auth/authenticate' \
--header 'Content-Type: application/json' \
--data-raw '{
  "email": "lindo@lekto.com",
  "password": "test_f2ccf0f4cb52"
}'

```
# Contexto Pago

### Pantalla 1: Se visualiza los diferentes servicios disponibles.

<img src="/paymentServices/src/main/resources/static/servicios.png" width="300"/>

#### Obtener Todos Servicios
```
curl --location --request GET 'localhost:8080/api/v1/servicios/find_by_all' \
--header 'Authorization: Bearer {token_jwt}' \
```
#### Obtener Servicios By Id
```
curl --location --request GET 'localhost:8080/api/v1/servicios/{:id}/find_by_id' \
--header 'Authorization: Bearer {token_jwt}'
```

### Pantalla 2: Se visualiza el servicio filtrado por nombre. Tambien se puede filtrar por tipo, PUBLICO o PRIVADO.
<img src="/paymentServices/src/main/resources/static/seleccion.png" width="300"/>

#### Obtener Servicios By Nombre
```
curl --location --request GET 'localhost:8080/api/v1/servicios/{:name}/find_by_nombre' \
--header 'Authorization: Bearer {token_jwt}'
```

### Pantalla 3: Una vez seleccionado el servicio que se va a pagar se procese a buscar el documento asociado a el.
<img src="/paymentServices/src/main/resources/static/nro.png" width="300"/>

#### Obtener Deuda By Nro Referencia
```
curl --location --request GET 'localhost:8080/api/v1/deudas/{:servicio_id}/find_by_numero_referencia/{:nro_referencia}' \
--header 'Authorization: Bearer {token_jwt}'
```

### Pantalla 4: Si se encontro la deuda asociado el numero de referencia se selecciona y ve en la pantalla. Esta disponible para ingresar el monto. Valida que el usuario posea saldo y crea la transaccion en estado 'PENDIENTE'.
<img src="/paymentServices/src/main/resources/static/deuda.png" width="300"/>

#### Obtener Deuda By Nro Referencia
```
curl --location --request POST 'localhost:8080/api/v1/transacciones/registrar-pago' \
--header 'Authorization: Bearer {token_jwt}' \
--header 'Content-Type: application/json' \
--data-raw '{
	"deuda" : {
		 "id" : 252
	},
	"usuario" : {
		 "id" : 1
	},
	"cuenta" : {
		"id" : 2
	},
	"monto" : 1000000,
	"nroDocumentoTitular" : "5144525"
}'
```

### Pantalla 5: En este punto se debe obtener el identificador de la trnasaccion y se envia en conjunto con el PIN. Se procecede a 
 * 1- Validar el PIN
 * 2- Confirmar la transacccion 
 * 3- Actualizar el saldo de la cuenta del cliente
 * 4- Cancelar la deuda
 
<img src="/paymentServices/src/main/resources/static/transaccion.png" width="300"/>

#### Confirmar Pago
```
curl --location --request PUT 'localhost:8080/api/v1/transacciones/{:id_transaccion}/confirmar-pago' \
--header 'Authorization: Bearer {token_jwt}' \
--header 'Content-Type: application/json' \
--data-raw '{
  "pin": "7854"
}'
```

### Pantalla 6: Mensaje de proceso exitoso
<img src="/paymentServices/src/main/resources/static/ok.png" width="300"/>

### Pantalla 7: Historial de transacciones
<img src="/paymentServices/src/main/resources/static/lista.png" width="300"/>

#### Lista de transaccion de un usuario determinado por rango de fecha
```
curl --location --request GET 'localhost:8080/api/v1/transacciones/1/find_by_date?begin=2023-01-22&end=2023-01-23' \
--header 'Authorization: Bearer {token_jwt}'
```

## Carga de datos inicial
##### Dentro del directorio `src/main/resources/postman/`se encuentra los archivos de `environment` y `coleccion` que deben ser importados en el Postman para realizar la carga inicial de datos.

* 1- Auth/Registrar Usuario
* 2- Auth/Login
* 3- Servicios/Registrar Servicios
* 4- Cuenta/Registrar Cuenta (debe asignarle el id del usuario creado en el paso 1)* 5- Deuda/Registrar Deuda (debe asignar el id de servicio creado en el paso 3)

# T0DO
* 1- Test Unitarios
* 2- Despliegue en contenedores (Docker)
* 3- Integracion con una app (Flutter/React Native)

