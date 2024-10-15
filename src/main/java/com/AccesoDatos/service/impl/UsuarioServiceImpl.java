package com.AccesoDatos.service.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.AccesoDatos.entity.Usuario;
import com.AccesoDatos.repository.PersonajeCompartidoRepository;
import com.AccesoDatos.repository.UsuarioRepository;
import com.AccesoDatos.service.UsuarioService;

@Service("usuarioServiceImpl")
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	@Qualifier("usuarioRepository")
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	@Qualifier("personajeCompartidoRepository")
	private PersonajeCompartidoRepository personajeCompartidoRepository;

	@Override
	public List<Usuario> mostrarUsuarios() {
		
		List<Usuario> usuarios = usuarioRepository.findAll();
		
		return usuarios;
	}

	@Override
	public Usuario guardarUsuario(Usuario usuario) {
		
		return usuarioRepository.save(usuario);
	}

	@Override
	public Usuario encontrarPorEmail(String email) {
		Usuario usuario = usuarioRepository.findByEmail(email);
		Hibernate.initialize(usuario.getPersonajesCompartidos());
		return usuarioRepository.findByEmail(email);
	}

	@Override
	public Usuario cargarPersonajesCompartidos(Usuario usuario) {
		usuario.setPersonajesCompartidos(personajeCompartidoRepository.findByUsuarioCompartio(usuario));
		return usuario;
	}

}
