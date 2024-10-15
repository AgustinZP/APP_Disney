package com.AccesoDatos.service;

import java.util.List;

import com.AccesoDatos.entity.Personaje;
import com.AccesoDatos.entity.TvShows;

public interface TvShowsService {
	
	public abstract List<TvShows> buscarPorPersonajeRelacionado(Personaje personaje);
	public abstract TvShows guardarVideoGame(TvShows tvShows);

}
