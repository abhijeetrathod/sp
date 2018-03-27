package com.abrathod.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abrathod.dao.UserDao;
import com.abrathod.entity.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<User> findByUserName(String userName) {
		
		return userDao.findByUserName(userName);
	}

}
