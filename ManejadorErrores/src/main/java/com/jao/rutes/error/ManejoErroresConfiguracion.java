package com.jao.rutes.error;

import org.apache.camel.builder.RouteConfigurationBuilder;

public class ManejoErroresConfiguracion extends RouteConfigurationBuilder{

	@Override
	public void configuration() throws Exception {
		routeConfiguration("manejo_de_errores").
		onException(Exception.class)
        
			.log("ExceptionJAO::ManejoErrores: ${exception}")
			.maximumRedeliveries(2)
			.redeliveryDelay(300);
		
	}
}
