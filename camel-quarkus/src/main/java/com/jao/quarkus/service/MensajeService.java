package com.jao.quarkus.service;

import org.apache.camel.ProducerTemplate;
//import org.springframework.stereotype.Service;

import jakarta.enterprise.context.ApplicationScoped;

//@Service
@ApplicationScoped
public class MensajeService {
	private final ProducerTemplate producerTemplate;

    public MensajeService(ProducerTemplate producerTemplate) {
        this.producerTemplate = producerTemplate;
    }

    public void doStuff() {
        producerTemplate.sendBody("direct:jao-direct-route", "Kaixo JAO");
    }
}
