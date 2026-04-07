# 🍲 API de Recetas y Categorías (Quarkus)

Este proyecto es un microservicio desarrollado con Quarkus para la gestión de recetas de cocina y sus categorías. Utiliza Hibernate Panache para la persistencia y MapStruct para el mapeo de DTOs.

## 🚀 Endpoints Principales

### 📖 Recetas (/recetas)

| Método | Endpoint | Descripción | Parámetros |
| :--- | :--- | :--- | :--- |
| POST | /recetas | Crea una nueva receta | @Valid CrearRecetaDto |
| GET | /recetas/tiempo | Lista recetas filtradas por tiempo | tiempoPreparacion (opcional) |
| GET | /recetas/nombre | Busca por nombre o ingredientes | q (texto a buscar) |
| GET | /recetas/{id} | Obtiene una receta por su ID | id (path) |
| PUT | /recetas/{id} | Actualiza una receta existente | id (path), ActualizarRecetaDto |
| DELETE | /recetas/{id} | Elimina una receta | id (path) |

#### Notas de Recetas:
- Filtro de Tiempo: Si no se envía tiempoPreparacion, devuelve todas ordenadas por fecha de publicación descendente. Si se envía, filtra por tiempo >= al valor indicado.
- Búsqueda: El endpoint /nombre realiza una búsqueda ILIKE (no distingue mayúsculas) tanto en el nombre como en los ingredientes.

### 📂 Categorías (/categorias)

| Método | Endpoint | Descripción | Parámetros |
| :--- | :--- | :--- | :--- |
| POST | /categorias | Crea una categoría | CrearCategoriaDto |
| GET | /categorias | Listado paginado de categorías | pagina (default 1), origen |
| GET | /categorias/{id} | Obtiene categoría por ID | id (path) |
| PUT | /categorias/{id} | Actualiza una categoría | id (path), ActualizarCategoriaDto |

#### Notas de Categorías:
- Paginación: El listado devuelve un objeto RespuestaPaginada.
- Filtros: Permite filtrar por el campo origen usando coincidencias parciales (LIKE).
- Validación: Incluye un CategoriaValidador personalizado antes de la persistencia.

## 🛠️ Tecnologías utilizadas

- Java 17
- Quarkus Framework (REST, Hibernate Panache, Jackson)
- Flyway: Gestión de migraciones de base de datos.
- MapStruct: Mapeo de entidades a DTOs.
- H2 Database: Base de datos en memoria (entorno dev/test).

## 📋 Requisitos Previos

- JDK 17 o superior.
- Maven 3.8.1+.

## 🏃 Ejecución en Local

Para levantar el proyecto en modo desarrollo con Live Coding:

```bash
./mvnw quarkus:dev
El servicio estará disponible en: http://localhost:8080
La consola de Dev UI en: http://localhost:8080/q/dev

🧪 Tests
Para ejecutar la batería de pruebas unitarias y de integración:
./mvnw test
