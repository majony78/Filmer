package com.filmer3.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.filmer3.entities.Actor;
import com.filmer3.entities.Pelicula;
import com.filmer3.service.IActorService;
import com.filmer3.service.IPeliculaService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private IPeliculaService peliculaService;
	
	@Autowired
	private IActorService actorService;
	
	@GetMapping("/peli-form")
	 public String peliForm(Model model) {
		model.addAttribute("pelicula", new Pelicula());
		 return "admin/peliForm";
	 }
	
	@PostMapping("/save-pelicula")
	public String savePelicula(@RequestParam(name = "file", required = false )MultipartFile portada, Pelicula pelicula,
			RedirectAttributes redirect) {
		
		if(!portada.isEmpty()) {
			String ruta = "C://Temp//uploads";
			String nombreUnico = UUID.randomUUID()+" " + portada.getOriginalFilename();
			
			try {
				byte[] bytes = portada.getBytes();
 				Path rutaAbsoluta = Paths.get(ruta + "//" + nombreUnico );
 				Files.write(rutaAbsoluta, bytes);
 				pelicula.setPortada(nombreUnico);
 				
 				peliculaService.save(pelicula);
 				redirect.addFlashAttribute("peliculaGuardada","Película guardada");
 				redirect.addFlashAttribute("peliParaActor", pelicula);
				
			}catch (Exception e) { 
				e.getCause().getMessage();
			}
		}
		
		return "redirect:/actors/actors-form";
	}
	
	@GetMapping("/gestion-peliculas")
	public String listadoPeliculas(Model model) {
		
		model.addAttribute("peliculas", peliculaService.listadoPeliculas());
		return "admin/gestionPeliculas";
	}
	
	@GetMapping("/eliminar-pelicula/{id}")
	public String eliminarPelicula(@PathVariable Long id, RedirectAttributes redirect) {
		
		peliculaService.eliminarById(id);
		redirect.addFlashAttribute("eliminarPelicula", "Película eliminada con éxito");
		
		return "redirect:/admin/gestion-peliculas";
	}
	
    @GetMapping("/editar-form/{id}")
	public String editarFormulario(@PathVariable Long id, RedirectAttributes redirect,Model model) {
    	
    	Pelicula pelicula = null;
    	
    	if(id > 0 ) {
    		pelicula = peliculaService.peliculaPorId(id);
    		model.addAttribute("pelicula", pelicula);
    	}
    	
    	
    	
		return "admin/editarPelicula";
	}
    
    @PostMapping("/editar-pelicula")
    public String editarPelicula(@RequestParam(name="file")MultipartFile imagenPortada, Pelicula peli, RedirectAttributes redirect,
    		@ModelAttribute("pelicula")Pelicula pelicula, Model model ) {
    	
    	if(!imagenPortada.isEmpty()) {
			String ruta = "C://Temp//uploads";
			String nombreUnico = UUID.randomUUID()+" " + imagenPortada.getOriginalFilename();
			
			try {
				byte[] bytes = imagenPortada.getBytes();
 				Path rutaAbsoluta = Paths.get(ruta + "//" + nombreUnico );
 				Files.write(rutaAbsoluta, bytes);
 				pelicula.setPortada(nombreUnico);
 				
 				peliculaService.save(pelicula);
 				redirect.addFlashAttribute("peliEditada","Película Editada");
 				
				
			}catch (Exception e) { 
				e.getCause().getMessage();
			}
		}
    	
    	
    	
    	return "redirect:/admin/gestion-peliculas";
    }
    
    @GetMapping("/editar-actores/{id}")
    public String editarActores(@PathVariable Long id, Model model) {
    	
    	Pelicula peliculaById = peliculaService.peliculaPorId(id);
    	model.addAttribute("peliEncontrada", peliculaById);
    	
    	
    	return "admin/edicionActores";
    }
    
    @GetMapping("cargar-actor/{id}")
    public String cargarActor(@PathVariable Long id, Model model) {
    	
    	Actor actor = actorService.obteberActorPorId(id);
    	model.addAttribute("actor", actor);
    	return "admin/editarActorForm";
    	
    }
    
    @PostMapping("/editar-actor")
    public String editarActor(@ModelAttribute("actor") Actor actor, RedirectAttributes redirect) {
    	
    	actorService.saveActor(actor);
    	redirect.addFlashAttribute("actorEditado", "Actor Modificado");
    	
    	return "redirect:/admin/gestion-peliculas";
    }
    
    
    @GetMapping("/eliminar-actor/{id}")
    public String eliinarActor(@PathVariable Long id, RedirectAttributes redirect ) {
    	actorService.eliminarActor(id);
    	redirect.addFlashAttribute("actorEliminado", "Actor eliminado");
    	
    	return "redirect:/admin/gestion-peliculas";
    }
    
    
	@PostMapping("/save-actor")
	public String saveActors(Actor actor, RedirectAttributes redirect, Model model ) {
		    
		actorService.saveActor(actor);
		redirect.addFlashAttribute("actorGuardado", "Actor guardado");
		
		return "redirect:/admin/gestion-peliculas";
	}
		
	
    
    

}


