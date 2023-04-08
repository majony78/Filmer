package com.filmer3.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.filmer3.entities.Comentario;
import com.filmer3.entities.Usuario;
import com.filmer3.service.IComentarioService;
import com.filmer3.service.Impl.UsuarioService;

@Controller
@RequestMapping("/comentarios")
public class ComentarioController {
	@Autowired
	IComentarioService comentarioService;

	@Autowired
	UsuarioService usuarioService;

	@GetMapping("/editar-comentario/{id}")
	public String editarComentarioForm(@PathVariable Long id, Model model) {

		model.addAttribute("comentario", comentarioService.buscarPorId(id));

		return "comentarios/editarComentarioForm";
	}

	@GetMapping("/editar")
	public String editar(@ModelAttribute("comentario") Comentario comentario, RedirectAttributes redirect,
			Authentication auth, HttpSession session) {

		String username = auth.getName();
		Optional<Usuario> usuario = usuarioService.getByUsername(username);
		comentario.setUsuario(usuario.get());

		comentarioService.saveComentario(comentario);

		redirect.addFlashAttribute("comentarioModificado", "Comentario modificado");

		return "redirect:/";
	}

	@GetMapping("/eliminar-comentario/{id}")
	public String eliminar(@PathVariable Long id, RedirectAttributes redirect) {

		comentarioService.eliminarComentario(id);

		redirect.addFlashAttribute("comentarioEliminado", "Comentario eliminado");

		return "redirect:/";

	}
}