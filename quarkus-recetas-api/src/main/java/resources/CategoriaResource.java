package resources;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

import dto.RespuestaPaginada;
import entities.Categoria;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import repositories.CategoriaRepository;

@Path("/categorias")

public class CategoriaResource {
	
	 @Inject
	 CategoriaRepository categorias;
	 
	 @GET
	    public RespuestaPaginada<Categoria> list(@QueryParam("pagina") @DefaultValue("1") int page,
	    		@QueryParam("origen") String queryOrigen
	    		) {
		 	var query = categorias.findPage(page);
		 	if(queryOrigen!=null) {
		 		var origelLike = "%"+queryOrigen+"%";
		 		query.filter("origen.like",Parameters.with("origen", origelLike));
		 	}
		 	
		 	
		 	return new RespuestaPaginada<Categoria>(query);
		 			
	    }

	    @POST
	    @Transactional
	    public Response create(Categoria categoria) {
	        categorias.persist(categoria);
	        return Response.created(URI.create("/categorias/" + categoria.getId()))
	                       .entity(categoria)
	                       .build();
	    }

	    @GET
	    @Path("/{id}")
	    public Categoria get(@PathParam("id") Long id) {
	        return categorias
	                .findByIdOptional(id)
	                .orElseThrow(() -> new NoSuchElementException("Categoria " + id + " no encontrada"));
	    }

	    @PUT
	    @Path("/{id}")
	    @Transactional
	    public Categoria update(@PathParam("id") Long id, Categoria categoria) {
	        Categoria entity = categorias
	                .findByIdOptional(id)
	                .orElseThrow(() -> new NoSuchElementException("Categoria " + id + " no encontrada"));
	        
	        entity.setNombre(categoria.getNombre());
	        // Si añadiste descripción o icono, actualízalos aquí también:
	        // entity.setDescripcion(categoria.getDescripcion());
	        
	        return entity;
	    }

}
