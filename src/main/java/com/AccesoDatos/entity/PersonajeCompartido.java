package com.AccesoDatos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "personajes_compartidos")
public class PersonajeCompartido {

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuarioCompartio;
	
	@ManyToOne
    @JoinColumn(name = "usuario_que_compartio_id")
    private Usuario usuarioQueCompartio;

	@ManyToOne
	@JoinColumn(name = "personaje_id")
	private Personaje personaje;
	
	@Column
	private int puntuacion;

	public PersonajeCompartido() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PersonajeCompartido(Usuario usuarioCompartio, Usuario usuarioQueCompartio, Personaje personaje) {
		super();
		this.usuarioCompartio = usuarioCompartio;
		this.usuarioQueCompartio = usuarioQueCompartio;
		this.personaje = personaje;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Usuario getUsarioCompartio() {
		return usuarioCompartio;
	}

	public void setUsuarioCompartio(Usuario usuarioCompartio) {
		this.usuarioCompartio = usuarioCompartio;
	}

	public Personaje getPersonaje() {
		return personaje;
	}

	public void setPersonaje(Personaje personaje) {
		this.personaje = personaje;
	}

	public Usuario getUsuarioQueCompartio() {
		return usuarioQueCompartio;
	}

	public void setUsuarioQueCompartio(Usuario usuarioQueCompartio) {
		this.usuarioQueCompartio = usuarioQueCompartio;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	@Override
	public String toString() {
		return "PersonajeCompartido [id=" + id + ", usuarioCompartio=" + usuarioCompartio + ", usuarioQueCompartio="
				+ usuarioQueCompartio + ", personaje=" + personaje + "]";
	}

}
