package br.com.evertonsilvadev.recipesbookapi.repository.impl;

import org.springframework.transaction.annotation.Transactional;

import br.com.evertonsilvadev.recipesbookapi.model.Follow;
import br.com.evertonsilvadev.recipesbookapi.model.User;
import br.com.evertonsilvadev.recipesbookapi.model.dto.UserDTO;
import br.com.evertonsilvadev.recipesbookapi.repository.UserRepositoryCustom;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Transactional
public class UserRepositoryCustomImpl implements UserRepositoryCustom{

	@PersistenceContext
    EntityManager entityManager;
	
	@Autowired
    SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public UserDTO getUser(Long id) {
		
		CriteriaBuilder qb = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<User> criteriaQuery = qb.createQuery(User.class);
		Root<User> user = criteriaQuery.from(User.class);
		ParameterExpression<Long> parameter = qb.parameter(Long.class);
		criteriaQuery.select(user).where(qb.equal(user.get("id"), parameter));
		
		TypedQuery<User> query = entityManager.createQuery(criteriaQuery);
		query.setParameter(parameter, id);
		
		UserDTO userDTO = query.getSingleResult().toDTO();

		setFollowers(id, qb, userDTO);
		setFolloweds(id, qb, userDTO);
                
        return userDTO;
	}
	
	private void setFollowers(Long id, CriteriaBuilder qb, UserDTO userDTO) {
		
		CriteriaQuery<Long> cq = qb.createQuery(Long.class);
		Root<Follow> root = cq.from(Follow.class);
		cq.select(qb.count(root));
		cq.where(qb.equal(root.get("idFollowed"), id));
		cq.distinct(true);
		userDTO.setFollowers(entityManager.createQuery(cq).getSingleResult());
		
	}
	
	private void setFolloweds(Long id, CriteriaBuilder qb, UserDTO userDTO) {
		
		CriteriaQuery<Long> cq = qb.createQuery(Long.class);
		Root<Follow> root = cq.from(Follow.class);
		cq.select(qb.count(root));
		cq.where(qb.equal(root.get("idFollower"), id));
		cq.distinct(true);
		userDTO.setFollowing(entityManager.createQuery(cq).getSingleResult());
		
	}
	
	public List<UserDTO> getFollowers(User user){
		
		CriteriaBuilder qb = entityManager.getCriteriaBuilder();	
		Metamodel m = entityManager.getMetamodel();
		
		TypedQuery<User> criteriaQuery = entityManager
				.createQuery("select u from User u where u.id in (select f.idFollower from Follow f where f.idFollowed = :id)", User.class)
				.setParameter("id", user.getId());
		
		
		List<UserDTO> list = criteriaQuery.getResultList().stream().map(userMap -> userMap.toDTO()).collect(Collectors.toList());
		
		return list;
		
	}
	
	public List<UserDTO> getFolloweds(User user){
		
		CriteriaBuilder qb = entityManager.getCriteriaBuilder();	
		Metamodel m = entityManager.getMetamodel();
		
		TypedQuery<User> criteriaQuery = entityManager
				.createQuery("select u from User u where u.id in (select f.idFollowed from Follow f where f.idFollower = :id)", User.class)
				.setParameter("id", user.getId());
		
		List<UserDTO> list = criteriaQuery.getResultList().stream().map(userMap -> userMap.toDTO()).collect(Collectors.toList());
		
		return list;
		
	}
	
}
