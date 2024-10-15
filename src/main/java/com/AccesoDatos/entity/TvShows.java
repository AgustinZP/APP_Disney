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
@Table(name="programas_television")
public class TvShows {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="programa_id")
	private long tvShowId;
	
	private String titulo;
	
	@ManyToOne
	@JoinColumn(name="personaje_id")
	private Personaje personaje;

	public TvShows() {
		super();
		
	}

	public TvShows(long tvShowId, String titulo, Personaje personaje) {
		super();
		this.tvShowId = tvShowId;
		this.titulo = titulo;
		this.personaje = personaje;
	}

	public long getTvShowId() {
		return tvShowId;
	}

	public void setTvShowId(long tvShowId) {
		this.tvShowId = tvShowId;
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
	    return "TvShows [tvShowId=" + tvShowId + ", titulo=" + titulo + "]";
	}


}
