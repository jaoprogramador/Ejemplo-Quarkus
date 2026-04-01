package repositories;

import entities.Categoria;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
@ApplicationScoped
public class CategoriaRepository implements PanacheRepository<Categoria>{
	
	public PanacheQuery<Categoria> findPage(int page){
		
		Page pagina= new Page(page -1, 5);
	 	var query = findAll(Sort.descending("nombre"));
	 	query.page(pagina);
	 	return query;
	}
}
