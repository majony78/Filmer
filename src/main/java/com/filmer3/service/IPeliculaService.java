package com.filmer3.service;

import java.util.List;

import com.filmer3.entities.Pelicula;

public interface IPeliculaService  {
	
	void save(Pelicula pelicula);
	List<Pelicula> listadoPeliculas();
	Pelicula peliculaPorId(Long id);
	void eliminarById(Long id);
	Pelicula BuscarPorPelicula(String titulo);

}
