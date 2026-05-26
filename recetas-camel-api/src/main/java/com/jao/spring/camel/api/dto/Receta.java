package com.jao.spring.camel.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Receta {
	private Long id;
	 

    private String nombre;
    private String ingredientes; // Luego podrías usar una relación @OneToMany
    private int tiempoPreparacion; // En minutos
    private String dificultad;
    private String fechaPublicacion;
    

}
