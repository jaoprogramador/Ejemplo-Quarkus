package resources;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import dto.Temperatura;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import services.TemperaturasService;

@Path("/temperaturas")
public class TemperaturaResource {
	
	private TemperaturasService temperaturas;
	
	//private List<Temperatura> valores = new ArrayList<Temperatura>();
	@Inject
	public TemperaturaResource(TemperaturasService temperaturas) {
		this.temperaturas= temperaturas;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	
	public Temperatura add(Temperatura temp) {
		temperaturas.addTemperatura(temp);
		return temp;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/una")
	public Temperatura medicion() {
		return new Temperatura("Bilbao", 12, 23);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/maxima")
	public String maxima() {
		return temperaturas.maxima()+"";
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Temperatura> getAll() {
		return temperaturas.obtenerTemperaturas();
		//return getAll();
		//return Collections.unmodifiableList(valores);
		/*
		 * return Arrays.asList( new Temperatura("Bilbao", 12, 23), new
		 * Temperatura("Donosti", 15, 25), new Temperatura("Gazteiz", 5, 23));
		 */
	}
	
	
}
