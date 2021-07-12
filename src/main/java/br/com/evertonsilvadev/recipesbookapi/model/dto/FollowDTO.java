package br.com.evertonsilvadev.recipesbookapi.model.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain = true)
public class FollowDTO {

	private Long id;
	
	private Long idFollowed;

	private Long idFollower;
	
	private Date dateFollow;
	
	
}
