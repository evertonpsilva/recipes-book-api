package br.com.evertonsilvadev.recipesbookapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.evertonsilvadev.recipesbookapi.business.AuthenticationBusiness;
import br.com.evertonsilvadev.recipesbookapi.model.dto.LoginDTO;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	AuthenticationBusiness business;
	
	@PostMapping
	public ResponseEntity<Void> authenticate(@RequestBody @Validated LoginDTO credential) throws Exception{
		
		String token = business.authenticaticateUser(credential);
		
		HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.set("Authorization", token);
		return ResponseEntity.ok().headers(responseHeaders).build();
		
	}
	
}
