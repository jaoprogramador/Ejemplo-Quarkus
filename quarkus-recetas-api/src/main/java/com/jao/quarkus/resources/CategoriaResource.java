package com.jao.quarkus.resources;

import java.net.URI;
import java.util.NoSuchElementException;

import com.jao.quarkus.dto.ActualizarCategoriaDto;
import com.jao.quarkus.dto.CategoriaValidador;
import com.jao.quarkus.dto.CrearCategoriaDto;
import com.jao.quarkus.dto.RespuestaPaginada;
import com.jao.quarkus.entities.Categoria;
import com.jao.quarkus.mappers.CategoriaMapper;
import com.jao.quarkus.repositories.CategoriaRepository;

import io.quarkus.panache.common.Parameters;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

@Path("/categorias")

public class CategoriaResource {
	
	 @Inject
	 CategoriaRepository categoriasRepos;
	 
	 @Inject
	 CategoriaMapper categoriaMapper;
	 
	 @Inject
	 CategoriaValidador categoriaValidador;
	 
	 @Inject
	 public CategoriaResource(CategoriaRepository categoriasRepos,CategoriaMapper categoriaMapper) {
		 this.categoriasRepos=categoriasRepos;
		 this.categoriaMapper=categoriaMapper;
	 }
	 
 	@POST
    @Transactional
    public Response create( CrearCategoriaDto categoria) {
    	var error =this.categoriaValidador.validaCategoria(categoria);
    	if (error.isPresent()) {
    		var msg = error.get();
    		return Response.status(400).entity(msg).build();
    	}
    	
 		var entity = categoriaMapper.desdeCrear(categoria);
    	categoriasRepos.persist(entity);
        return Response.created(URI.create("/categorias/" + entity.getId()))
                       .entity(categoria)
                       .build();
    }
    
    @PUT
    @Path("/{id}")
    @Transactional
    public Categoria update(@PathParam("id") Long id, ActualizarCategoriaDto categoria) {
    	
        Categoria entity = categoriasRepos
                .findByIdOptional(id)
                .orElseThrow(() -> new NoSuchElementException("Categoria " + id + " no encontrada"));
        categoriaMapper.actualizar(categoria, entity);
        categoriasRepos.persist(entity);
        // Si añadiste descripción o icono, actualízalos aquí también:
        // entity.setDescripcion(categoria.getDescripcion());
        
        return entity;
    }
 	@GET
    public RespuestaPaginada<Categoria> list(@QueryParam("pagina") @DefaultValue("1") int page,
    		@QueryParam("origen") String queryOrigen
    		) {
	 	var query = categoriasRepos.findPage(page);
	 	if(queryOrigen!=null) {
	 		var origelLike = "%"+queryOrigen+"%";
	 		query.filter("origen.like",Parameters.with("origen", origelLike));
	 	}
	 	
	 	
	 	return new RespuestaPaginada<Categoria>(query);
	 			
    }

   

    @GET
    @Path("/{id}")
    public Categoria get(@PathParam("id") Long id) {
        return categoriasRepos
                .findByIdOptional(id)
                .orElseThrow(() -> new NoSuchElementException("Categoria " + id + " no encontrada"));
    }

	    

}
