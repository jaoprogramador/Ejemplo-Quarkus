package entities;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@FilterDef(name = "origen.like",
		   parameters = @ParamDef(name = "origen", type = String.class))
@Filter(name = "origen.like", condition ="LOWER(origen) LIKE LOWER(:origen)")
//@Filter(name = "origen.like", condition ="origen LIKE :origen")
//@Filter(name = "nombre.like", condition ="nombre = :nombre")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

	private String nombre;
    
    private String origen;
    @CreationTimestamp
    private LocalDate fechaCreacion;
   
	@UpdateTimestamp
    private LocalDate fechaActuali;

	
	public Categoria(Long id, String nombre, String origen, LocalDate fechaCreacion, LocalDate fechaActuali) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.origen = origen;
		this.fechaCreacion = fechaCreacion;
		this.fechaActuali = fechaActuali;
	}
	public Categoria() {
    }
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public LocalDate getFechaActuali() {
		return fechaActuali;
	}

	public void setFechaActuali(LocalDate fechaActuali) {
		this.fechaActuali = fechaActuali;
	}
    
}
