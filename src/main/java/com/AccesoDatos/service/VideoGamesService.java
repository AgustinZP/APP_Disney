package com.AccesoDatos.service;

import java.util.List;

import com.AccesoDatos.entity.Personaje;
import com.AccesoDatos.entity.VideoGames;

public interface VideoGamesService {
	
	public abstract List<VideoGames> buscarPorPersonajeRelacionado(Personaje personaje);
	public abstract VideoGames guardarVideoGame(VideoGames videoGame);

}
