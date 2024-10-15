package com.AccesoDatos.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AccesoDatos.entity.Personaje;
import com.AccesoDatos.entity.VideoGames;

@Repository("videoGamesRepository")
public interface VideoGamesRepository extends JpaRepository<VideoGames, Serializable>{
	
	public abstract List<VideoGames> findByPersonaje(Personaje personaje);

}
