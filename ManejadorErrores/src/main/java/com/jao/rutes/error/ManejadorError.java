package com.jao.rutes.error;

import org.apache.camel.builder.RouteBuilder;

public class ManejadorError extends RouteBuilder{

	@Override
	public void configure() throws Exception {
        from("timer:ticker?period=1s")
                
                .setBody(simple("Hola JAO::ManejadorError "))
                .doTry()
	                .process(exchange -> {
	                    throw new RuntimeException("JAO ERROR exception");
	                })
	            .doCatch(Exception.class)
	            .log("Catch Exception ${exception}")
	            .doFinally()
	            .log("Finally : ${exception}")
                .log("Fin ruta con body: ${body}");
	}

}
