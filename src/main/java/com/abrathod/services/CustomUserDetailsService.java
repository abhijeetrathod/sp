package com.abrathod.services;

import java.util.ArrayList;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abrathod.dao.UserDao;
import com.abrathod.dao.UserRolesDao;
import com.abrathod.entity.User;



@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserDao userDao ;
	
	
	@Autowired
	UserRolesDao userRolesDao;

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		List<User> users;
		String password = null;
		List<String> roleNames = new ArrayList<>();
		roleNames.add("Guest");

		try {
			System.out.print("username " + userName);
			users = userDao.findByUserName(userName.toUpperCase());
			if (users.isEmpty()) {
				throw new UsernameNotFoundException("Username not found");
			}
			password = users.get(0).getPassword();
			List<String> roleNameList = userRolesDao.findAllActiveRoleName(users.get(0).getUserId());
			if (!roleNameList.isEmpty()) {
				roleNames.remove("Guest");
				roleNames.addAll(roleNameList);
			}
			//System.out.println("role NAme " + roleNames);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//System.out.println("Error in user upload " + e);
			e.printStackTrace();
		}
		return new org.springframework.security.core.userdetails.User(userName, password, true, true, true, true,
				getGrantedAuthorities(roleNames.toArray(new String[roleNames.size()])));
	}

	private List<GrantedAuthority> getGrantedAuthorities(String[] roleNames) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String roleName : roleNames) {
			authorities.add(new SimpleGrantedAuthority(roleName));
		}

		return authorities;
	}

}