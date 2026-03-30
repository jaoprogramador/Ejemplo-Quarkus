package entities;

import java.time.LocalDate;

import io.quarkiverse.groovy.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Receta extends PanacheEntity{
	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
	 */

    private String nombre;
    private String ingredientes; // Luego podrías usar una relación @OneToMany
    private int tiempoPreparacion; // En minutos
    private String dificultad;
    private LocalDate fechaPublicacion;
    
	/*
	 * public Long getId() { return id; }
	
	public void setId(Long id) {
		this.id = id;
	} */
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getIngredientes() {
		return ingredientes;
	}
	public void setIngredientes(String ingredientes) {
		this.ingredientes = ingredientes;
	}
	public int getTiempoPreparacion() {
		return tiempoPreparacion;
	}
	public void setTiempoPreparacion(int tiempoPreparacion) {
		this.tiempoPreparacion = tiempoPreparacion;
	}
	public String getDificultad() {
		return dificultad;
	}
	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}
	public LocalDate getFechaPublicacion() {
		return fechaPublicacion;
	}
	public void setFechaPublicacion(LocalDate fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public Receta(
			/* Long id, */ String nombre, String ingredientes, int tiempoPreparacion, String dificultad,
			LocalDate fechaPublicacion) {
		super();
		//this.id = id;
		this.nombre = nombre;
		this.ingredientes = ingredientes;
		this.tiempoPreparacion = tiempoPreparacion;
		this.dificultad = dificultad;
		this.fechaPublicacion = fechaPublicacion;
	}
	// 1. EL CONSTRUCTOR VACÍO (OBLIGATORIO)
    public Receta() {
    }
}
