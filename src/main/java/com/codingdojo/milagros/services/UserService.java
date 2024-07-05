package com.codingdojo.milagros.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.milagros.models.User;
import com.codingdojo.milagros.repositories.UserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class UserService {
	@Autowired
	private UserRepository ur;
	
	/*Metodo que registra un nuevo usuario*/
	public User register(User newUser , BindingResult result) {
		
		//COMPARAR CONTRASEÑAS
		String password =newUser.getPassword();
		String confirm = newUser.getConfirmation();
		//REVISAR QUE EL EMAIL  NO ESTE REGISTRADO
		if(!password.equals(confirm)) {
			
			//path,clave,mensaje
			result.rejectValue("confirmation", "Matches","Password and confirmation dont match");
		
		}
		String email = newUser.getEmail();
		User userExist = ur.findByEmail(email);
		if(userExist !=null) {
			//El correo ya esta registrado
			result.rejectValue("email", "Unique", "Email already exists");
		}
		
			//Si existe error , regresa null
		if(result.hasErrors()) {
			return null;
		}else {
			//no hay error
			//hashear contraseña
			String passHash = BCrypt.hashpw(password, BCrypt.gensalt());
			
			newUser.setPassword(passHash); //establecemos el password HASHEADO
			return ur.save(newUser);
		
		}
	}
	
	/*Método que revisa que los datos sean correctos para Iniciar Sesión*/
	public User login(String email,String password) {
		//revisamos que el correo exita
		User userTryingLogin = ur.findByEmail(email);
		
		if(userTryingLogin==null) {
			return null;
		}
		//BCrypt.checkpw(contraseñaNOencriptada, constraseñaSIencriptada)
		if(BCrypt.checkpw(password, userTryingLogin.getPassword())) {
			return userTryingLogin;
			
		}else {
			return null;
		}
	
	
	}

	
	
}
