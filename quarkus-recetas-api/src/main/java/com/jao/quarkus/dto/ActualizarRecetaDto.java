package com.jao.quarkus.dto;

public record ActualizarRecetaDto(
		String nombre,
	     String ingredientes,
	     int tiempoPreparacio,
	     String dificultad,
	     String activo
		) {

}
