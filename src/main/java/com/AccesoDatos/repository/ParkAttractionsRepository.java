package com.AccesoDatos.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AccesoDatos.entity.ParkAttractions;
import com.AccesoDatos.entity.Personaje;

import java.util.List;


@Repository("parkAttractionsRepository")
public interface ParkAttractionsRepository extends JpaRepository<ParkAttractions, Serializable>{
	
	public abstract List<ParkAttractions> findByPersonaje(Personaje personaje);

}
