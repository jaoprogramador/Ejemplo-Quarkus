package com.jao.quarkus.dto;

import jakarta.validation.constraints.NotBlank;

public record CrearCategoriaDto(
		@NotBlank
		String nombre, String origen
		) {
		
		
	

}
