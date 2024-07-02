# Resumen
En este repositorio se encuentra un microservicio programado en Kotlin + springboot con la finalidad de consumir el API de [Json Placeholder](https://jsonplaceholder.typicode.com) mas concretamente los endpoints de [Albums](https://jsonplaceholder.typicode.com/albums) y [Photos](https://jsonplaceholder.typicode.com/albums).

Para ello he utilizado una estructura básica de MVC junto con el framework de spring boot para crear el controlador y servicio utilizados para la lógica del micro.

# Índice
[Técnologías usadas](#Técnologías-usadas)

[Como iniciar el microservicio](Como-iniciar-el-microservicio)

[Como ejecutar la cobertura de código con jacoco](Como-ejecutar-la-cobertura-de-código-con-jacoco)

[Detalles técnicos](#Detalles-técnicos)

# Técnologías usadas
## Base
1. Kotlin: Es el lenguaje en el que se ha escrito todo el micro.
2. Spring framework: Se utiliza para montar la estructura del micro y toda la lógica de Beans utilizada para la inyección de dependencias.
3. Spring security: Lo he utilizado para añadir una capa de seguridad al microservicio donde solo se podrá consultar por los endpoints de consulta de albums y de swagger.
4. Spring web: Se utiliza la clase de [`RestTemplate`](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/client/RestTemplate.html) para hacer las llamadas HTTP a los endpoints de [Json Placeholder](https://jsonplaceholder.typicode.com)

## Test
1. Junit5: Librería base utilizada para la creación de test
2. Mockito: Librería auxiliar creada para facilitar el testeo de código haciendo posible crear objetos "mock" para probar la funcionalidad deseada
3. Jacoco: Herramienta utilizada para la creación de reportes de cobertura del código.

## Documentación
1. OpenApi swagger: Librería utilizada para crear un endpoint de consulta con todas las operaciones y objetos del microservicio.


# Como iniciar el microservicio

Lo primero que debemos hacer es tener un IDE válido para poder abrir el proyecto. En mi caso uso [Intellj idea community edition](https://www.jetbrains.com/idea/download/download-thanks.html?platform=windows&code=IIC) también sería posible usar otro IDE como Spring Tool Suite pero sería necesario instalar plugins para permitir el uso de kotlin.

El segundo paso sería descargarnos una release válida del microservicio como [esta](https://github.com/IsraelNavarrete/JsonPlaceHolderAPI/archive/refs/tags/1.0.0.zip) o clonarnos el repositorio en local. Una vez tengamos todo esto en nuestro local podemos empezar por importar el proyecto.

Para ello lo primero que tenemos que hacer es abrir el IDE elegido (quizás si es la primera vez que se abre pide unos requisitos previos a poder importar el proyecto)
![image](https://github.com/IsraelNavarrete/JsonPlaceHolderAPI/assets/61150530/7fb99108-2127-40c7-ba6f-0e4714e4d6e7)

Como se puede ver en la imagen hay un boton que pone `"Open"` podemos utilizarlo para abrir el proyecto. para ello solo habría que navegar hasta la carpeta donde lo tengamos.

Cuando abra el proyecto tendremos que ir a la parte derecha del IDE y junto a los botones de la barra de arriba veremos uno en vertical que pone `"maven"`

![image](https://github.com/IsraelNavarrete/JsonPlaceHolderAPI/assets/61150530/cec0a282-f749-49f7-98eb-8a0f59932f8e)

Se nos tiene que abrir esta pestaña.

![image](https://github.com/IsraelNavarrete/JsonPlaceHolderAPI/assets/61150530/b39b9ecc-a2b0-4583-9f11-f4a54a98b6be)

Cuando esté abierta lo más cómodo y sencillo es dar doble click en los botones de `"clean"` e `"install"`. Cuando acabe la operación de `"install"` entonces nos dirijimos a la parte izquierda del IDE y si no tuvieramos abierta la pestaña de `"project"` sería darle al botón igual que con `"maven"`.

Una vez abierto podremos ver la estructura del proyecto de esta forma:

![image](https://github.com/IsraelNavarrete/JsonPlaceHolderAPI/assets/61150530/0d9d5f1d-9e94-4ad0-804d-78525fd35c90)

Una vez abierto podemos dirigirnos al main del programa, ubicado en `src/main/kotlin/com/example/jsonplaceholderbcnc/JsonplaceholderbcncApplication.kt`. Cuando lo abramos veremos esta clase:

![image](https://github.com/IsraelNavarrete/JsonPlaceHolderAPI/assets/61150530/8b95c7ee-3768-4cac-8b15-ab49659f6c7a)

Con esto abierto lo mas sencillo es darle al botón de play al lado de `fun main` y darle al boton de run:

![image](https://github.com/IsraelNavarrete/JsonPlaceHolderAPI/assets/61150530/10cd3e9b-e29c-4edb-8c0e-2a3ed7741eef)

Cuando lo iniciemos en la parte de abajo veremos como nos empiezan a salir logs y como uno de ellos nos marca que el microservicio se ha iniciado correctamente:

![image](https://github.com/IsraelNavarrete/JsonPlaceHolderAPI/assets/61150530/193cfb9a-6610-4a58-bf94-54a98a578489)

Finalmente para probar el microservicio simplemente habría que acceder a esta [url](localhost:8080/v1/jsonplaceholder/album) desde un navegador o postman y ya veríamos la respuesta del micro con todos los álbums.

## Como ver el swagger

Hay que seguir los pasos anteriores para levantar el microservicio. Una vez levantando solo tenemos que acceder a esta [url](http://localhost:8080/swagger-ui/index.html)

![image](https://github.com/IsraelNavarrete/JsonPlaceHolderAPI/assets/61150530/7cb6bbe1-db12-4358-951f-d8f7d6de55f1)

# Como ejecutar la cobertura de código con jacoco

Con el IDE ya abierto nos vamos a la pestaña de `"maven"` y damos click izquierdo al botón de `"install"` y selecccionamos "Modify run configuration":

![image](https://github.com/IsraelNavarrete/JsonPlaceHolderAPI/assets/61150530/51d70138-0bbf-4c27-a1ca-41961fbab150)

Aquí se nos abrirá una pestaña de configuración donde podemos cambiarle el nombre de la nueva config que haremos. en mi caso le puse "clean install jacoco" pero no es necesario hacerlo. Lo que si es importante es que cambiemos `install -f pom.xml` por este `clean install test jacoco:prepare-agent jacoco:report -f pom.xml` y demos a apply y ok.

Cuando lo hayamos hecho si abrimos la pestaña de Run configurations veremos que está el nuestro que acabamos de crear:

![image](https://github.com/IsraelNavarrete/JsonPlaceHolderAPI/assets/61150530/bd269165-9951-4c31-a083-c9fa620dc00b)

Le damos doble click para ejecutarlo y cuando finalice sin errores debemos irnos a la pestaña de `"project` y abrir la carpeta de `"target"` 

![image](https://github.com/IsraelNavarrete/JsonPlaceHolderAPI/assets/61150530/679da12f-c05d-4ebc-bddd-1ce2515e1c11)

Ya abierto nos vamos a la carpeta de `"site/jacoco"` y veremos unos cuantos archivos y carpetas:

![image](https://github.com/IsraelNavarrete/JsonPlaceHolderAPI/assets/61150530/f0622064-1b5a-4383-a51d-b00e88ad77c9)

Abrimos el `"index.html"` con el navegador que queramos y veremos la cobertura:

![image](https://github.com/IsraelNavarrete/JsonPlaceHolderAPI/assets/61150530/735be728-5229-43f8-ad87-ecd825822875)

# Detalles técnicos
Para el reporte de jacoco he añadido en el pom una configuración de excludes:

```xml
<configuration>
					<excludes>
						<exclude>com/example/**/model/**</exclude>
						<exclude>com/example/**/config/**</exclude>
					</excludes>
				</configuration>
```

Simplemente le digo a jacoco que no tenga en cuenta esos archivos a la hora de hacer la cobertura.

Por mantener el microservicio lo mas simple posible he hecho que en la clase de `src/main/kotlin/com/example/jsonplaceholderbcnc/service/impl/AlbumServiceImpl.kt` se haga tanto las llamadas al API como la lógica de juntar los datos de ambas llamadas. Se podría dividir aún mas donde se haga una clase aparte encargada exclusivamente de las llamadas REST y dejar que la implementación
solamente trate los datos recibidos pero he pensado que es mejor dejarlo sencillo a añadir mas capas. Aunque si el microservicio siguiera creciendo sería necesario plantearse esta solución a largo plazo.

