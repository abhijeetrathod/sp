package com.abrathod.dao;

import java.util.List;

import com.abrathod.entity.UserRoles;





public interface UserRolesDao {
	
	public List<String> findAllActiveRoleName(Long userID) throws Exception;
	public List<UserRoles>	findAllActive() throws Exception;
	}
