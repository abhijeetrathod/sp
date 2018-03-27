package com.abrathod.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abrathod.dao.RolesDao;
import com.abrathod.entity.Roles;


@Service
public class RolesServiceImpl implements RolesService {
	@Autowired
	RolesDao ltP2pRolesDao;

	@Override
	public List<Roles> findInRoleId(Long roleID) throws Exception {
		// TODO Auto-generated method stub
		return ltP2pRolesDao.findByRoleId(roleID);
	}

	@Override
	public List<Roles> findAllActive() throws Exception {
		// TODO Auto-generated method stub
		return ltP2pRolesDao.findAllActive();
	}

	

	

	
}
