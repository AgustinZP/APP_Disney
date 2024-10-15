package com.AccesoDatos.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AccesoDatos.entity.Films;
import com.AccesoDatos.entity.Personaje;

@Repository("filmsRepository")
public interface FilmsRepository extends JpaRepository<Films, Serializable>{
	
	public abstract List<Films> findByPersonaje(Personaje personaje);

}
