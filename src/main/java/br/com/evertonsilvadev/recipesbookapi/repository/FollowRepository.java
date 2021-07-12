package br.com.evertonsilvadev.recipesbookapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.evertonsilvadev.recipesbookapi.model.Follow;

public interface FollowRepository extends JpaRepository<Follow, Long>  {

	public Follow findByIdFollowerAndIdFollowed(Long idFollower, Long idFollowed);
	
}
