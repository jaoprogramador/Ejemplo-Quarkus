package com.jao.quarkus.dto;

public record ActualizarRecetaDto(
		String nombre,
	     String ingredientes,
	     int tiempoPreparacion,
	     String dificultad,
	     String activo
		) {

}
