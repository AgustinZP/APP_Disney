package com.AccesoDatos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.AccesoDatos.entity.Personaje;
import com.AccesoDatos.entity.TvShows;
import com.AccesoDatos.repository.TvShowsRepository;
import com.AccesoDatos.service.TvShowsService;

@Service("tvShowsServiceImpl")
public class TvShowsServiceImpl implements TvShowsService{
	
	@Autowired
	@Qualifier("tvShowsRepository")
	private TvShowsRepository tvShowsRepository;

	@Override
	public List<TvShows> buscarPorPersonajeRelacionado(Personaje personaje) {
		
		return tvShowsRepository.findByPersonaje(personaje);
	}

	@Override
	public TvShows guardarVideoGame(TvShows tvShows) {
		
		return tvShowsRepository.save(tvShows);
	}

}
