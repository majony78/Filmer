package com.filmer3.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.filmer3.entities.Comentario;

@Repository
public interface IComentarioDAO extends JpaRepository<Comentario, Long> {
	
	

}
