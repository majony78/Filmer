package com.filmer3.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filmer3.dao.IPeliculaDAO;
import com.filmer3.entities.Pelicula;
import com.filmer3.service.IPeliculaService;

@Service
public class PeliculaServiceImpl implements IPeliculaService{
	
	@Autowired 
	private IPeliculaDAO peliculaDao;
	
	@Override
	public void save(Pelicula pelicula) {
           peliculaDao.save(pelicula);   
    
		
	}

	@Override
	public List<Pelicula> listadoPeliculas() {
	
		return peliculaDao.findAll();
	}

	@Override
	public Pelicula peliculaPorId(Long id) {
	
		return peliculaDao.findById(id).orElse(null);
	}

	@Override
	public void eliminarById(Long id) {
		
		peliculaDao.deleteById(id);
		
	}

	@Override
	public Pelicula BuscarPorPelicula(String titulo) {
		
		return peliculaDao.findByTitulo(titulo);
	}
	
	

}
 