package com.abrathod.dao;

import java.util.List;

import com.abrathod.entity.User;

public interface UserDao {
	
	public List<User> findByUserName(String userName);

}
