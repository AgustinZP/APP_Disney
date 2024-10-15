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
@Table(name="peliculas")
public class Films {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pelicula_id")
	private long filmId;
	
	private String titulo;
	
	@ManyToOne
	@JoinColumn(name="personaje_id")
	private Personaje personaje;

	public Films() {
		super();
		
	}

	public Films(long filmId, String titulo, Personaje personaje) {
		super();
		this.filmId = filmId;
		this.titulo = titulo;
		this.personaje = personaje;
	}

	public long getFilmId() {
		return filmId;
	}

	public void setFilmId(long filmId) {
		this.filmId = filmId;
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
		return "Films [filmId=" + filmId + ", titulo=" + titulo + ", personaje=" + personaje
				+ "]";
	}
	
	

}
