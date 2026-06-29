package com.jao.quarkus.routes;


import org.apache.camel.builder.RouteBuilder;
//import org.springframework.stereotype.Component;

//@Component
public class RutaJMS extends RouteBuilder {

    public static final String ID = "RutaJMS";

    @Override
    public void configure() {
    	//En caso de tener muchas conexiones habría que indicar cual usar
    	//from("jms:{{jao.queue.input.name}}?connectionFactory=myConnectionFactory")
    	from("jms:{{jao.queue.input.name}}")
	        .routeId(ID)
	        .log("RutaJMS::Recibida request: ${body}")
	        .bean("TransformarMensajeJAO")
	        .to("jms:{{jao.queue.output.name}}");
    }
}