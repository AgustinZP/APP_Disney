package com.AccesoDatos.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.AccesoDatos.entity.PersonajeGuardado;
import com.AccesoDatos.entity.Usuario;

@Repository("personajeGuardadoRepository")
public interface PersonajeGuardadoRepository extends JpaRepository<PersonajeGuardado, Serializable>{
	
	public abstract List<PersonajeGuardado> findByUsuario(Usuario usuario);
	public abstract PersonajeGuardado findById(long id);

}
