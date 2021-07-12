package br.com.evertonsilvadev.recipesbookapi.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import br.com.evertonsilvadev.recipesbookapi.authentication.TokenAuthenticationService;
import br.com.evertonsilvadev.recipesbookapi.model.dto.LoginDTO;

@Service
public class AuthenticationBusiness {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenAuthenticationService tokenService;

	public String authenticaticateUser(LoginDTO credential) throws Exception {
		
		UsernamePasswordAuthenticationToken dadosLogin = credential.toLoginToken();
		
		try {

			Authentication authentication = authManager.authenticate(dadosLogin);
			String token = tokenService.generateToken(authentication);

			return token;
			
		} catch (AuthenticationException e) {
			System.out.println(e.getLocalizedMessage());
			throw new Exception("Teste");
		}
		
	}
	
}
