package com.AccesoDatos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.AccesoDatos.entity.PersonajeCompartido;
import com.AccesoDatos.entity.PersonajeGuardado;
import com.AccesoDatos.entity.Usuario;
import com.AccesoDatos.repository.PersonajeCompartidoRepository;
import com.AccesoDatos.service.PersonajeCompartidoService;

@Service("personajeCompartidoServiceImpl")
public class PersonajeCompartidoServiceImpl implements PersonajeCompartidoService{
	
	@Autowired
	@Qualifier("personajeCompartidoRepository")
	private PersonajeCompartidoRepository personajeCompartidoRepository;

	@Override
	public List<PersonajeCompartido> mostrarPersonajeCompartidosPorUsuario(Usuario usuarioCompartio) {
		
		return personajeCompartidoRepository.findByUsuarioCompartio(usuarioCompartio);
	}

	@Override
	public PersonajeCompartido guardarPersonajeCompartido(PersonajeCompartido personajeCompartido) {
		
		return personajeCompartidoRepository.save(personajeCompartido);
	}
	
	@Override
    public PersonajeCompartido obtenerPersonajeCompartidoPorId(long id) {
        return personajeCompartidoRepository.findById(id);
    }

	@Override
	public PersonajeCompartido guardarPuntuacionPersonajeCompartido(PersonajeCompartido personajeCompartido, int nuevaPuntuacion) {
	    if (personajeCompartido != null) {
	        personajeCompartido.setPuntuacion(nuevaPuntuacion);
	        return personajeCompartidoRepository.save(personajeCompartido);
	    }
	    return null;
	}


	

	

	

}
