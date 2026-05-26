### Archivos Listos para tu Proyecto:
* Tu archivo README (Markdown) está listo: `[file-tag: code-generated-file-0-1779524985067752244]`
* Tu guía del proyecto en formato PDF: `[file-tag: code-generated-file-1-1779524985067756444]`

---

### Código para tu `README.md` (Copia y pega este contenido)

```markdown
# Recetas Hoy - Apache Camel Integration 🚀

Este proyecto es una aplicación de integración basada en **Spring Boot** y **Apache Camel** diseñada para automatizar la gestión, filtrado, transformación y enrutamiento inteligente de archivos de recetas y menús de cocina. 

A través de este ejercicio práctico, se implementan varios de los patrones de integración empresarial más comunes (*Enterprise Integration Patterns - EIP*), como el enrutamiento basado en contenido, la división de mensajes (*Splitter*) y la transformación de datos en caliente.

---

## 🛠️ Tecnologías Utilizadas

* **Java 17** (o superior)
* **Spring Boot** (Gestión del ciclo de vida de la aplicación)
* **Apache Camel** (Motor de integración y definición de rutas)
* **Camel File Component** (Para la interacción dinámica con el sistema de archivos)

---

## 📂 Arquitectura de Archivos y Directorios

El flujo lee datos desde un directorio origen y deposita los resultados transformados en un directorio destino:

* **Origen (`origen`):** Carpeta donde se depositan los ficheros de texto plano con los registros de las recetas. Cuenta con el parámetro `noop=true` para que Apache Camel no elimine ni mueva los archivos originales tras procesarlos (ideal para pruebas repetitivas).
* **Destino (`destino`):** Carpeta donde se generan los ficheros finales procesados (en formato `.csv` o filtrados).

---

## 🛤️ Rutas e Integraciones Implementadas

El archivo principal `RecetasHoyCamelApplication.java` actúa como el `RouteBuilder` y expone las siguientes soluciones listas para usar (puedes activar/desactivar cada flujo descomentándolo en el método `configure()`):

### 1. Copia Dinámica Completa
* **Método:** `moverTodasRecetas()`
* **Acción:** Traspasa de forma directa e íntegra todos los archivos desde la carpeta origen a la de destino sin aplicar alteraciones en el nombre ni en el cuerpo.

### 2. Filtrado por Nombre de Archivo
* **Método:** `moverBebidas(String tipo)`
* **Acción:** Utiliza un filtro evaluando los metadatos del intercambio. Lee los archivos de origen y solo procesa aquellos cuyo nombre empiece con el prefijo indicado (por ejemplo, `Bebidas`).

### 3. Filtrado por Contenido Interno
* **Método:** `moverBebidasConContenido(String contenido)`
* **Acción:** Convierte el cuerpo del mensaje entrante a texto plano (`String`) y evalúa si contiene una palabra clave en específico (ej. `"Coca"`). Si da positivo, guarda el archivo en el destino.

### 4. Transformador de Formato Plano a CSV Personalizado
* **Métodos:** `leerFichero()`, `procesarRegistros()` y `procesarEstadoRegistrosCSV()`
* **Acción:** Captura el contenido del fichero, procesa el texto mediante Java Streams dividiendo el texto por espacios en blanco (`" "`), y reconstruye el contenido separándolo por barras verticales (`|`) o comas (`,`), guardando el resultado final como un archivo estructurado (`records.csv` / `Pedido.csv`).

### 5. Splitter y Enrutador Avanzado Basado en Contenido (CBR)
* **Método:** `procesarEstadoRegistrosMultiplesCSV()`
* **Acción:** Desestructura el contenido CSV (`unmarshal().csv()`), fragmenta el archivo registro por registro (`split()`) utilizando comas y evalúa dinámicamente mediante condiciones (`choice().when()`) hacia qué archivo específico debe ir cada fila según las palabras clave de su estado:
  * Si contiene *"En carta"* ➡️ Va a `EnCarta.csv`
  * Si contiene *"Sin existencias"* ➡️ Va a `SinExistencias.csv`
  * Si contiene *"Pedido"* ➡️ Va a `Pedido.csv`

---

## 🚀 Cómo Ejecutar el Proyecto

1. **Configurar las rutas:** Modifica las variables estáticas `RECETAS_HOY_ORIGEN` y `RECETAS_HOY_DESTINO` (o las rutas relativas `ORIGEN` y `DESTINO`) de acuerdo a las carpetas locales de tu máquina.
2. **Preparar carpetas:** Asegúrate de que las carpetas existan en tu sistema o deja que Camel las cree automáticamente al arrancar.
3. **Compilar y Correr:** Ejecuta el proyecto desde tu IDE preferido (como STS, Eclipse o IntelliJ) o a través de la terminal con Maven:
   ```bash
   mvn spring-boot:run