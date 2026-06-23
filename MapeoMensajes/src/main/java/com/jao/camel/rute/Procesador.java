package com.jao.camel.rute;

import org.apache.camel.builder.RouteBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Procesador extends RouteBuilder {
    @Override
    public void configure() {
        //from("file:src/main/resources/archivos?fileName=recetas-italianas.txt&noop=true")
    	from("file:src/main/resources/archivos?fileName=receta-carbonara.json&noop=true")
	        .unmarshal().json()
    		.log("Body: ${body}, Headers: ${headers}")
	        .process(exchange -> {
	            //String body = exchange.getMessage().getBody(String.class);
	            //String bodyModificado = body.toUpperCase();
	        	ObjectNode body = exchange.getMessage().getBody(ObjectNode.class);
	        	body.put("nuevoIngredienteExtra", "Chorizo");
	            //ObjectMapper mapper = new ObjectMapper();
	            //ObjectNode node = mapper.readValue(body, ObjectNode.class);
	            //node.put("nuevoIngrediente", "Nata");
	            //exchange.getMessage().setBody(bodyModificado);
	            //exchange.getMessage().setBody(node.toPrettyString());
	            exchange.getMessage().setBody(body);
	        }) 
	        .marshal().json()
	        .to("file:src/main/resources/archivos/output?fileName=receta-carbonara.json"); 
	                
    }
}