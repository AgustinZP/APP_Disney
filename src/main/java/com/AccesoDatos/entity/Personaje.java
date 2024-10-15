package com.AccesoDatos.entity;

import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="personajes")
public class Personaje {
	
	@Id
	@Column(name="personaje_id")
	private int id;
	
	private String name;
	
	private String imageUrl;
	
	private String sourceUrl;
	
	@OneToMany(mappedBy = "personaje")
	@Cascade(CascadeType.ALL)
	List<Films> films;
	
	@OneToMany(mappedBy = "personaje")
	@Cascade(CascadeType.ALL)
	List<ShortFilms> shortFilms;
	
	@OneToMany(mappedBy = "personaje")
	@Cascade(CascadeType.ALL)
	List<TvShows> tvShows;
	
	@OneToMany(mappedBy = "personaje")
	@Cascade(CascadeType.ALL)
	List<VideoGames> videoGames;
	
	@OneToMany(mappedBy = "personaje")
	@Cascade(CascadeType.ALL)
	List<ParkAttractions> parkAttractions;

	public Personaje() {
		super();	
	}

	public Personaje(int id, String name, String imageUrl, String sourceUrl, List<Films> films,
			List<ShortFilms> shortFilms, List<TvShows> tvShows, List<VideoGames> videoGames,
			List<ParkAttractions> parkAttractions) {
		super();
		this.id = id;
		this.name = name;
		this.imageUrl = imageUrl;
		this.sourceUrl = sourceUrl;
		this.films = films;
		this.shortFilms = shortFilms;
		this.tvShows = tvShows;
		this.videoGames = videoGames;
		this.parkAttractions = parkAttractions;
	}

	public int getId() {
		return id;
	}

	public void setId(int personajeId) {
		this.id = personajeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public List<Films> getFilms() {
		return films;
	}

	public void setFilms(List<Films> films) {
		this.films = films;
	}

	public List<ShortFilms> getShortFilms() {
		return shortFilms;
	}

	public void setShortFilms(List<ShortFilms> shortFilms) {
		this.shortFilms = shortFilms;
	}

	public List<TvShows> getTvShows() {
		return tvShows;
	}

	public void setTvShows(List<TvShows> tvShows) {
		this.tvShows = tvShows;
	}

	public List<VideoGames> getVideoGames() {
		return videoGames;
	}

	public void setVideoGames(List<VideoGames> videoGames) {
		this.videoGames = videoGames;
	}

	public List<ParkAttractions> getParkAttractions() {
		return parkAttractions;
	}

	public void setParkAttractions(List<ParkAttractions> parkAttractions) {
		this.parkAttractions = parkAttractions;
	}

	@Override
	public String toString() {
	    return "Personaje [personajeId=" + id + ", name=" + name + "]";
	}
}
