package com.hsypower.epct.service.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsypower.epct.dao.IUserDAO;
import com.hsypower.epct.entity.User;
import com.hsypower.epct.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDAO userDAO;

	@Override
	public User getUserByName(String name) {
		List<User> users = userDAO.findByName(name);
		if (users.isEmpty()) {
			return null;
		} else {
			return users.get(0);
		}
	}

	@Override
	public User getUserById(long userId) {
		return userDAO.findOne(userId);
	}

	@Override
	public User createNewUser(User newUser, User user) {
		newUser.setCreatedBy(user.getName());
		newUser.setCreateOn(new Date());

		return userDAO.save(newUser);
	}

	@Override
	public User updateUser(User newUser, User user) {
		newUser.setModifiedBy(user.getName());
		newUser.setModifyOn(new Date());

		return userDAO.save(newUser);
	}

	@Override
	public Iterator<User> findAll() {
		return userDAO.findAll().iterator();
	}

}
