package com.AccesoDatos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.AccesoDatos.entity.Films;
import com.AccesoDatos.entity.Personaje;
import com.AccesoDatos.repository.FilmsRepository;
import com.AccesoDatos.service.FilmsService;

@Service("FilmsServiceImpl")
public class FilmsServiceImpl implements FilmsService{
	
	@Autowired
	@Qualifier("filmsRepository")
	private FilmsRepository filmsRepository;

	@Override
	public List<Films> buscarPorPersonajeRelacionado(Personaje personaje) {
		
		return filmsRepository.findByPersonaje(personaje);
	}

	@Override
	public Films guardarFilms(Films film) {
		
		return filmsRepository.save(film);
	}
	
	

}
