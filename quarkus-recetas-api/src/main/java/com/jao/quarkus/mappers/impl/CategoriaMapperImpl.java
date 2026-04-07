package com.jao.quarkus.mappers.impl;

import com.jao.quarkus.dto.ActualizarCategoriaDto;
import com.jao.quarkus.dto.CrearCategoriaDto;
import com.jao.quarkus.entities.Categoria;
import com.jao.quarkus.mappers.CategoriaMapper;

import jakarta.enterprise.context.RequestScoped;
@RequestScoped
public class CategoriaMapperImpl implements CategoriaMapper{

	@Override
	public Categoria desdeCrear(CrearCategoriaDto dto) {
		var q = new Categoria();
		q.setNombre(dto.nombre());
		q.setOrigen(dto.origen());
		return q;
	}

	@Override
	public void actualizar(ActualizarCategoriaDto dto, Categoria categoria) {
		categoria.setNombre(dto.name());
		
	}

}
