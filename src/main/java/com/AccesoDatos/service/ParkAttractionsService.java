package com.AccesoDatos.service;

import java.util.List;

import com.AccesoDatos.entity.ParkAttractions;
import com.AccesoDatos.entity.Personaje;

public interface ParkAttractionsService {
	
	public abstract List<ParkAttractions> buscarPorPersonajeRelacionado(Personaje personaje);
    public abstract ParkAttractions guardarParkAttraction(ParkAttractions parkAttraction);

}
