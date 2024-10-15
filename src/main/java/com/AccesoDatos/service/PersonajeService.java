package com.AccesoDatos.service;

import java.util.List;

import com.AccesoDatos.entity.Personaje;

public interface PersonajeService {
	
	public abstract List<Personaje> mostrarPersonajes();
	public abstract Personaje guardarPersonaje(Personaje personaje);
	//public abstract List<Personaje> buscarPersonajePorNombre(String nombre);
	public abstract Personaje buscarPorId(int id);
	public abstract Personaje buscarPorNombre(String nombre);

}
