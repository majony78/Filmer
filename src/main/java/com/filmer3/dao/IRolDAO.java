package com.filmer3.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.filmer3.entities.Rol;
import com.filmer3.enums.RolNombre;


public interface IRolDAO extends JpaRepository<Rol, Integer> {
	Optional<Rol> findByRolNombre(RolNombre rolNombre);
	boolean existsByRolNombre(RolNombre rolNombre);

}
