package com.AccesoDatos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.AccesoDatos.entity.Personaje;
import com.AccesoDatos.entity.ShortFilms;
import com.AccesoDatos.repository.ShortFilmsRepository;
import com.AccesoDatos.service.ShortFilmsService;

@Service("shortFilmsServiceImpl")
public class ShortFilmsServiceImpl implements ShortFilmsService{
	
	@Autowired
	@Qualifier("shortFilmsRepository")
	private ShortFilmsRepository shortFilmsRepository;

	@Override
	public List<ShortFilms> buscarPorPersonajeRelacionado(Personaje personaje) {
		
		return shortFilmsRepository.findByPersonaje(personaje);
	}

	@Override
	public ShortFilms guardarShortFilms(ShortFilms shortFilm) {
		
		return shortFilmsRepository.save(shortFilm);
	}

}
