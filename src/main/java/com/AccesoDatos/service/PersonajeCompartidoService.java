package com.AccesoDatos.service;

import java.util.List;

import com.AccesoDatos.entity.PersonajeCompartido;
import com.AccesoDatos.entity.PersonajeGuardado;
import com.AccesoDatos.entity.Usuario;
import com.AccesoDatos.repository.PersonajeCompartidoRepository;

public interface PersonajeCompartidoService {
	
	public abstract List<PersonajeCompartido> mostrarPersonajeCompartidosPorUsuario(Usuario usuarioCompartio);
    
    public abstract PersonajeCompartido guardarPersonajeCompartido(PersonajeCompartido personajeCompartido);
    
    public abstract  PersonajeCompartido obtenerPersonajeCompartidoPorId(long id);
    
    public abstract  PersonajeCompartido guardarPuntuacionPersonajeCompartido(PersonajeCompartido personajeCompartido, int nuevaPuntuacion);

}
