package com.abrathod.dao;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.abrathod.entity.Roles;



@Component
public class RolesDaoImpl implements RolesDao{

	@PersistenceContext(name = "em")
	private EntityManager em;

	@Override
	public List<Roles> findByRoleId(Long id) throws Exception{
		// TODO Auto-generated method stub
		return em.createNamedQuery("Roles.findByRoleId").setParameter("roleId", id).getResultList();
	}

	
	@Override
	public List<Roles> findAllActive() throws Exception{
		// TODO Auto-generated method stub
		return em.createNamedQuery("Roles.findAll").getResultList();
	}

	

	
	
	
	
}
