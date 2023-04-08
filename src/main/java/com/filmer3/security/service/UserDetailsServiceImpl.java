package com.filmer3.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.filmer3.entities.Usuario;
import com.filmer3.service.Impl.UsuarioService;
@Service
public class UserDetailsServiceImpl  implements UserDetailsService{
	
	
	@Autowired
	private UsuarioService usuarioService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioService.getByUsername(username)
				.orElseThrow( () -> new UsernameNotFoundException(username));
		
		return UsuarioPrincipal.build(usuario);
		
	
	}

}
