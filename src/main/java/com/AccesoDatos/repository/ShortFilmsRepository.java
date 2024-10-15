package com.AccesoDatos.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AccesoDatos.entity.Personaje;
import com.AccesoDatos.entity.ShortFilms;

@Repository("shortFilmsRepository")
public interface ShortFilmsRepository extends JpaRepository<ShortFilms, Serializable>{
	
	public abstract List<ShortFilms> findByPersonaje(Personaje personaje);

}
