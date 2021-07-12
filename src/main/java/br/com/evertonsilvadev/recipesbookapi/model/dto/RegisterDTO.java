package br.com.evertonsilvadev.recipesbookapi.model.dto;

import br.com.evertonsilvadev.recipesbookapi.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class RegisterDTO {
	
	private String username;

	private String email;

	private String name;

	private String password;

	private String photoUrl;

	private String bio;

	private String location;
	
	public User toModel() {
		return new User()
				.setUsername(this.username)
				.setEmail(this.email)
				.setName(this.name)
				.setPassword(this.password)
				.setPhotoUrl(this.photoUrl)
				.setBio(this.bio)
				.setLocation(this.location);
	}
}
