package com.jao.quarkus.mappers;

import com.jao.quarkus.dto.ActualizarCategoriaDto;
import com.jao.quarkus.dto.CrearCategoriaDto;
import com.jao.quarkus.entities.Categoria;

public interface CategoriaMapper {
	Categoria desdeCrear (CrearCategoriaDto dto);

	void actualizar(ActualizarCategoriaDto dto, Categoria categoria);
	
	
}
