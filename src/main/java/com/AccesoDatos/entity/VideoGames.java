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
@Table(name="video_juegos")
public class VideoGames {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="videojuego_id")
	private long videoGameId;
	
	private String titulo;
	
	@ManyToOne
	@JoinColumn(name="personaje_id")
	private Personaje personaje;

	public VideoGames() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VideoGames(long videoGameId, String titulo, Personaje personaje) {
		super();
		this.videoGameId = videoGameId;
		this.titulo = titulo;
		this.personaje = personaje;
	}

	public long getVideoGameId() {
		return videoGameId;
	}

	public void setVideoGameId(long videoGameId) {
		this.videoGameId = videoGameId;
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
		return "VideoGames [videoGameId=" + videoGameId + ", titulo=" + titulo + ", personajeRelacionado="
				+ personaje + "]";
	}
	
	

}
