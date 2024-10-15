package com.AccesoDatos.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AccesoDatos.entity.PersonajeCompartido;
import com.AccesoDatos.entity.PersonajeGuardado;
import com.AccesoDatos.entity.Usuario;

@Repository("personajeCompartidoRepository")
public interface PersonajeCompartidoRepository extends JpaRepository<PersonajeCompartido, Serializable>{
	
	public abstract List<PersonajeCompartido> findByUsuarioCompartio(Usuario usuarioCompartio);
	public abstract PersonajeCompartido findById(long id);
}
