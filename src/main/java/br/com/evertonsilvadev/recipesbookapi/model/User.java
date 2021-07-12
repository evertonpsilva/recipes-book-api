package br.com.evertonsilvadev.recipesbookapi.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.evertonsilvadev.recipesbookapi.model.dto.FollowDTO;
import br.com.evertonsilvadev.recipesbookapi.model.dto.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Table(name = "users")
@Entity
@Getter @Setter @NoArgsConstructor
@Accessors(chain = true)
public class User implements UserDetails, Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "USERNAME", nullable = false)
	private String username;

	@Column(name = "EMAIL", nullable = false)
	private String email;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@Column(name = "EMAIL_VERIFIED")
	private Boolean emailVerified = false;

	@Column(name = "PHOTO_URL")
	private String photoUrl;

	@Column(name = "BIO")
	private String bio;

	@Column(name = "LOCATION")
	private String location;

	@Column(name = "DATE_CREATED")
	private Date createdAt;
	
	public UserDTO toDTO() {
		return new UserDTO()
				.setUsername(this.username)
				.setEmail(this.email)
				.setName(this.name)
				.setEmailVerified(this.emailVerified)
				.setPhotoUrl(this.photoUrl)
				.setBio(this.bio)
				.setLocation(this.location)
				.setCreatedAt(this.createdAt);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
