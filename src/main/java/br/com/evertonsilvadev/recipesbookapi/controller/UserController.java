package br.com.evertonsilvadev.recipesbookapi.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.evertonsilvadev.recipesbookapi.business.UserBusiness;
import br.com.evertonsilvadev.recipesbookapi.model.Follow;
import br.com.evertonsilvadev.recipesbookapi.model.User;
import br.com.evertonsilvadev.recipesbookapi.model.dto.RegisterDTO;
import br.com.evertonsilvadev.recipesbookapi.model.dto.UpdateUserDTO;
import br.com.evertonsilvadev.recipesbookapi.model.dto.UserDTO;
import javassist.tools.web.BadHttpRequest;


@RestController
@RequestMapping("/users")
public class UserController {
	 
	@Autowired
	UserBusiness business;
	
	@GetMapping
	public ResponseEntity<UserDTO> getProfile(@AuthenticationPrincipal User user){
		return ResponseEntity.ok(this.business.getProfile(user));
	}
	
	@GetMapping("followers")
	public ResponseEntity<List<UserDTO>> getFollowers(@AuthenticationPrincipal User user){
		return ResponseEntity.ok(this.business.getFollowers(user));
	}
	
	@GetMapping("followeds")
	public ResponseEntity<List<UserDTO>> getFolloweds(@AuthenticationPrincipal User user){
		return ResponseEntity.ok(this.business.getFolloweds(user));
	}

	@GetMapping("email-in-use/{email}")
	public ResponseEntity<Boolean> emailInUse(@PathVariable("email") String email){
		return ResponseEntity.ok(this.business.emailInUse(email));
	}
	
	@GetMapping("username-in-use/{username}")
	public ResponseEntity<Boolean> usernameInUse(@PathVariable("username") String username){
		return ResponseEntity.ok(this.business.usernameInUse(username));
	}
	
	@PostMapping("follow/{usernameFollowed}")
	public ResponseEntity<Void> follow(@PathVariable("usernameFollowed") String usernameFollowed, @AuthenticationPrincipal User user){
		this.business.follow(usernameFollowed, user);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("unfollow/{usernameFollowed}")
	public ResponseEntity<Void> unfollow(@PathVariable("usernameFollowed") String usernameFollowed, @AuthenticationPrincipal User user){
		this.business.unfollow(usernameFollowed, user);
		return ResponseEntity.ok().build();
	}
		 
	@PostMapping
	public ResponseEntity<UserDTO> createUser(@RequestBody @Validated RegisterDTO data) throws BadHttpRequest{
		return ResponseEntity.ok(this.business.create(data));
	}
	
	@PutMapping
	public ResponseEntity<UserDTO> updateUser(@RequestBody @Validated UpdateUserDTO data, @AuthenticationPrincipal User user){
		System.out.println(user.getUsername());
		return ResponseEntity.ok(this.business.update(data, user));
	}
	
}
