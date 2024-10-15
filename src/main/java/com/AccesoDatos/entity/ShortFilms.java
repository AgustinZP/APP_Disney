package com.AccesoDatos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="cortos")
public class ShortFilms {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="corto_id")
	private long shortFilmId;
	
	private String titulo;
	
	@ManyToOne
	@JoinColumn(name="personaje_id")
	private Personaje personaje;

	public ShortFilms() {
		super();
		
	}

	public ShortFilms(long shortFilmId, String titulo, Personaje personaje) {
		super();
		this.shortFilmId = shortFilmId;
		this.titulo = titulo;
		this.personaje = personaje;
	}

	public long getShortFilmId() {
		return shortFilmId;
	}

	public void setShortFilmId(long shortFilmId) {
		this.shortFilmId = shortFilmId;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Personaje getPersonaje() {
		return personaje;
	}

	public void setPersonaje(Personaje personaje) {
		this.personaje = personaje;
	}

	@Override
	public String toString() {
		return "ShortFilms [shortFilmId=" + shortFilmId + ", titulo=" + titulo + ", personaje="
				+ personaje + "]";
	}
	
	
}
