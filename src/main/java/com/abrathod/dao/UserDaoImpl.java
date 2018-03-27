package com.abrathod.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.abrathod.entity.User;

@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao{

	@PersistenceContext(name="em")
	EntityManager em;
	
	@Override
	@Transactional(readOnly = true)
	public List<User> findByUserName(String userName) {
		
		return (List<User>)em.createNamedQuery("User.findByUserName").setParameter("userName1", userName).getResultList();
	}

	
}
