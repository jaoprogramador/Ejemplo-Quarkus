package resources;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dto.Temperatura;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/temperaturas")
public class TemperaturaResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/una")
	public Temperatura medicion() {
		return new Temperatura("Bilbao", 12, 23);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Temperatura> getAll() {
		
		return Arrays.asList(
				new Temperatura("Bilbao", 12, 23),
				new Temperatura("Donosti", 15, 25),
				new Temperatura("Gazteiz", 5, 23));
	}
}
