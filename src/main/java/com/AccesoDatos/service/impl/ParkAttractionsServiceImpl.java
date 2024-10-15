package com.AccesoDatos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.AccesoDatos.entity.ParkAttractions;
import com.AccesoDatos.entity.Personaje;
import com.AccesoDatos.repository.ParkAttractionsRepository;
import com.AccesoDatos.service.ParkAttractionsService;

@Service("parkAttractionsServiceImpl")
public class ParkAttractionsServiceImpl implements ParkAttractionsService{
	
	@Autowired
	@Qualifier("parkAttractionsRepository")
	private ParkAttractionsRepository parkAttractionsRepository;

	@Override
	public List<ParkAttractions> buscarPorPersonajeRelacionado(Personaje personaje) {
		
		return parkAttractionsRepository.findByPersonaje(personaje);
	}

	@Override
	public ParkAttractions guardarParkAttraction(ParkAttractions parkAttraction) {
		
		return parkAttractionsRepository.save(parkAttraction);
	}

}
