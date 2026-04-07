package com.jao.quarkus.mappers;

import com.jao.quarkus.dto.ActualizarRecetaDto;
import com.jao.quarkus.dto.CrearRecetaDto;
import com.jao.quarkus.entities.Receta;

public interface RecetaMapper {
	Receta desdeCrear (CrearRecetaDto dto);

	void actualizar(ActualizarRecetaDto dto, Receta receta);
}
