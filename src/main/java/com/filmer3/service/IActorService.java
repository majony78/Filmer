package com.filmer3.service;

import com.filmer3.entities.Actor;

public interface IActorService {
	
	void saveActor(Actor actor);
	Actor obteberActorPorId(Long id);
	void eliminarActor(Long id);

}
