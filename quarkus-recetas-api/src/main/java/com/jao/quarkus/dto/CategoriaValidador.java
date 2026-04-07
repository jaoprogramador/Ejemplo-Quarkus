package com.jao.quarkus.dto;

import java.util.Optional;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Validator;

@ApplicationScoped
public class CategoriaValidador {
	
	Validator validador;
	
	@Inject
	public CategoriaValidador(Validator validador) {
		this.validador= validador;
	}
	
	public Optional<Object> validaCategoria (CrearCategoriaDto categoria) {
		
		var errors = validador.validate(categoria);
		if(errors.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(errors.stream().findFirst().get().getMessage());
		
	}

}
