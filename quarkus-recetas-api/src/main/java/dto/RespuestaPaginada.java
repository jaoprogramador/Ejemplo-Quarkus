package dto;

import java.util.List;
import io.quarkus.hibernate.orm.panache.PanacheQuery;



public record RespuestaPaginada<E> ( int pagina, int totalPaginas,List<E> datos){
	
	public RespuestaPaginada (PanacheQuery query) {
		this( query.page().index + 1, query.pageCount(),query.list());
	}
}
