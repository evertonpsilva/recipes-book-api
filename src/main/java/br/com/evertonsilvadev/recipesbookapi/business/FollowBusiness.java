package br.com.evertonsilvadev.recipesbookapi.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.evertonsilvadev.recipesbookapi.model.Follow;
import br.com.evertonsilvadev.recipesbookapi.repository.FollowRepository;

@Service
public class FollowBusiness {

	@Autowired
	FollowRepository repository;
	
	public void follow(Follow model) {
		this.repository.save(model);
	}
	
	public void unfollow(Long idFollower, Long idFollowed){
		Follow follow = this.getFollow(idFollower, idFollowed);
		try {
			this.repository.delete(follow);
		}catch(Exception e) {
			throw e;
		}
	}
	
	private Follow getFollow(Long idFollower, Long idFollowed) {
		return this.repository.findByIdFollowerAndIdFollowed(idFollower, idFollowed);
	}
	
}
