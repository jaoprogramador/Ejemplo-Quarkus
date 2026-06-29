# Camel Spring Boot Integration (`camel-springboot`)

Este proyecto es un entorno de aprendizaje y demostración para integrar **Apache Camel 4** dentro del ecosistema de **Spring Boot 3**, utilizando inyección de dependencias para procesar mensajería asíncrona.

El proyecto simula una canalización de datos donde los mensajes recibidos a través de colas JMS se procesan mediante Beans de Spring de manera totalmente desacoplada.

## 🚀 Arquitectura del Proyecto

El flujo de integración sigue el patrón de diseño clásico de mensajería:

1. **Origen:** Se escucha la cola JMS de entrada (`mensaje-input`).
2. **Procesamiento (Spring Beans):** El componente `TransformarMensajeJAO` intercepta el `Exchange`, mapeando el cuerpo y las cabeceras (headers) de los mensajes a mayúsculas utilizando servicios inyectados especializados.
3. **Destino:** El mensaje transformado se publica en la cola JMS de salida (`mensaje-output`).

---

## 🛠️ Tecnologías Utilizadas

* **Java 17**
* **Spring Boot 3.3.4** (Inversión de Control y Gestión de Beans)
* **Apache Camel 4.8.0** (Motor de Integración)
* **Apache ActiveMQ Artemis** (Broker de Mensajería JMS)
* **Testcontainers** (Para pruebas de integración automáticas con Docker)

---

## 📋 Requisitos Previos

Antes de ejecutar la aplicación o los tests, asegúrate de tener instalado:

* **Java 17** o superior.
* **Maven 3.x**.
* **Docker Desktop** (Obligatorio para poder ejecutar los Tests de Integración).

---

## ⚙️ Configuración (`application.properties`)

La aplicación se parametriza desde el archivo `src/main/resources/application.properties`. Asegúrate de que las colas y credenciales del broker coincidan con tu entorno local:

```properties
spring.application.name=camel-springboot

# Evita que el hilo principal muera al arrancar en stand-alone
camel.springboot.main-run-controller=true

# Conexión al Broker de Artemis local
spring.artemis.broker-url=tcp://localhost:61616
spring.artemis.user=artemis
spring.artemis.password=artemis

# Definición de colas dinámicas
jao.queue.input.name=mensaje-input
jao.queue.output.name=mensaje-output