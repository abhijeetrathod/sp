package com.abrathod.dao;

import java.util.List;

import com.abrathod.entity.Roles;






public interface RolesDao {
	public List<Roles> findByRoleId(Long id) throws Exception;
	
	public List<Roles>	findAllActive() throws Exception;
	
	
}
