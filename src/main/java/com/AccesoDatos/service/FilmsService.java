package com.AccesoDatos.service;

import java.util.List;

import com.AccesoDatos.entity.Films;
import com.AccesoDatos.entity.Personaje;

public interface FilmsService {
	
	public abstract List<Films> buscarPorPersonajeRelacionado(Personaje personaje);
	
	public abstract Films guardarFilms(Films film);

}
