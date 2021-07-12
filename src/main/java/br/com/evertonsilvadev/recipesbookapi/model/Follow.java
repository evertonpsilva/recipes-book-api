package br.com.evertonsilvadev.recipesbookapi.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.evertonsilvadev.recipesbookapi.model.dto.FollowDTO;
import br.com.evertonsilvadev.recipesbookapi.model.dto.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Table(name = "followers")
@Entity
@Getter @Setter @NoArgsConstructor
@Accessors(chain = true)
public class Follow implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_FOLLOW")
	private Long id;
	
	@Column(name="ID_FOLLOWED")
	private Long idFollowed;

	@Column(name="ID_FOLLOWER")
	private Long idFollower;
	
	@Column(name = "DATE_FOLLOW")
	private Date dateFollow;
	
	public FollowDTO toDTO() {
		FollowDTO followDTO = new FollowDTO();
		followDTO.setId(id);
		followDTO.setIdFollowed(idFollowed);
		followDTO.setIdFollower(idFollower);
		followDTO.setDateFollow(dateFollow);
		return followDTO;
	}
	
	
}
