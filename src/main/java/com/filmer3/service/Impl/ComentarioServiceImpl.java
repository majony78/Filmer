package com.filmer3.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filmer3.dao.IComentarioDAO;
import com.filmer3.entities.Comentario;
import com.filmer3.service.IComentarioService;

@Service
public class ComentarioServiceImpl implements IComentarioService{
	
	@Autowired
    private IComentarioDAO comentarioDao;
	
	@Override
	public void saveComentario(Comentario comentario) {
		comentarioDao.save(comentario);
		
		
	}

	@Override
	public Comentario buscarPorId(Long id) {
		
		return comentarioDao.findById(id).orElse(null);
	}

	@Override
	public void eliminarComentario(long id) {
		comentarioDao.deleteById(id);
		
	}

}
