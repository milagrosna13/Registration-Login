package com.codingdojo.milagros.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.milagros.models.User;
import com.codingdojo.milagros.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {
	
	@Autowired
	private UserService us;
	
	@GetMapping("/")
	public String index(@ModelAttribute("newUser") User user) {
		return "index.jsp";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User user, BindingResult result, HttpSession session) {
		us.register(user, result);
		if(result.hasErrors()) {
		return "index.jsp";
		}else {
			//Guardo al usuario en session
			session.setAttribute("userInSession", user);
			
			return "redirect:/dashboard";
		}
	}
	
	
	@GetMapping("/dashboard")
	public String dashboard(HttpSession session) {
		
		//FALTA VALIDACION DE QUE EL USUARIO ESTA EN SESION
		User userTemp = (User) session.getAttribute("userInSession");
		if(userTemp==null) {
			return "redirect:/";
		}
		
		return "dashboard.jsp";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam("email") String email,@RequestParam("password") String password, 
						RedirectAttributes redirectAttributes,HttpSession session)/*usar MENSAJES DE FLASH*/ {
		User userTryingLogin= us.login(email, password);
		
		
		if(userTryingLogin==null) {
			redirectAttributes.addFlashAttribute("errorLogin","email o password incorrecto");
			return "redirect:/";
		}else {
			session.setAttribute("userInSession", userTryingLogin);
			return "redirect:/dashboard";
		}
	}
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("userInSession");
		return "redirect:/";
	
	}
}
