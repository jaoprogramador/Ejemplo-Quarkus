package repositories;

import entities.Receta;
import io.quarkiverse.groovy.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RecetaRepository implements PanacheRepository<Receta>{

}
