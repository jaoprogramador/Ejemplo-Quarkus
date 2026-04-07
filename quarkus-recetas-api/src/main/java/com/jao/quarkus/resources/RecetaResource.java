package com.jao.quarkus.resources;

import java.util.List;
import java.util.NoSuchElementException;

import com.jao.quarkus.dto.ActualizarRecetaDto;
import com.jao.quarkus.dto.CrearRecetaDto;
import com.jao.quarkus.entities.Receta;
import com.jao.quarkus.mappers.RecetaMapper;
import com.jao.quarkus.repositories.RecetaRepository;

import io.quarkus.panache.common.Sort;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/recetas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RecetaResource {

    @Inject
    RecetaRepository recetaRepository;
    
    
	 
	 @Inject
	 RecetaMapper recetaMapper;
	 
	 @Inject
	 public RecetaResource(RecetaRepository recetaRepository,RecetaMapper recetaMapper) {
		 this.recetaRepository=recetaRepository;
		 this.recetaMapper=recetaMapper;
	 }

 	@POST
    @Transactional
    public Response crear(@Valid CrearRecetaDto receta) {
 		var entity = recetaMapper.desdeCrear(receta);
    	//receta.setId(null);
    	recetaRepository.persist(entity);
        return Response.status(Response.Status.CREATED).entity(receta).build();
    }
    @PUT
    @Path("/{id}")
    @Transactional
    public Response actualizar(@PathParam("id") Long id, ActualizarRecetaDto receta) {
    	var recetaActualizar = recetaRepository.findById(id);
    	if(recetaActualizar != null) {
			/*
			 * recetaActualizar.setNombre(receta.getNombre());
			 * recetaActualizar.setIngredientes(receta.getIngredientes());
			 * recetaActualizar.setTiempoPreparacion(receta.getTiempoPreparacion());
			 * recetaActualizar.setDificultad(receta.getDificultad());
			 * recetaActualizar.setFechaPublicacion(receta.getFechaPublicacion());
			 */
    		recetaMapper.actualizar(receta, recetaActualizar);
    		
    		return Response.ok(recetaActualizar).build();
    	}
    	throw new NoSuchElementException("No har recetas con el id="+id);
    	
        
    }
	    
    @GET
    @Path("/tiempo")
    public List<Receta> listarTodasMas30min(@QueryParam("tiempoPreparacion") Integer tiempoPreparacion) {
    	if(tiempoPreparacion == null) {
            return recetaRepository.listAll(Sort.descending("fechaPublicacion"));

    	}else {
    		return recetaRepository.list("tiempoPreparacion >= ?1 ", tiempoPreparacion );
    		//return recetaRepository.list("tiempoPreparacion >= 35" );
    	}
        
    }
    @GET
    @Path("/nombre")
    public List<Receta> listarPorNombre(@QueryParam("q") String nombre) {
    	if(nombre == null) {
            return recetaRepository.listAll();

    	}else {
    		String sql ="%" + nombre+"%";
    		return recetaRepository.list("nombre ILIKE ?1 OR ingredientes ILIKE ?2", sql, sql );
    		//return recetaRepository.list("tiempoPreparacion >= 35" );
    	}
        
    }
    
	/*
	 * @GET public List<Receta> listarTodas() { return recetaRepository.listAll(); }
	 */

    @GET
    @Path("/{id}")
    public Receta obtenerPorId(@PathParam("id") Long id) {
        var receta = recetaRepository.findById(id);
        if (receta == null) {
            throw new NoSuchElementException("Receta no encontrada con el id "+id);
        }
        return receta;
    }

    

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response eliminar(@PathParam("id") Long id) {
        boolean eliminado = recetaRepository.deleteById(id);
        if (!eliminado) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }
}
