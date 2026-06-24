package com.jao.springboot.routes;



import org.apache.camel.builder.RouteBuilder;

public class RutaDirect extends RouteBuilder {

    public static final String ID = "DirectRoute";

    @Override
    public void configure() {
        from("direct:jao-direct-route")
                .routeId(ID)
                .log("RutaDirect:::Recibido request: ${body}");
    }


}
