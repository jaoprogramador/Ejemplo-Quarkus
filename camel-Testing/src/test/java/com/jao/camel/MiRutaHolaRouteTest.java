package com.jao.camel;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

import com.jao.camel.route.RutaInicio;
public class MiRutaHolaRouteTest extends CamelTestSupport{
	@Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        // Registramos la ruta que queremos probar
        return new RutaInicio();
    }

	@Test
	public void testTransformacionMayusculas() throws Exception {
	    // 1. Modificar la estructura de la ruta ANTES de que arranque el contexto
	    AdviceWith.adviceWith(context, "ruta-hola", a -> {
	        a.replaceFromWith("direct:start");
	        // Interceptamos cualquier endpoint que contenga la palabra "file"
	        a.mockEndpointsAndSkip("file*"); 
	    });

	    // 2. Ahora que la ruta está modificada, arrancamos el contexto de Camel manualmente
	    context.start();

	    // 3. Obtener el endpoint mock que Camel crea automáticamente al interceptar la ruta
	    // Nota: El prefijo correcto que genera Camel suele ser "mock:file:..."
	    MockEndpoint mockOutput = getMockEndpoint("mock:file:src/main/resources/archivo/output");
	    
	    mockOutput.expectedMessageCount(1);
	    mockOutput.expectedBodiesReceived("HOLA JAO!!!"); // Asegúrate de que coincide con lo que hace tu Processor

	    // 4. Enviar el mensaje a través del punto de entrada modificado
	    template.sendBody("direct:start", "hola JAO!!!");

	    // 5. Verificar que se cumplen las condiciones del Mock sin esperar los 10 segundos
	    mockOutput.assertIsSatisfied();
	}
}
