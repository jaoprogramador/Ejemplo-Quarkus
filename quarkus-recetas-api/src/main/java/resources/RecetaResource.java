package resources;

import entities.Receta;
//import repositories.RecetaRepository;
//simport jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/recetas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RecetaResource {

    //@Inject
    //RecetaRepository recetaRepository;

    @GET
    public List<Receta> listarTodas() {
        return Receta.listAll();
    }

    @GET
    @Path("/{id}")
    public Receta obtenerPorId(@PathParam("id") Long id) {
        Receta receta = Receta.findById(id);
        if (receta == null) {
            throw new WebApplicationException("Receta no encontrada", Response.Status.NOT_FOUND);
        }
        return receta;
    }

    @POST
    @Transactional
    public Response crear(Receta receta) {
        Receta.persist(receta);
        return Response.status(Response.Status.CREATED).entity(receta).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response eliminar(@PathParam("id") Long id) {
        boolean eliminado = Receta.deleteById(id);
        if (!eliminado) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }
}
