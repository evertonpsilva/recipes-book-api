package br.com.evertonsilvadev.recipesbookapi.authentication;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.evertonsilvadev.recipesbookapi.model.User;
import br.com.evertonsilvadev.recipesbookapi.repository.UserRepository;

@Service
public class AuthenticationService implements UserDetailsService {
	
	@Autowired
	private UserRepository repository;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByEmail(username);
		if (user != null) {
			return (UserDetails) user;
		}
		
		throw new UsernameNotFoundException("Dados inválidos!");
	}

}
