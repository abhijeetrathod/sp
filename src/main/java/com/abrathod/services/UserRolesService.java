package com.abrathod.services;

import java.util.List;

import com.abrathod.entity.UserRoles;




public interface UserRolesService {

	

	public List<UserRoles> findAllActive() throws Exception;

	public List<String> findAllActiveRoleName(Long userID) throws Exception;
	
}
