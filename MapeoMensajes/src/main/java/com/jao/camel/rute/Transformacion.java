package com.jao.camel.rute;

import org.apache.camel.builder.RouteBuilder;

public class Transformacion extends RouteBuilder {
    @Override
    public void configure() {
        
    	from("file:src/main/resources/archivos?fileName=recetas-italianas-transformacion.txt&noop=true")
    		.log("Body: ${body}, Headers: ${headers}")
	        .transform(body().regexReplaceAll("Pasta", "Espaguetis a la Carbonara RARO"))
	        .transform(simple("<request>${body}</request>"))
	        .to("file:src/main/resources/archivos/output?fileName=recetas-italianas-transformacion.txt"); 
	                
    }
}