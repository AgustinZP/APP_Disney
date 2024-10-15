package com.AccesoDatos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.AccesoDatos.entity.Personaje;
import com.AccesoDatos.repository.PersonajeRepository;
import com.AccesoDatos.service.PersonajeService;

@Service("personajeServiceImpl")
public class PersonajeServiceImpl implements PersonajeService{
	
	@Autowired
	@Qualifier("personajeRepository")
	private PersonajeRepository personajeRepository;

	@Override
	public List<Personaje> mostrarPersonajes() {
		
		return personajeRepository.findAll();
	}

	@Override
	public Personaje guardarPersonaje(Personaje personaje) {
		
		return personajeRepository.save(personaje);
	}

	@Override
	public Personaje buscarPorId(int id) {
		
		return personajeRepository.findById(id);
	}

	@Override
	public Personaje buscarPorNombre(String nombre) {
		
		return personajeRepository.findByName(nombre);
	}

	
}
