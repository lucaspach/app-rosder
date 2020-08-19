
#### CONSIDERACIONES GENERALES ####

- En el archivo: /ejercicio-modelo/solucion_modelodedatos.png se encuentra mi solución al Modelo de Datos de un sistema de Facultades.
- App.ROSDER-postman.json contiene todas las peticiones utilizando postman. De todas formas se genero el equivalente en curl.

- Por razones de tiempo no pude hacer ninguno de los bonus, pero algunas soluciones:
MySQL: reescribir las props de Spring boot para apuntar a un db de MySQL. Utilizar la herramienta workbench para generar el schema, tablas, vinculaciones y luego crear el script y colocarlo en el archivo data.sql.
Autenticación y autorización: En el proyecto que hice a modo de practica me llevo un tiempo y use JWT con la siguiente guía: 
https://medium.com/@akhileshanand/spring-boot-api-security-with-jwt-and-role-based-authorization-fea1fd7c9e32
Test Unitarios: Estructura given, when, them para cada función.


=====================================================================================================================================================================================
#### Ejercicio 1 ####

- Back-end completo, levantar en el server con: mvn spring-boot:run -Drun.jvmArguments="-Dspring.profiles.active=dev"

- Con respecto al front-end elegi React y trate de aplicar algunas cosas pero no llegue a mucho (y tenia la entrega en el día). Deje el front-end para el final, la verdad que de las 3 partes globales (analiss y diseño de soluciones, front-end, back-end) es la que menos me apasiona. Sumado a que los desarrolos que tenia eran con HTML, CSS y JS crudos no pude llegar a implementar algo que funcione. Tuve problemas varios a la hora de conectar un formulario simple y la función "fetch" mi API.
Tome el diseño de un proyecto academico de APPS de Gifs para tratar de abstraerme y buscando puntos que pueda reutilizar pero no logre hacer enviar el POST como formulario correctamente.
Adjunto de todas formas el proyecto en crudo y las distintas peticiones que use con POSTMAN para probar la API.

Tengo que ponerme al día con React y se que es lo que actualmente se usa y mucho! No tendría ningun problema en acelerar el aprendizaje del mismo.

1. **obtener todos los clientes**
curl --location --request GET 'http://localhost:8080/api/customer/findAll'

2. **crear cliente persona fisica**
curl --location --request POST 'http://localhost:8080/api/customer/sign-up/physical' \
--header 'Content-Type: application/json' \
--data-raw '{
    "rut": 101010,
    "firstName": "Lucas",
    "lastName": "Pacheco",
    "cc": 165183
}'

3. **crear cliente persona juridica**
curl --location --request POST 'http://localhost:8080/api/customer/sign-up/legal' \
--header 'Content-Type: application/json' \
--data-raw '{
    "rut": 131313,
    "businessName": "Pacheco'\''s S.A",
    "foundationYear": 1990
}'

4. **obtener un cliente por RUT**
curl --location --request GET 'http://localhost:8080/api/customer/101010'

5. **eliminar un cliente por RUT**
curl --location --request DELETE 'http://localhost:8080/api/customer/101010'

6. **actualizar un cliente por RUT (persona fisica)**
curl --location --request PUT 'http://localhost:8080/api/customer/fisica/111111' \
--header 'Content-Type: application/json' \
--data-raw '{
    "rut": 111111,
    "firstName": "Viviana",
    "lastName": "Olmos",
    "cc": 151522
}'

7. **actualizar un cliente por RUT (persona juridica)** 
curl --location --request PUT 'http://localhost:8080/api/customer/fisica/111111' \
--header 'Content-Type: application/json' \
--data-raw '{
    "rut": 111111,
    "firstName": "Viviana",
    "lastName": "Olmos",
    "cc": 151522
}'


=====================================================================================================================================================================================
#### Ejercicio 2 ####

- Aclaración: En el caso del endpoint 4. El objeto currentAccount se debera obtener actualizado cada vez que se haga la petición para así validar correctamente la regla de negocio con los diferentes descubiertos.
Ej:
-----------------
PRIMERA VEZ
"currentAccount": {
         "accountNumber": 1,
         "money": {
               "id": 1
         },
         "transactions": [],
         "balance": 1611.60
      }

Al momento de añadir un nuevo movimiento sería hacer un GET con el endpoint 3b
"currentAccount": {
         "accountNumber": 1,
         "money": {
               "id": 1
         },
         "transactions": [],
         "balance": /**SALDO ACTUALIZADO**/
      }

-----------------

Curls creados por cada endpoint
   1. *crear cuenta*: 
   curl --location --request POST 'http://localhost:8080/api/currentaccount/sign-up' \
   --header 'Content-Type: application/json' \
   --data-raw '{   
      "accountNumber": 1,
      "balance": 111.444,
      "money": {
         "id": 1
      }       
   }'

   2. *eliminar cuenta*
   curl --location --request DELETE 'http://localhost:8080/api/currentaccount/3'

   3. *listar cuentas*
   curl --location --request GET 'http://localhost:8080/api/currentaccount/findAll'

   b.   *listar cuenta x Id*
   curl --location --request GET 'http://localhost:8080/api/currentaccount/1'

   4. *agregar movimiento*
   curl --location --request POST 'http://localhost:8080/api/transaction' \
   --header 'Content-Type: application/json' \
   --data-raw '{
      "currentAccount": {
         "accountNumber": 1,
         "money": {
               "id": 1
         },
         "transactions": [],
         "balance": 1611.60
      },
      "transactionType": {
         "id": 2,
         "name": "CREDITO"
      },
      "description": "Compra ACA",
      "amount": 1500.1588
   }'

   5. *listar movimientos x cuenta* (ordenados de forma decreciente, por fecha)
   curl --location --request GET 'http://localhost:8080/api/transaction/1'


======================================================================================================================================================================