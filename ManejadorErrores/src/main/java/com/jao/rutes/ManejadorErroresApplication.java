package com.jao.rutes;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

import com.jao.rutes.error.EnExcepcionError;
import com.jao.rutes.error.ManejoErroresConfiguracion;


public class ManejadorErroresApplication {

	 public static void main(String[] args) throws Exception {
	        try (CamelContext camelContext = new DefaultCamelContext()) {

	            //camelContext.addRoutes(new ManejadorError());
	        	//camelContext.addRoutes(new EnExcepcionError());
	        	camelContext.addRoutes(new ManejoErroresConfiguracion());
	            camelContext.start();
	            Thread.sleep(2000);
	        }
	    }
	}
