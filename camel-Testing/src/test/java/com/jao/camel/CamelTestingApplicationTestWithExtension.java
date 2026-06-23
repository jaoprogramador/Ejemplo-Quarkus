package com.jao.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.apache.camel.component.mock.MockEndpoint;

import com.jao.camel.route.RutaInicio;

class CamelTestingApplicationTestWithExtension {

	private static CamelContext camelContext;
	private static final String mockRutaParaArchivoSalida = "mock:ejemploRutaParaArchivoSalida";
	private static final String directRuteTest = "direct:textRutaInicio";

	
	@BeforeAll
	public static void setUp() throws Exception{
		
		camelContext = new DefaultCamelContext();
		camelContext.addRoutes(new RutaInicio());
		
		 AdviceWith.adviceWith(
	                camelContext,
	                RutaInicio.ROUTE_ID,
	                route -> route
	                        .interceptSendToEndpoint("file:src/main/resources/archivo/output")
	                        .skipSendToOriginalEndpoint()
	                        .to(mockRutaParaArchivoSalida)
	        );
		 
        AdviceWith.adviceWith(
                camelContext,
                RutaInicio.ROUTE_ID,
                route -> route
                        .replaceFromWith(directRuteTest)
        );

		
		
		camelContext.start();
		
	}

	@AfterAll
	public static void tearDown() throws Exception{
		
		camelContext.stop();
		
	}
	@Test
	void textRutaInicio() throws Exception {
		String testBody = "Hola JAO !!!";//"Bye World";
		MockEndpoint toFileOut = camelContext.getEndpoint(mockRutaParaArchivoSalida, MockEndpoint.class);
		toFileOut.expectedMessageCount(1);
		toFileOut.message(0).body().isEqualTo(testBody.toUpperCase());
		
		 ProducerTemplate producer = camelContext.createProducerTemplate();

	     producer.sendBody(directRuteTest, testBody);

		toFileOut.assertIsSatisfied();
	}
}
