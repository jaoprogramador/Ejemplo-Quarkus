package com.jao.spring.camel.api.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jao.spring.camel.api.dto.Receta;
import com.jao.spring.camel.api.service.RecetaService;
@Component
public class RecetaProcessor implements Processor{
	
	@Autowired
	private RecetaService recetaService;
	
	@Override
	public void process(Exchange exchange) throws Exception {
		recetaService.aniadirReceta(exchange.getIn().getBody(Receta.class));
		
	}

}
