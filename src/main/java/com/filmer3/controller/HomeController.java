package com.filmer3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.filmer3.entities.Pelicula;
import com.filmer3.service.IPeliculaService;

@Controller
public class HomeController {
	
	@Autowired
	private IPeliculaService peliculaService;
	
	@GetMapping("/")
	public String home(Model model, Authentication auth) {
		
		if(auth != null) {
		  String username = auth.getName();
		  model.addAttribute("username", username);
		}
		
		model.addAttribute("peliculas", peliculaService.listadoPeliculas());
		model.addAttribute("pelicula", new Pelicula());
		return "home";
	}
	
	@GetMapping("/buscar")
	public String buscarPelicula(@RequestParam  String titulo, @ModelAttribute("pelicula") Pelicula pelicula
			,Model model) {
		Pelicula peli = peliculaService.BuscarPorPelicula(titulo);
		
		if(titulo != null) {
		
		model.addAttribute("peli", peli);
		}
		
		if(peli == null) {
			
			model.addAttribute("peliNoEncontrada", "Sin resultados...");
			}
		
		
		
		
		return "peliculaBuscador";
	}
	
	
	
	
	

}
