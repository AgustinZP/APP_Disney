package com.AccesoDatos.service;

import java.util.List;

import com.AccesoDatos.entity.Personaje;
import com.AccesoDatos.entity.PersonajeCompartido;
import com.AccesoDatos.entity.PersonajeGuardado;
import com.AccesoDatos.entity.Usuario;


public interface PersonajeGuardadoService {
	
	public abstract List<PersonajeGuardado> obtenerPersonajesGuardados(Usuario usuario);

	public abstract void guardarPersonajeParaUsuario(Usuario usuario, Personaje personaje);

	public abstract void guardarPersonaje(Personaje personaje);
	
	public abstract  PersonajeGuardado obtenerPersonajeGuardadoPorId(long id);
	
	public abstract  PersonajeGuardado guardarPuntuacionPersonaje(PersonajeGuardado personajeGuardado, int nuevaPuntuacion);

}
