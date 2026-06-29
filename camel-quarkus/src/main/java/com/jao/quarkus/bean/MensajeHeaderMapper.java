package com.jao.quarkus.bean;

import jakarta.enterprise.context.ApplicationScoped;

//import org.springframework.stereotype.Service;

//@Service
@ApplicationScoped
public class MensajeHeaderMapper {

    public String map(String input) {
        return input.toUpperCase();
    }
}
