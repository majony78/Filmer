package com.filmer3.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filmer3.dao.IActorDAO;
import com.filmer3.entities.Actor;
import com.filmer3.service.IActorService;

@Service
public class ActorServiceImpl implements IActorService{
	
	@Autowired
	private IActorDAO actorDao;

	@Override
	public void saveActor(Actor actor) {
		
		actorDao.save(actor);
		
	}

	@Override
	public Actor obteberActorPorId(Long id) {
		
		
		return actorDao.findById(id).orElse(null);
	}

	@Override
	public void eliminarActor(Long id) {
		actorDao.deleteById(id);
	}

}
