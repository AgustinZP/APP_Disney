package com.AccesoDatos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.AccesoDatos.entity.Personaje;
import com.AccesoDatos.entity.PersonajeGuardado;
import com.AccesoDatos.entity.Usuario;
import com.AccesoDatos.repository.PersonajeGuardadoRepository;
import com.AccesoDatos.service.PersonajeGuardadoService;

@Service("personajeGuardadoServiceImpl")
public class PersonajeGuardadoServiceImpl implements PersonajeGuardadoService{
	
	@Autowired
	@Qualifier("personajeGuardadoRepository")
	private PersonajeGuardadoRepository personajeGuardadoRepository;

	@Override
	public List<PersonajeGuardado> obtenerPersonajesGuardados(Usuario usuario) {
		
		return personajeGuardadoRepository.findByUsuario(usuario);
	}

	@Override
	public void guardarPersonajeParaUsuario(Usuario usuario, Personaje personaje) {
		
		PersonajeGuardado personajeGuardado = new PersonajeGuardado();
		personajeGuardado.setUsuario(usuario);
		personajeGuardado.setPersonaje(personaje);
		personajeGuardadoRepository.save(personajeGuardado);
		
	}

	@Override
	public void guardarPersonaje(Personaje personaje) {
		
		//personajeGuardadoRepository.save(personaje);
		
	}

	 @Override
	    public PersonajeGuardado obtenerPersonajeGuardadoPorId(long id) {
	        return personajeGuardadoRepository.findById(id);
	    }

	@Override
	public PersonajeGuardado guardarPuntuacionPersonaje(PersonajeGuardado personajeGuardado,
			int nuevaPuntuacion) {
		if (personajeGuardado != null) {
			personajeGuardado.setPuntuacion(nuevaPuntuacion);
	        return personajeGuardadoRepository.save(personajeGuardado);
	    }
	    return null;
	}	

}
