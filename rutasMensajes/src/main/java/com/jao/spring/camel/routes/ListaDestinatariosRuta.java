package com.jao.spring.camel.routes;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class ListaDestinatariosRuta extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("jms:recipient.list.queue")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) {
                        String departamentos = exchange.getMessage().getHeader("departamentos", String.class);
                        List<String> whereToSend = new ArrayList<>();
                        if (departamentos != null) {
                            Arrays.asList(departamentos.split(",")).forEach(departamento -> 
                                whereToSend.add("jms:" + departamento + ".queue")
                            );
                        }
                        exchange.getMessage().setHeader("destinoEnvio", whereToSend);
                    }
                })
                .recipientList(header("destinoEnvio"));
    }
}