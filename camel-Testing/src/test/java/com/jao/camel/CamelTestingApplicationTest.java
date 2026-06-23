package com.jao.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.infra.core.CamelContextExtension;
import org.apache.camel.test.infra.core.DefaultCamelContextExtension;
import org.apache.camel.test.infra.core.annotations.ContextFixture;
import org.apache.camel.test.infra.core.annotations.RouteFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import com.jao.camel.route.RutaInicio;

class CamelTestingApplicationTest {
	
	@RegisterExtension
	protected static CamelContextExtension camelContextExtension = new DefaultCamelContextExtension();
	private static final String mockRutaParaArchivoSalida = "mock:ejemploRutaParaArchivoSalida";
	private static final String directRuteTest = "direct:textRutaInicio";

	
	@ContextFixture
	public void setUp(CamelContext camelContext) throws Exception{
		
		
		
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

	@RouteFixture
	public void addRoute(CamelContext camelContext) throws Exception{
		
		camelContext.addRoutes(new RutaInicio());
		
	}
	@Test
	void textRutaInicio() throws Exception {
		String testBody = "Hola JAO !!!";//"Bye World";
		MockEndpoint toFileOut = camelContextExtension.getMockEndpoint(mockRutaParaArchivoSalida);
		toFileOut.expectedMessageCount(1);
		toFileOut.message(0).body().isEqualTo(testBody.toUpperCase());
		
		 ProducerTemplate producer = camelContextExtension.getProducerTemplate();

	     producer.sendBody(directRuteTest, testBody);

		toFileOut.assertIsSatisfied();
	}
}
