package com.filmer3.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.filmer3.entities.Pelicula;

@Repository
public interface IPeliculaDAO extends JpaRepository<Pelicula, Long> {
	
	Pelicula findByTitulo(String titulo);
	

}
