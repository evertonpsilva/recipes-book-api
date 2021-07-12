package br.com.evertonsilvadev.recipesbookapi.authentication;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.evertonsilvadev.recipesbookapi.model.User;
import br.com.evertonsilvadev.recipesbookapi.repository.UserRepository;

public class AuthenticationTokenFilter extends OncePerRequestFilter  {

	private TokenAuthenticationService tokenService;
	private UserRepository repository;

	public AuthenticationTokenFilter(TokenAuthenticationService tokenService, UserRepository repository) {
		this.tokenService = tokenService;
		this.repository = repository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = getToken(request);
		boolean validToken = tokenService.isValidToken(token);
		if (validToken) {
			authenticate(token);
		}
		filterChain.doFilter(request, response);
	}

	private void authenticate(String token) {
		Long id = tokenService.getIdUser(token);
		User user = repository.findById(id).get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String getToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		
		return token.substring(7, token.length());
	}
	
}
