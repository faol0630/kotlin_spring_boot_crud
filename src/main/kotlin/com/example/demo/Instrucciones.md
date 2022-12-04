Para que funcione todo correctamente, se debe activar tomcat y se debe tener
workbench activo ya que se va a trabajar directamente en mysql y no se va a 
usar H2 ni ninguna otra base de datos.

1) Al crear el archivo en la web https://start.spring.io/ en dependencies agregar:
    *) H2 Database (sql, ya no seria necesario al usar workbench)
    *) Spring Web (web)
    *) Validation (hibernate validator)
    *) Spring Data JPA (sql)
    *) Spring Boot DevTools (reiniciar mas facil el tomcat actualizando los cambios que vamos haciendo)
    
quedan faltando algunas dependencies que toca agregarlas manualmente en el archivo
pom.xml.por ejemplo: Swagger.

2) Para que funcione la conexion a mysql asegurarse de tener en dependencies:
    
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
   </dependency>

3) en application properties, dentro de Resources copiar:

    spring.output.ansi.enabled=always

    #para que funcione swagger:
    spring.mvc.pathmatch.matching-strategy=ant-path-matcher

    #activar la consola de H2 (base de datos)
    spring.h2.console.enabled = true

    #para ver historial de consultas en SQL
    logging.level.org.hibernate.SQL= DEBUG
    logging.level.org.hibernate.type.descriptor.SQL.BasicBinder= TRACE

    #para usar un archivo fisico con la base de datos y no usar memoria volatil:
    #la siguiente direccion apunta al directorio base del computador
    # (directorio que usualmente tiene el nombre que le dio el usuario)
    #spring.datasource.url=jdbc:h2:file:${user.home}/h2/testdb

    #direccion donde estoy guardando mis proyectos:
    spring.datasource.url=jdbc:h2:file:${user.home}/AndroidStudioProjects/BACKEND/hello_worid/h2/testdb
    #el archivo seria: testdb.mv.db

    #para no borrar los cambios hechos en la base datos tales como elementos agregados
    #o editados:
    spring.jpa.hibernate.ddl-auto= update

4) La configuracion para que funcione mysql que es debe poner en properties
    en Resources(en este caso H2 ya no es necesario.en vez del punto 3 
    usar el punto 4):

   spring.output.ansi.enabled=always

    #swagger:
    spring.mvc.pathmatch.matching-strategy=ant-path-matcher

    #my sql:
    spring.datasource.url = jdbc:mysql://localhost/kotlinrest?createDatabaseIfNotExist=true&verifyServerCertificate=false&useSSL=true
    spring.datasource.driver-class-name = com.mysql.cj.jdbc.Driver
    spring.jpa.database-platform = org.hibernate.dialect.MySQL57Dialect
    spring.datasource.username = root
    spring.datasource.password = root

    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.format_sql=true

    # Valores: none, validate, update, create, create-drop <= default
    spring.jpa.hibernate.ddl-auto = create-drop
    #spring.jpa.hibernate.ddl-auto = update

5) Crear los paquetes controller, domain(podria llamarse model), service, 
   configuration, dao(podria llamarse repository) y utils

6) Crear el o los data class(entity) en el paquete domain
   (un data class por cada tabla.)

7) Crear basicCrud en el paquete service

8) Crear abstract class BasicController en el paquete controller


[BASES DE DATOS(H2)]

6) Crear paquete dao y dentro crear archivo Dao.kt que incluye la interface Dao
   (un archivo Dao por cada data class(entity) que hayamos creado)

7) Crear class Services en el paquete service(en este caso Services,
   un class services por cada Entity)

8) Crear la class Controller en el paquete controller(una class por cada Entity)

[SWAGGER]

10) Crear paquete configuration y dentro de este paquete crear la class Swagger
para acceder a swagger: http://localhost:8080/swagger-ui.html

11) En la carpeta del proyecto crear una clase que hereda de ApplicationRunner
    (en este caso se llama OnBoot)

[Lo siguiente no es necesario para usar el CRUD basico]

12) En el paquete controller crear un archivo llamado RestResponseEntityErrorHandler.
Con este archivo se van a manejar los errores que puedan surgir en la conexion.

13) Crear paquete dto y dentro crear archivo dto.kt
se crea para devolver por medio de la API un objeto similar al modelo que tenemos pero
que no es el mismo(parecido a un Mock).Son llamados objetos de transferencia de datos.
Crear dentro de archivo dto la dataClass ApiResponse.

[Usar una segunda tabla (entity) en relacion uno a muchos]

14) En el data class(entity) que ya existe agregar una nueva variable del tipo data class
    (entity) se va a crear.(en este caso: val provider: Provider)

15) En el paquete domain crear una nueva Entity o data class
    (en este caso se llama Provider y tiene 3 variables: id, name, email)

16) En el paquete dao crear la interface de la nueva data class
    (en este caso seria interface providerDao)

17) En el paquete services crear una nueva class Services que herede de BasicCrud
    (en este caso se llama ProviderServices)

18) En el paquete controller crear class controller para usar con el nuevo Entity
    (en este caso crear la class ProviderController)

19) En la class OnBoot inyectar el nuevo service(ProviderServices)

ResponseEntity viene con Spring y se usa para determinar el status de la respuesta. 
200 OK, 404 not found, etc
La respuesta pasa por ResponseEntity por eso se escribe: 
ResponseEntity<Boolean> o ResponseEntity<T> etc

Para ver la consola de H2:
http://localhost:8080/h2-console 
(da error con la creacion de base de datos.no permite entrar)