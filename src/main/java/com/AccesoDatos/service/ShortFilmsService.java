package com.AccesoDatos.service;

import java.util.List;

import com.AccesoDatos.entity.Personaje;
import com.AccesoDatos.entity.ShortFilms;

public interface ShortFilmsService {
	
	public abstract List<ShortFilms> buscarPorPersonajeRelacionado(Personaje personaje);
	public abstract ShortFilms guardarShortFilms(ShortFilms shortFilm);

}
