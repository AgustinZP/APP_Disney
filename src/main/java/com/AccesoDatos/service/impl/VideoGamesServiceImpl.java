package com.AccesoDatos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.AccesoDatos.entity.Personaje;
import com.AccesoDatos.entity.VideoGames;
import com.AccesoDatos.repository.VideoGamesRepository;
import com.AccesoDatos.service.VideoGamesService;

@Service("videoGamesServiceImpl")
public class VideoGamesServiceImpl implements VideoGamesService{
	
	@Autowired
	@Qualifier("videoGamesRepository")
	private VideoGamesRepository videoGamesRepository;

	@Override
	public List<VideoGames> buscarPorPersonajeRelacionado(Personaje personaje) {
		
		return videoGamesRepository.findByPersonaje(personaje);
	}

	@Override
	public VideoGames guardarVideoGame(VideoGames videoGame) {
		
		return videoGamesRepository.save(videoGame);
	}

}
