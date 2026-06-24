package com.jao.springboot.routes;


import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class RutaJMS extends RouteBuilder {

    public static final String ID = "RutaJMS";

    @Override
    public void configure() {
        from("jms:{{jao.queue.input.name}}")
                .routeId(ID)
                .log("RutaJMS::Recivida request: ${body}")
                .bean("TransformarMensajeJAO")
                .to("jms:{{jao.queue.output.name}}");
    }
}