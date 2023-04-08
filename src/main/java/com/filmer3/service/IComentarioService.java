package com.filmer3.service;

import com.filmer3.entities.Comentario;

public interface IComentarioService {
	
	void saveComentario(Comentario comentario);
	Comentario buscarPorId(Long id);
	void eliminarComentario(long id);

}
