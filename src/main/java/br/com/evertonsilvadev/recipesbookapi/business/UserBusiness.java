package br.com.evertonsilvadev.recipesbookapi.business;

import java.util.List;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.evertonsilvadev.recipesbookapi.model.Follow;
import br.com.evertonsilvadev.recipesbookapi.model.User;
import br.com.evertonsilvadev.recipesbookapi.model.dto.RegisterDTO;
import br.com.evertonsilvadev.recipesbookapi.model.dto.UpdateUserDTO;
import br.com.evertonsilvadev.recipesbookapi.model.dto.UserDTO;
import br.com.evertonsilvadev.recipesbookapi.repository.UserRepository;
import javassist.tools.web.BadHttpRequest;

@Service
public class UserBusiness {

	@Autowired
	UserRepository repository;
	
	@Autowired
	FollowBusiness followBusiness;

	public Boolean emailInUse(String email) {
		return repository.findByEmail(email) != null ? true : false;
	}
	
	public Boolean usernameInUse(String username) {
		return repository.findByUsername(username) != null ? true : false;
	}
	
	public List<UserDTO> getFollowers(User user) {
		return repository.getFollowers(user);
	}
	
	public List<UserDTO> getFolloweds(User user) {
		return repository.getFolloweds(user);
	}
	
	public void follow(String usernameFollowed, User user) {		
		User followed = repository.findByUsername(usernameFollowed);
		Follow followModel = new Follow().setIdFollowed(followed.getId()).setIdFollower(user.getId());
		this.followBusiness.follow(followModel);
	}
	
	public void unfollow(String usernameFollowing, User user) {
		User following = repository.findByUsername(usernameFollowing);
		
		followBusiness.unfollow(user.getId(), following.getId());
	}
	
	public UserDTO getProfile(User user) {
		return repository.getUser(user.getId());
	}
	
	public UserDTO update(UpdateUserDTO data, User user) {
		BeanUtils.copyProperties(data, user);
		return repository.save(user).toDTO();
	}
	
	public UserDTO create(RegisterDTO data) throws BadHttpRequest {

		String encryptPassword = new BCryptPasswordEncoder().encode(data.getPassword());
		data.setPassword(encryptPassword);
		
		try {
			User createdUser = repository.save(data.toModel());
			return createdUser.toDTO();
		}catch(Exception e) {	
			System.out.println("osdaasasdjiasd");
			System.out.println(e.getMessage());
			throw new BadHttpRequest();
//			throw e;
		}
		
	}
	
}
