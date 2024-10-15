package com.AccesoDatos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;


@Entity
@Table(name = "personajes_guardados")
public class PersonajeGuardado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "personaje_guardado_id")
	private long id;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "personaje_id")
	private Personaje personaje;
	
	@Column
	@Min(0)
	@Max(5)
	private int puntuacion;

	public PersonajeGuardado() {
		super();
	}

	public PersonajeGuardado(long id, Usuario usuario, Personaje personaje) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.personaje = personaje;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Personaje getPersonaje() {
		return personaje;
	}

	public void setPersonaje(Personaje personaje) {
		this.personaje = personaje;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

}
