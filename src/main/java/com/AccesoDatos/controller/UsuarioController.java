package com.AccesoDatos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.AccesoDatos.component.Validations;
import com.AccesoDatos.entity.Usuario;
import com.AccesoDatos.service.PersonajeService;
import com.AccesoDatos.service.UsuarioService;

import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping(value="/user")
public class UsuarioController {
	
	private static final String LOGIN="login";
	private static final String REGISTER="registro";
	private static final String REGISTERED="vistabusqueda";
	
	@Autowired
	@Qualifier("usuarioServiceImpl")
	private UsuarioService usuarioService;
	
	@Autowired
	@Qualifier("personajeServiceImpl")
	private PersonajeService personajeService;
	
	@Autowired
	@Qualifier("validations")
	private Validations validations;
	
	@GetMapping("/login")
	public String login(Model model) {
		Usuario usu = new Usuario();
		model.addAttribute("usu", usu);
		boolean correct = false;
		model.addAttribute("correcto", correct);
		return "login";
	}
	
	@RequestMapping(value = "/logueado", method = RequestMethod.GET)
	public String logueado(Usuario user, Model model, HttpSession session) {
	    List<Usuario> users = usuarioService.mostrarUsuarios();
	    for (Usuario u : users) {
	        if (u.getEmail().equals(user.getEmail()) && u.getPassword().equals(user.getPassword())) {
	            // Guardar el usuario en la sesión HTTP
	            session.setAttribute("usuarioLogueado", u);
	            System.out.println("Usuario desde el metodo logueado: " + u);
	            model.addAttribute("usu", u);
	            model.addAttribute("personaje", personajeService);
	            return "vistabusqueda";
	        }
	    }
	    model.addAttribute("usu", user);
	    model.addAttribute("correcto", false);
	    return "redirect:registro";
	}

	
	@GetMapping("/registro")
	public String register(Model model) {
		Usuario user = new Usuario();
		model.addAttribute("user", user);
		return REGISTER;
	}
	
	@GetMapping("/registrado")
	public String registered(@ModelAttribute("user")Usuario user, Model model, BindingResult bindingresult, HttpSession session) {
		boolean valPassword = validations.validadorPassword(user.getPassword());
		boolean valTelephone = validations.validadorTelefono(user.getTelefono());
		if(!valPassword) {
			bindingresult.rejectValue("password", "error.password","La contraseña debe contener mayusc, minus, numeros y caracteres especiales.");
			}
		if(!valTelephone) {
			bindingresult.rejectValue("telefono", "error.telephone", "El teléfono debe tener 9 digitos.");
			}
		if(bindingresult.hasErrors()) {
			return REGISTER;
			}
		usuarioService.guardarUsuario(user);
		return "redirect:login";
	}
	

}
