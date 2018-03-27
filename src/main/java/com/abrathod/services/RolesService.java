package com.abrathod.services;

import java.util.List;

import com.abrathod.entity.Roles;


public interface RolesService {

	

	public List<Roles> findInRoleId(Long roleID) throws Exception;

	

	public List<Roles> findAllActive() throws Exception;

	
}
