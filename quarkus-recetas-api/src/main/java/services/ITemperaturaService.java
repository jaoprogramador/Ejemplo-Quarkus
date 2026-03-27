package services;

import java.util.List;
import java.util.Optional;

import dto.Temperatura;

public interface ITemperaturaService {

	void addTemperatura(Temperatura temp);

	List<Temperatura> obtenerTemperaturas();

	int maxima();

	boolean isEmpty();

	Optional<Temperatura> getTemperatura(String ciudad);

}
