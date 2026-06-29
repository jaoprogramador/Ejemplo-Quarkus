package com.jao.quarkus.bean;

import jakarta.enterprise.context.ApplicationScoped;

//port org.springframework.stereotype.Service;

//@Service
@ApplicationScoped
public class MensajeBodyMapper {
	 public String map(String input) {
	        return input.toUpperCase();
	    }
}
