package com.jao.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

import com.jao.camel.bean.beanMapper;
import com.jao.camel.rute.Bean;
import com.jao.camel.rute.Procesador;
import com.jao.camel.rute.Transformacion;


public class MapeoMensajesApplication {

	 public static void main(String[] args) throws Exception {
	        try (CamelContext camelContext = new DefaultCamelContext()) {
	            camelContext.addRoutes(new Procesador());
	            camelContext.addRoutes(new Bean());
	            camelContext.addRoutes(new Transformacion());
	            camelContext.getRegistry().bind("mi-modificacion", new beanMapper());
	            
	            camelContext.start();
	            Thread.sleep(2000);
	            camelContext.stop();
	        }
	    }

}
