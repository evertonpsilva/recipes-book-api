package br.com.evertonsilvadev.recipesbookapi.model.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter
@Accessors(chain = true)
public class UserDTO {
	private String username;
	
	private String name;
	
	private String email;
	
	private String photoUrl;
	
	private String bio;
	
	private String location;
	
	private Date createdAt;

	private Boolean emailVerified;
	
	private Long followers;
	
	private Long following;
	
	public UserDTO(String username, 
			String name, 
			String email, 
			String photoUrl) {
		this.username = username;
		this.name = name;
		this.email = email;
		this.photoUrl = photoUrl;
	}
	public UserDTO() {
		
	}
	
}
