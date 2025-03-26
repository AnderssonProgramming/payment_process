# payment_process
This repository shows the information of a basic payment process CRUD.

# DESCRIPCIÓN

## PARCIAL CICLO VIDA DEL DESARROLLO DE SOFTWARE SEGUNDO TERCIO (CVDS)
Una importante empresa del sector de ventas ha decidido trabajar con un proveedor
de pagos externo llamado ECICredit por lo tanto, ha decidido contratarlo para que
sea el ingeniero de software que aplique este MVP de la primera versión.
El proceso de pago es el siguiente:
Un usuario ingresa la información necesaria de la compra, agregando los productos
de su interés, si los campos no cumplen con las restricciones la solicitud queda
declinada, por el contrario si pasa la primera validación el proceso lo deja aprobado
y guarda el Pago con toda la información necesaria y su estado.
El usuario una vez tiene registrado su pago puede consultarlo por el identificador del
usuario para que el pueda ver sus pagos acreditados.
Criterios de Aceptación y Restricciones:

- Es Necesario pedirle al usuario la información del detalle del pago como los
artículos que está comprando junto a su valor unitario y la cantidad de ese
artículo y la fecha de compra.

- El valor total de la transacción es el valor de todos los artículos sumados, si
no coincide debe generar una excepción informativa al usuario.

- El usuario al finalizar el pago desea saber toda la información de la orden
relacionado con el monto total, su número de transacción, junto a la
respuesta obtenida, en caso de error, debe adjuntar a la data transaccional el
mensaje de respuesta de la solicitud y el estatus de declinado o aprobado.

- El usuario necesita ingresar las fechas en formato DD-MM-YYYY.

# SPRING INITIALIZR
![alt text](image.png)

# DIAGRAMA DE COMPONENTES

Un diagrama de componentes estructurado con la lógica de negocio de este parcial, donde el pago es procesado de acuerdo al usuario mediante HTTP Request con MongoDB, al no manejar tablas, sino todo con bases de datos no relacionales.
![alt text](image-1.png)


# DIAGRAMA DE CLASES

Basado en los requisitos del parcial, y suponiendo que ya existe el usuario, solo este tiene el pago asociado de varios productos, así que en el diagrama de clases se evidencia las diferentes capas, model, repository, controller, DTO, service.


![alt text](Elysium_Class_Diagram-1.png)


# IMPLEMENTACIÓN

## Dependencias
Se agregan en el pom.xml las dependencias necesarias para la documentación en Swagger de los endpoints y también para el reporte de JACOCO

![alt text](image-2.png)


Se crea toda la arquitectura para Pago y Product, evidenciando el buen funcionamiento con MongoDb al poder hacer eL CRUD para producto, para luego mostrarlo en la interfaz con REACT.

- POST

![alt text](image-3.png)

- GET
Lista de productos
![alt text](image-4.png)

Por id de Producto
![alt text](image-5.png)

- PATCH

![alt text](image-6.png)

Volver con el GET para revisar el cambio
![alt text](image-7.png)


- DELETE

![alt text](image-8.png)

Se verifica con el GET, mandando error 404 por lo que ese Producto ya no existe.

![alt text](image-9.png)



Como otra parte de implementación, se generó la documentación en Swagger para cada endpoint.
Cuando se ejecuta mvn spring-boot:run, con esta URL se ve la documentación: http://localhost:8080/swagger-ui/index.html#/

![alt text](image-10.png)


# TESTS

Se generaron las pruebas para el correcto funcionamiento de las clases o interfaces.


# AZURE

Se configura el ambiente de CI-CD en actions de git, así como, añadir el secret para que despliegue en Azure correctamente.
![alt text](image-11.png)

![alt text](image-12.png)

Ya se desplegó en Azure

![alt text](image-13.png)
https://fetch-ewfzgrefd4afhdbd.canadacentral-01.azurewebsites.net/


# REACT

Ahora, se muestra la implementación del CRUD para Producto, aunque esta misma implementación está en el otro repo.

![alt text](image-14.png)

Se agregó el producto
![alt text](image-15.png)

Ahora si se quiere editar algún atributo, pues se hace directamente con el endpoint que maneja el método PATCH

![alt text](image-16.png)

Y ahora con el método DELETE, hay la posibilidad de eliminar un producto ya creado.
![alt text](image-17.png)

