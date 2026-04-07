package com.jao.quarkus.repositories;

import com.jao.quarkus.entities.Receta;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RecetaRepository implements PanacheRepository<Receta>{

}
