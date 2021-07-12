package br.com.evertonsilvadev.recipesbookapi.model.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginDTO {

 	private String email;
 	private String password;
	
 	public UsernamePasswordAuthenticationToken toLoginToken() {
 		System.out.println(email);
 		System.out.println(password);
		return new UsernamePasswordAuthenticationToken(email, password);
	}
}
