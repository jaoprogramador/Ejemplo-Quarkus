package com.jao.recetas;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;


@QuarkusTest
public class RecetaTest {
	@Test
    public void testListarTodasLasRecetas() {
        given()
          .when().get("/recetas/tiempo")
          .then()
             .statusCode(200)
             .contentType(ContentType.JSON)
             .body("$", hasSize(greaterThanOrEqualTo(1))); // Verifica que al menos hay una receta
    }

    @Test
    public void testFiltrarPorTiempo() {
        given()
          .queryParam("tiempoPreparacion", 30)
          .when().get("/recetas/tiempo")
          .then()
             .statusCode(200)
             .body("tiempoPreparacion", everyItem(greaterThanOrEqualTo(30)));
    }

    @Test
    public void testObtenerPorIdExistente() {
        given()
          .pathParam("id", 1)
          .when().get("/recetas/{id}")
          .then()
             .statusCode(200)
             .body("id", is(1))
             .body("nombre", notNullValue());
    }

    @Test
    public void testObtenerPorIdNoExistente() {
        given()
          .pathParam("id", 999)
          .when().get("/recetas/{id}")
          .then()
             .statusCode(500); // Según tu código, lanzas NoSuchElementException que por defecto da 500
    }

    @Test
    public void testBuscarPorNombre() {
        given()
          .queryParam("q", "Pizza")
          .when().get("/recetas/nombre")
          .then()
             .statusCode(200)
             .body("[0].nombre", equalTo("Pizza"));
    }

    @Test
    public void testCrearReceta() {
        String nuevaReceta = """
            {
                "nombre": "Tortilla",
                "ingredientes": "Huevos, patatas, cebolla",
                "tiempoPreparacio": 40,
                "dificultad": "Media"
            }
            """;

        given()
          .contentType(ContentType.JSON)
          .body(nuevaReceta)
          .when().post("/recetas")
          .then()
             .statusCode(201)
             .body("nombre", is("Tortilla"));
    }
	

}
