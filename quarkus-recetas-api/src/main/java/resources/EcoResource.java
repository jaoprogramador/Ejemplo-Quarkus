package resources;

import java.util.Optional;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;

@Path("/saludar")
public class EcoResource {
	
	@GET
	public String saludar(@QueryParam("usuario") String usuario) {
		/*
		 * if(usuario==null) { return "El usuario es null"; }else return "Egun on " +
		 * usuario;
		 */
		return Optional
				.ofNullable(usuario)
				.map(t -> "Egun on "+ usuario)
				.orElse("El usuario es null");
	}
	
	@GET
	@Path("/{nombre}")
	public String saludo(@PathParam ("nombre") String nombre) {
		return "Hola "+nombre;
	}
	@GET
	@Path("/{nombre}/mayusculas")
	public String gritar(@PathParam ("nombre") String nombre) {
		return "Hola ".toUpperCase()+nombre.toUpperCase();
	}
	
	@GET
	@Path("/dias")
	public String dias() {
		return "Egun on";
	}
	@GET
	@Path("/noches")
	public String noches() {
		return "Gabon";
	}
	@GET
	@Path("/tardes")
	public String tardes() {
		return "Arratsalde on";
	}
	

}
