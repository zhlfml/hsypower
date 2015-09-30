package com.hsypower.epct.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.hsypower.epct.entity.User;

public interface IUserDAO extends CrudRepository<User, Long> {

	List<User> findByName(String name);
}
