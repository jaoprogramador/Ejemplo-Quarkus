package resources;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import dto.Temperatura;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import services.TemperaturasService;

@Path("/temperaturas")
public class TemperaturaResource {
	
	private TemperaturasService temperaturas;
	
	
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
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/maxima")
	public Response maxima() {
		if(temperaturas.isEmpty()) {
			return Response.status(404).entity("No hay temperaturas registradas").build();
		}else {
			int temperaturaMaxima= temperaturas.maxima();
			return Response.ok(temperaturaMaxima)
					.header("X-Hola", "Buenos días")
					.build();
		}
		//return Integer.toString(temperaturas.maxima());
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{ciudad}")
	public Temperatura getCiudad(@PathParam ("ciudad") String ciudad) {
		return temperaturas.getTemperatura(ciudad)
				.orElseThrow(() -> new NoSuchElementException("No hay registro para la ciudad "+ciudad, null));
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Temperatura> getAll() {
		return temperaturas.obtenerTemperaturas();
		
	}
	
	
}
