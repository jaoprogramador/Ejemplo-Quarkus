package com.jao.camel.route;

import org.apache.camel.builder.RouteBuilder;

public class RutaInicio extends RouteBuilder{
	public static final String ROUTE_ID = "ruta-hola";

	@Override
	public void configure() throws Exception {
		from("file:src/main/resources/archivo?noop=true")
       .routeId(ROUTE_ID)
        .process(exchange -> exchange.getMessage().setBody(
                exchange.getIn().getBody(String.class).toUpperCase()))
        .to("file:src/main/resources/archivo/output");

	}

}
