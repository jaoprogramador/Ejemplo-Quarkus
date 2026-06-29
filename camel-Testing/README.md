# 🍳 Apache Camel Testing

He creado una ruta que copia el contenido de un fichero /camel-Testing /src/main/resources/archivo/hola-mundo.txt y lo copia en /camel-Testing/src/main/resources/archivo/output/hola-mundo.txt con su cóntenido en mayúsculas.
HE creado un test que mediante contenedores de kafka y otra ruta de apache camel para consumir mensajes asincronos kafka y guardarlos en un fichero txt mediante Producer-Consumer-Topic

---

## 🚀 Test unitarios

/camel-Testing/src/test/java/com/jao/camel/CamelTestingApplicationTest.java
/camel-Testing/src/test/java/com/jao/camel/CamelTestingApplicationTestsWithSupport.java
/camel-Testing/src/test/java/com/jao/camel/CamelTestingApplicationTestWithExtension.java
/camel-Testing/src/test/java/com/jao/camel/MiRutaHolaRouteTest.java
/camel-Testing/src/test/java/com/jao/camel/KafkaRouteIntegrationTest.java