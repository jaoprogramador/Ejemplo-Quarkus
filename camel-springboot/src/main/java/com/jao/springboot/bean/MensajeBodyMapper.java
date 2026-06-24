package com.jao.springboot.bean;

import org.springframework.stereotype.Service;

@Service
public class MensajeBodyMapper {
	 public String map(String input) {
	        return input.toUpperCase();
	    }
}
