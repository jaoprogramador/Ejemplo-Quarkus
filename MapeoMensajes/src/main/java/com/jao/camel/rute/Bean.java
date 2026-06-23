package com.jao.camel.rute;

import org.apache.camel.builder.RouteBuilder;

public class Bean extends RouteBuilder {
    @Override
    public void configure() {
        
    	from("file:src/main/resources/archivos?fileName=receta-carbonara-bean.json&noop=true")
	        .unmarshal().json()
    		.log("Body: ${body}, Headers: ${headers}")
	        .bean("mi-modificacion")
	        .marshal().json()
	        .to("file:src/main/resources/archivos/output?fileName=receta-carbonar-bean.json"); 
	                
    }
}