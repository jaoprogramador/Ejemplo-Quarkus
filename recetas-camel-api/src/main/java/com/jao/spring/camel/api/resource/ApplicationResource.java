package com.jao.spring.camel.api.resource;



import org.apache.camel.BeanInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.jao.spring.camel.api.dto.Receta;
import com.jao.spring.camel.api.processor.RecetaProcessor;
import com.jao.spring.camel.api.service.RecetaService;

@Component
public class ApplicationResource extends RouteBuilder{
	@Autowired
	private RecetaService recetaService;
	
	@BeanInject
	private RecetaProcessor recetaProcessor;

	@Override
	public void configure() throws Exception {
		//restConfiguration().component("servlet").port(9090).host("localhost").bindingMode(RestBindingMode.json);
		//rest().get("/hellow-JAO").produces(MediaType.APPLICATION_JSON_VALUE).route().setBody(constant("Wellcome to JAO"));
		
		restConfiguration()
		.component("servlet")
		.bindingMode(RestBindingMode.json); // Deja que Camel gestione el puerto automáticamente con Spring
		
		// Declaramos la raíz común para limpiar el buffer de salida de Chrome
		rest("/api")
			.get("/hellow-JAO")
				.produces(MediaType.APPLICATION_JSON_VALUE)
				.to("direct:saludo")
			
			.get("/getRecetas")
				.produces(MediaType.APPLICATION_JSON_VALUE)
				.to("direct:recetas")
		
			.post("/addReceta")
			.type(Receta.class) // <-- IMPORTANTE: Le dice a Camel que transforme el JSON entrante en esta clase Java
			.consumes(MediaType.APPLICATION_JSON_VALUE)
			.produces(MediaType.APPLICATION_JSON_VALUE)
			.to("direct:guardarReceta");
			
			
			
		// ---- RUTAS INTERNAS ----
		from("direct:saludo")
			.setBody(constant("Welcome to JAO recetas API Camel"));
			
		
		from("direct:recetas")
	    .setBody().method(recetaService, "getRecetas")
	    .log("Recetas enviadas al cliente.");
		
		// Ruta para guardar la receta (POST) con tu Processor
		from("direct:guardarReceta")
			.log("Petición POST recibida en Camel.")
			.process(recetaProcessor) 
			.log("Procesador ejecutado y receta añadida con éxito.");
		    

	}

}
