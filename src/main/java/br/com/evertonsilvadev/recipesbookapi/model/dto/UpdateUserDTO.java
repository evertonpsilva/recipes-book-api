package br.com.evertonsilvadev.recipesbookapi.model.dto;

import br.com.evertonsilvadev.recipesbookapi.model.User;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain = true)
public class UpdateUserDTO {
	
	private String name;
	
	private String username;
	
	private String photoUrl;
	
	private String bio;
	
	private String location;
	
	public User toResource() {
		return new User()
				.setName(name)
				.setUsername(username)
				.setPhotoUrl(photoUrl)
				.setBio(bio)
				.setLocation(location);
	}
	
}
