package com.jao.spring.camel.routes;

import org.apache.camel.builder.RouteBuilder;

public class EleccionRuta  extends RouteBuilder {
    @Override
    public void configure() {
        from("jms:input.MailQueue")
                .log("Body: ${body}, Headers: ${headers}")
                .choice()
                .when(exchange -> {
                    Object requestType = exchange.getMessage().getHeader("requestType");
                    if (requestType != null) {
                        return "statement".equals(requestType);
                    }
                    return false;
                })
                    .to("jms:statement.MailQueue")
                .when(simple("${header.requestType} == 'paymentDetails'"))
                    .to("jms:request.details.MailQueue")
                .otherwise()
                    .to("jms:unrecognised.queue")
                .end()
                .log("Processing ended.")
        ;
    }
}