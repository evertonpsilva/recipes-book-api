package br.com.evertonsilvadev.recipesbookapi.repository;

import java.util.List;

import br.com.evertonsilvadev.recipesbookapi.model.User;
import br.com.evertonsilvadev.recipesbookapi.model.dto.UserDTO;

public interface UserRepositoryCustom {

	UserDTO getUser(Long id);
	List<UserDTO> getFollowers(User user);
	List<UserDTO> getFolloweds(User user);
	
}
