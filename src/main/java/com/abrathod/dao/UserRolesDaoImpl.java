package com.abrathod.dao;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.abrathod.entity.UserRoles;




@Component
public class UserRolesDaoImpl implements UserRolesDao{

	@PersistenceContext(name = "em")
	private EntityManager em;

	

	@Override
	public List<UserRoles> findAllActive() throws Exception{
		// TODO Auto-generated method stub
		return em.createNamedQuery("USER_ROLES.findAllActive.findAllActive").getResultList();
	}



	@Override
	public List<String> findAllActiveRoleName(Long userID) throws Exception{
		// TODO Auto-generated method stub
		return em.createNamedQuery("userRoles.findAllActiveRoleName").setParameter("userId", userID).getResultList();
	}
	
}
