package com.jao.spring.camel.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jao.spring.camel.api.dto.Receta;

import jakarta.annotation.PostConstruct;

@Service
public class RecetaService {
	
	private List <Receta> lista = new ArrayList();
	
	@PostConstruct
	public void initDB() {
		
		   
		lista.add(new Receta(1L, "Rape", "Rape, aceite, ajos, perejil, sal, vinagre, patatas", 25, "BAJA", "2026-05-24"));
		lista.add(new Receta(2L, "Tortilla de Patatas", "Patatas, huevos, cebolla, aceite de oliva, sal", 40, "MEDIA", "2026-05-26"));
		lista.add(new Receta(3L, "Paella Valenciana", "Arroz, pollo, conejo...", 60, "ALTA", "2026-01-15"));
		lista.add(new Receta(4L, "Salmorejo Cordobés", "Tomates maduros, pan...", 15, "BAJA", "2026-03-10"));
		lista.add(new Receta(5L, "Lasaña de Carne", "Placas de pasta, carne...", 50, "MEDIA", "2026-02-28"));
		lista.add(new Receta(6L, "Croquetas de Jamón", "Jamón serrano, harina...", 90, "ALTA", "2026-04-05"));
		lista.add(new Receta(7L, "Salmón al Horno con Verduras", "Lomos de salmón...", 20, "BAJA", "2026-05-26"));
		lista.add(new Receta(8L, "Risotto de Setas", "Arroz arborio, setas...", 35, "MEDIA", "2026-05-01"));
		lista.add(new Receta(9L, "Crema de Calabaza", "Calabaza, patata...", 30, "BAJA", "2026-05-20"));
		lista.add(new Receta(10L, "Tarta de Queso la Viña", "Queso crema, nata...", 55, "MEDIA", "2026-05-25"));
		
	}
	
	public Receta aniadirReceta (Receta receta) {
		lista.add(receta);
		return receta;
	}
	
	public List <Receta> getRecetas() {
		return lista;
	}

}
