package com.AccesoDatos.service;

import java.util.List;

import com.AccesoDatos.entity.Usuario;

public interface UsuarioService {
	
	public abstract List<Usuario> mostrarUsuarios();
	public abstract Usuario guardarUsuario(Usuario usuario);
	public abstract Usuario encontrarPorEmail(String email);
	public abstract Usuario cargarPersonajesCompartidos(Usuario usuario);
}
