# Proyecto  Camel Quarkus JMS Integration

Este proyecto ha sido desarrollado como un entorno de aprendizaje para dominar la integración de **Apache Camel** con **Quarkus** utilizando **CDI (Contexts and Dependency Injection)** en sustitución del ecosistema tradicional de Spring Boot. 

La aplicación demuestra cómo conectar múltiples brokers de mensajería (Apache Artemis y RabbitMQ) de forma dinámica, procesar mensajes utilizando rutas de Camel y aplicar transformaciones mediante Beans de Jakarta.

---

## 🏗️ Arquitectura del Proyecto

El flujo de mensajería consta de dos canales principales: un canal síncrono en memoria (`direct:`) y una integración asíncrona mediante mensajería empresarial utilizando un componente JMS configurado a medida para Apache Artemis.

               [ MensajeService ]
                      │
                      ▼ (envía "Kaixo JAO")
           ┌──────────────────────┐
           │  direct:jao-direct   │  <-- RutaDirect
           └──────────────────────┘
                      │ (Log)
                      ▼
                    RutaJMS                 │
│                                                        │
│  artemis:queue:mensaje-input                           │
│            │                                           │
│            ▼                                           │
│   [ TransformarMensajeJAO ]                            │
│            │───► MensajeBodyMapper   (A MAYÚSCULAS)    │
│            └───► MensajeHeaderMapper (A MAYÚSCULAS)    │
│            │                                           │
│            ▼                                           │
│  artemis:queue:mensaje-output                          │


### Componentes Clave:
1. **`RutaDirect`**: Ruta interna de Camel que recibe cargas de trabajo locales de manera síncrona mediante un `ProducerTemplate`.
2. **`RutaJMS`**: Escucha en una cola de entrada de Apache Artemis, procesa el cuerpo y las cabeceras del mensaje de forma transparente y deposita el resultado en una cola de salida.
3. **`JmsComponenteProducer`**: Productor de CDI que configura dinámicamente fábricas de conexiones separadas para `artemis` y `rabbitmq` utilizando los identificadores provistos por Quarkus.
4. **`TransformarMensajeJAO`**: Bean inyectado de ámbito `@ApplicationScoped` encargado de interactuar con el ciclo de vida del `Exchange` de Camel para transformar los datos utilizando mapeadores atómicos.

---

## 🛠️ Tecnologías Utilizadas

* **Java 17+**
* **Quarkus (Framework Java Nativo para Kubernetes)**
* **Apache Camel Quarkus Core & JMS**
* **Apache Artemis (ActiveMQ)** como Broker de Mensajería.
* **Jakarta EE 10** (Inyección con `@Inject`, `@Named`, `@ApplicationScoped`, `@Produces`).

---

## ⚙️ Configuración (`application.properties`)

A diferencia de Spring Boot, Quarkus utiliza sus propias propiedades optimizadas para la compilación en tiempo de construcción. La configuración de conexión con el Broker de Artemis se realiza mediante la extensión oficial de Artemis para Quarkus:

```properties
# Configuración general
quarkus.application.name=camel-quarkus

# Configuración del Broker Apache Artemis (Extensión Quarkus Artemis)
quarkus.artemis.url=tcp://localhost:61616
quarkus.artemis.username=artemis
quarkus.artemis.password=artemis

# Variables de negocio para las colas Camel
jao.queue.input.name=mensaje-input
jao.queue.output.name=mensaje-output

## Arrancar en dev mode
docker run --detach --name mycontainer -p 61616:61616 -p 8161:8161 --rm apache/activemq-artemis:2.38.0-alpine



