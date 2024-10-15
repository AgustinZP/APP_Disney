package com.AccesoDatos.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AccesoDatos.entity.Personaje;
import com.AccesoDatos.entity.TvShows;

@Repository("tvShowsRepository")
public interface TvShowsRepository extends JpaRepository<TvShows, Serializable>{
	
	public abstract List<TvShows> findByPersonaje(Personaje personaje);

}
