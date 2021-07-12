package br.com.evertonsilvadev.recipesbookapi.repository;

import java.awt.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.evertonsilvadev.recipesbookapi.model.User;
import br.com.evertonsilvadev.recipesbookapi.model.dto.UserDTO;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom{

	User findByEmail(String email);
	User findByUsername(String username);
//	
////	@Query(nativeQuery = true,
////		value="SELECT DISTINCT\r\n" + 
////				"		user.USERNAME as \"username\", \r\n" + 
////				"		user.NAME as \"name\", \r\n" + 
////				"		user.EMAIL as \"email\", \r\n" + 
////				"		user.PHOTO_URL as \"photoUrl\", \r\n" +  
////				"		user.BIO as \"bio\", \r\n" + 
////				"		user.LOCATION as \"location\", \r\n" + 
////				"		user.DATE_CREATED as \"createdAt\", \r\n" + 
////				"		user.EMAIL_VERIFIED as \"emailVerified\", \r\n" + 
////				"        (SELECT DISTINCT COUNT(ID_FOLLOW) FROM recipesbook.followers follow where user.id = follow.id_followed) as \"followers\" ,\r\n" + 
////				"        (SELECT DISTINCT COUNT(ID_FOLLOW) FROM recipesbook.followers follow where user.id = follow.id_follower) as \"following\" \r\n" + 
////				" FROM recipesbook.users user\r\n" +
////				" where user.id = :id ")
//	@Query(nativeQuery = true,
//		value="SELECT new br.com.evertonsilvadev.recipesbookapi.model.dto.UserDTO(username, name " + 
//	", email, photo_url) FROM users user where user.id = :id"
//	)
//	UserDTO getUser(@Param("id") Long id);
}

