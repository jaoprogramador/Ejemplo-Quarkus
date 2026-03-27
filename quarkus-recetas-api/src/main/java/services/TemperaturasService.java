package services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dto.Temperatura;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TemperaturasService {
	
	private List<Temperatura> valores = new ArrayList<>();
	
	public void addTemperatura(Temperatura temp) {
		valores.add(temp);
	}

	public List<Temperatura> obtenerTemperaturas(){
		return Collections.unmodifiableList(valores);
	}
	
	public int maxima(){
		//return valores.stream().mapToInt(Temperatura::getMaxima ).max().getAsInt();
		return valores.stream()
                .mapToInt(Temperatura::getMaxima)
                .max()
                .orElse(0);
	}
	
	

}
