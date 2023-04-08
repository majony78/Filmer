package com.filmer3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.filmer3.entities.Actor;
import com.filmer3.entities.Pelicula;
import com.filmer3.service.IActorService;
import com.filmer3.service.IPeliculaService;

@Controller
@RequestMapping("/actors")
@SessionAttributes("peliParaActor")
public class ActorController {
	
	@Autowired
    private IActorService actorService; 
	
	@Autowired
    private IPeliculaService peliculaService; 
	
	@GetMapping("/actors-form")
	public String actorsForm(Actor actor, Model model,
			@ModelAttribute("peliParaActor")Pelicula pelicula) {
		model.addAttribute("actor", new Actor());
		model.addAttribute("film", pelicula);
		return "admin/actorsForm";
	}
	
	@PostMapping("/save-actor")
	public String SaveActors(Actor actor, RedirectAttributes redirect, Model model,
			@ModelAttribute("peliParaActor")Pelicula pelicula) {
		    
		actorService.saveActor(actor);
		redirect.addFlashAttribute("actorGuardado", "Actor guardado");
		
		return "redirect:/actors/actors-form";
	}
	
	@GetMapping("/add-actores/{id}")
	public String addActores(@PathVariable Long id, Model model) {
		  
		
		Pelicula pelicula = peliculaService.peliculaPorId(id);
		
		model.addAttribute("actor", new Actor());   
		model.addAttribute("film", pelicula);
		
		return "admin/addActoresForm";
	}
	


}
