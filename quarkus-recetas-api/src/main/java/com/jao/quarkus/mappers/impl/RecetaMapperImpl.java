package com.jao.quarkus.mappers.impl;

import com.jao.quarkus.dto.ActualizarRecetaDto;
import com.jao.quarkus.dto.CrearRecetaDto;
import com.jao.quarkus.entities.Receta;
import com.jao.quarkus.mappers.RecetaMapper;

import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class RecetaMapperImpl implements RecetaMapper{

	@Override
	public Receta desdeCrear(CrearRecetaDto dto) {
		var q = new Receta();
		q.setNombre(dto.nombre());
		q.setIngredientes(dto.ingredientes());
		q.setDificultad(dto.dificultad());
		q.setTiempoPreparacion(dto.tiempoPreparacion());
		q.setActivo(dto.activo());
		
		return q;
	}

	@Override
	public void actualizar(ActualizarRecetaDto dto, Receta receta) {
		
		receta.setNombre(dto.nombre());
		receta.setIngredientes(dto.ingredientes());
		receta.setDificultad(dto.dificultad());
		receta.setTiempoPreparacion(dto.tiempoPreparacion());
		receta.setActivo(dto.activo());
		
	}

}
