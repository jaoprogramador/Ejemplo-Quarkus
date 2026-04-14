package com.jao.quarkus.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CrearRecetaDto(
		@NotBlank
		 String nombre,
	     String ingredientes,
	     @Min(10)
	     int tiempoPreparacion,
	     String dificultad,
	     String activo
		) {

}
