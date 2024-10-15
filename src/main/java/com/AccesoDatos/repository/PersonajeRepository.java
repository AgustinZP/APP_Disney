package com.AccesoDatos.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AccesoDatos.entity.Personaje;

@Repository("personajeRepository")
public interface PersonajeRepository extends JpaRepository<Personaje, Serializable>{
	
	//public abstract List<Personaje> findByName(String name);
	public abstract Personaje findById(int id);
	public abstract Personaje findByName(String name);

}
