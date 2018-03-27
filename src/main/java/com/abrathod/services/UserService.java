package com.abrathod.services;

import java.util.List;

import com.abrathod.entity.User;

public interface UserService {

	public List<User> findByUserName(String userName);
}
