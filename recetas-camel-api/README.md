# 🍳 API REST de Recetas con Apache Camel y Spring Boot

Esta es una API REST profesional desarrollada con **Spring Boot 3.2.5** y **Apache Camel 4.4.0 (Java 17)**. El proyecto implementa un catálogo dinámico de recetas utilizando la arquitectura orientada a la integración de Camel, exponiendo servicios estructurados a través del componente `CamelServlet` y delegando el procesamiento de los datos mediante un `Processor` desacoplado.

---

## 🚀 Arquitecture y Componentes Clave

El diseño de la aplicación sigue los principios de desacoplamiento de Enterprise Integration Patterns (EIP):
* **Componente REST (Apache Camel DSL):** Configura la exposición de los endpoints HTTP y gestiona de manera automática la serialización/deserialización de payloads JSON.
* **Procesador (RecetaProcessor):** Una clase especializada que implementa `org.apache.camel.Processor`. Intercepta el ciclo de vida del objeto `Exchange`, extrae de forma segura el mensaje de entrada y actúa como puente directo hacia la capa de negocio.
* **Servicio (RecetaService):** Simula el almacenamiento y gestión de datos (CRUD en memoria mediante colecciones concurrentes).
* **Modelo/DTO (Receta):** Objeto de transferencia de datos optimizado para el mapeo con Jackson.

---

## ⚙️ Configuración del Proyecto (`pom.xml`)

La gestión de dependencias se apoya en los Starters oficiales de Spring Boot y el ecosistema de Apache Camel adaptado a Spring:

```xml
<properties>
    <java.version>17</java.version>
    <camel.version>4.4.0</camel.version> 
</properties>

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>

    <dependency>
        <groupId>org.apache.camel.springboot</groupId>
        <artifactId>camel-spring-boot-starter</artifactId>
        <version>${camel.version}</version>
    </dependency>
    <dependency>
        <groupId>org.apache.camel.springboot</groupId>
        <artifactId>camel-servlet-starter</artifactId>
        <version>${camel.version}</version>
    </dependency>
    <dependency>
        <groupId>org.apache.camel.springboot</groupId>
        <artifactId>camel-jackson-starter</artifactId>
        <version>${camel.version}</version>
    </dependency>
    
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
</dependencies>

# Especificación de Endpoints (API)
http://localhost:9090/api/getRecetas
http://localhost:9090/api/addReceta