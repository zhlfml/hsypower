package com.hsypower.epct.service;

import java.util.Iterator;

import com.hsypower.epct.entity.User;

public interface IUserService {

	User getUserByName(String name);
	
	User getUserById(long userId);
	
	User createNewUser(User newUser, User user);
	
	User updateUser(User newUser, User user);
	
	Iterator<User> findAll();
}
