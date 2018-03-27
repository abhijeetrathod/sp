package com.abrathod.services;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abrathod.dao.UserRolesDao;
import com.abrathod.entity.UserRoles;



@Service
public class UserRolesServiceImpl implements UserRolesService {
	@Autowired
	UserRolesDao userRolesDao;

	

	@Override
	public List<UserRoles> findAllActive() throws Exception{
		// TODO Auto-generated method stub
		return userRolesDao.findAllActive();
	}

	@Override
	public List<String> findAllActiveRoleName(Long userID) throws Exception{
		// TODO Auto-generated method stub
		return userRolesDao.findAllActiveRoleName(userID);
	}

}
