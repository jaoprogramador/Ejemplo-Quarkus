package com.jao.quarkus.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.jao.quarkus.dto.Temperatura;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TemperaturasService implements ITemperaturaService{
	
	private List<Temperatura> valores = new ArrayList<>();
	
	@Override
	public void addTemperatura(Temperatura temp) {
		valores.add(temp);
	}
	@Override
	public List<Temperatura> obtenerTemperaturas(){
		return Collections.unmodifiableList(valores);
	}
	@Override
	public int maxima(){
		
		return valores.stream()
                .mapToInt(Temperatura::getMaxima)
                .max()
                .orElse(0);
	}
	
	@Override
	public boolean isEmpty() {
		return valores.isEmpty();
		
	}
	
	@Override
	public Optional<Temperatura> getTemperatura(String ciudad) {
		return valores.stream().filter(t -> t.getCiudad().equals(ciudad))
				.findAny();
	}
	
	

}
