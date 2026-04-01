package entities;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Receta {
	
	 @Id
	 
	 @GeneratedValue(strategy = GenerationType.IDENTITY) 
	 private Long id;
	 

    private String nombre;
    private String ingredientes; // Luego podrías usar una relación @OneToMany
    private int tiempoPreparacion; // En minutos
    private String dificultad;
    private LocalDate fechaPublicacion;
    @CreationTimestamp
    private LocalDate fechaCreacion;
   
	@UpdateTimestamp
    private LocalDate fechaActuali;
	
	@ManyToOne // Muchas recetas -> Una categoría
    @JoinColumn(name = "categoria_id") // Nombre de la columna en la tabla Receta
    private Categoria categoria;
    
	
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

	public Receta(
			Long id,  String nombre, String ingredientes, int tiempoPreparacion, String dificultad,
			LocalDate fechaPublicacion,LocalDate fechaCreacion, LocalDate fechaActuali) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.ingredientes = ingredientes;
		this.tiempoPreparacion = tiempoPreparacion;
		this.dificultad = dificultad;
		this.fechaPublicacion = fechaPublicacion;
		this.fechaCreacion = fechaCreacion;
		this.fechaActuali = fechaActuali;
	}
	// 1. EL CONSTRUCTOR VACÍO (OBLIGATORIO)
    public Receta() {
    }
}
