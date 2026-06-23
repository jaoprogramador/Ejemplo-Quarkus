package com.jao.camel;

import org.apache.camel.builder.AdviceWith;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;

import com.jao.camel.route.RutaInicio;

class CamelTestingApplicationTestsWithSupport extends CamelTestSupport{

	
	private static final String mockRutaParaArchivoSalida = "mock:ejemploRutaParaArchivoSalida";
	private static final String directRuteTest = "direct:textRutaInicio";

	
	@Override
	public void beforeTestExecution(ExtensionContext context) throws Exception{
		
		super.beforeTestExecution(context);

		
		 AdviceWith.adviceWith(
				 	context(),
	                RutaInicio.ROUTE_ID,
	                route -> route
	                        .interceptSendToEndpoint("file:src/main/resources/archivo/output")
	                        .skipSendToOriginalEndpoint()
	                        .to(mockRutaParaArchivoSalida)
	        );
		 
        AdviceWith.adviceWith(
        		context(),
                RutaInicio.ROUTE_ID,
                route -> route
                        .replaceFromWith(directRuteTest)
        );

	}
	 @Override
	protected RouteBuilder createRouteBuilder() {
		return new RutaInicio();
	}
	
	@Test
	void textRutaInicio() throws Exception {
		String testBody = "Hola JAO !!!";//"Bye World";
		MockEndpoint toFileOut = getMockEndpoint(mockRutaParaArchivoSalida);
		toFileOut.expectedMessageCount(1);
		toFileOut.message(0).body().isEqualTo(testBody.toUpperCase());
		
		 

	     template.sendBody(directRuteTest, testBody);

		toFileOut.assertIsSatisfied();
	}
}
