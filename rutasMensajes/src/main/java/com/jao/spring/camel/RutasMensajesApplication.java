package com.jao.spring.camel;
import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.qpid.jms.JmsConnectionFactory;

import com.jao.spring.camel.routes.EleccionRuta;
import com.jao.spring.camel.routes.ListaDestinatariosRuta;

import jakarta.jms.ConnectionFactory;


public class RutasMensajesApplication {
    public static void main(String[] args) throws Exception {
        try (CamelContext camelContext = new DefaultCamelContext()) {
           // camelContext.addRoutes(new EleccionRuta());
            camelContext.addRoutes(new ListaDestinatariosRuta());
            JmsComponent jms = camelContext.getComponent("jms", JmsComponent.class);
            jms.setConnectionFactory(createConnectionFactory());

            camelContext.start();
            Thread.sleep(2000000);
        }
    }

    private static ConnectionFactory createConnectionFactory() {
        JmsConnectionFactory connectionFactory = new JmsConnectionFactory("amqp://localhost:61616");
        connectionFactory.setUsername("artemis");
        connectionFactory.setPassword("artemis");
        return connectionFactory;

    }
}