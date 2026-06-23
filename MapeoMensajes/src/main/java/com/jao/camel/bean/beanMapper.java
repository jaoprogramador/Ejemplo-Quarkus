package com.jao.camel.bean;

import org.apache.camel.Exchange;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class beanMapper {
	public void transform(Exchange exchange) {
		ObjectNode body = exchange.getMessage().getBody(ObjectNode.class);
    	body.put("nuevoIngredienteExtra", "Pimienta negra");
        exchange.getMessage().setBody(body);
	}
}
