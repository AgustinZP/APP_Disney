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
@Table(name="parque_atracciones")
public class ParkAttractions {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="parque_id")
	private long id;
	
	private String nombreParque;
	
	@ManyToOne
	@JoinColumn(name="personaje_id")
	private Personaje personaje;

	public ParkAttractions() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ParkAttractions(long id, String nombreParque, Personaje personaje) {
		super();
		this.id = id;
		this.nombreParque = nombreParque;
		this.personaje = personaje;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombreParque() {
		return nombreParque;
	}

	public void setNombreParque(String nombreParque) {
		this.nombreParque = nombreParque;
	}

	public Personaje getPersonaje() {
		return personaje;
	}

	public void setPersonaje(Personaje personaje) {
		this.personaje = personaje;
	}

	@Override
	public String toString() {
		return "ParkAttractions [id=" + id + ", nombreParque=" + nombreParque + ", personaje="
				+ personaje + "]";
	}
	
	

}
