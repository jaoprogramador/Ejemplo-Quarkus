package com.jao.rutes.error;

public class EnExcepcionError extends ManejadorError{

	@Override
	public void configure() throws Exception {
        
    	from("timer:ticker?period=1s")
            .routeConfigurationId("manejo_de_errores")
	        .setBody(simple("Hola JAO::EnExcepcionError "))
	        
	            .process(exchange -> {
	                throw new RuntimeException("EnExcepcionError::JAO ERROR exception");
	            })
	        .log("Fin ruta con body: ${body}");
    	
    	from("timer:ticker_2?period=1s")
    		.routeConfigurationId("manejo_de_errores")
        	.setBody(simple("Hola JAO::EnExcepcionError "))
        
            .process(exchange -> {
                throw new RuntimeException("EnExcepcionError::JAO ERROR exception");
            })
        .log("Fin ruta con body: ${body}");
	}

}
