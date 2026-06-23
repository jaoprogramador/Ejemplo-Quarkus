package com.jao.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

import com.jao.camel.route.RutaInicio;
import com.jao.camel.route.RutaInicioKafka;


public class CamelTestingApplication {

	public static void main(String[] args) {
		try {
			CamelContext camelContext = new DefaultCamelContext();
			//camelContext.addRoutes(new RutaInicio());
			camelContext.addRoutes(new RutaInicioKafka());
			camelContext.start();
			Thread.sleep(2000);
			
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
