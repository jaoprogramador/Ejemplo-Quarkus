package com.jao.springboot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Map;
import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.activemq.ArtemisContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(properties = {
	    "camel.springboot.main-run-controller=false"
	})
@Testcontainers // Activa el ciclo de vida automático de los contenedores Docker
public class RutaJmsIntegrationTest {

    // 1. Levantamos un contenedor real de ActiveMQ Artemis en Docker
    @Container
    private static final ArtemisContainer artemis = new ArtemisContainer("apache/activemq-artemis:2.35.0-alpine")
            .withUser("artemis")
            .withPassword("artemis")
    		.withStartupAttempts(3);

    // 2. Inyectamos la URL dinámica que Docker le asigne al broker en las propiedades de Spring
    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.artemis.broker-url", artemis::getBrokerUrl);
    }

    // Spring Boot inyecta de forma automática los templates de Camel ya configurados
    @Autowired
    private ProducerTemplate producerTemplate;

    @Autowired
    private ConsumerTemplate consumerTemplate;

    @Autowired
    private CamelContext camelContext;

    @Test
    void testRutaJmsCompleta() throws Exception {
        // Preparación de datos
        String testBody = "kaixo jao jms";
        String testHeaderVal = "inicio";
        
        // Nombres de colas mapeados desde tus propiedades
        String inputQueue = "jms:mensaje-input";
        String outputQueue = "jms:mensaje-output";

        // 3. Enviamos el mensaje a la cola JMS de entrada con un Header específico
        producerTemplate.sendBodyAndHeaders(inputQueue, testBody, Map.of("jao-header", testHeaderVal));

        // 4. Consumimos el mensaje resultante de la cola JMS de salida (esperamos un máximo de 3 segundos)
        // Usamos el tipo Exchange para poder extraer tanto el Body como los Headers transformados
        var exchangeResult = consumerTemplate.receive(outputQueue, 3000);

        // 5. Validaciones (Aserciones)
        assertNotNull(exchangeResult, "El mensaje no llegó a la cola de salida.");
        
        String bodyFinal = exchangeResult.getMessage().getBody(String.class);
        String headerFinal = exchangeResult.getMessage().getHeader("jao-header", String.class);

        // Verificamos que los mappers hayan hecho su trabajo pasándolo todo a MAYÚSCULAS
        assertEquals("KAIXO JAO JMS", bodyFinal);
        assertEquals("INICIO", headerFinal);
    }
}