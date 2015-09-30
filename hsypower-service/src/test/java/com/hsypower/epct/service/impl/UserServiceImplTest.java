package com.hsypower.epct.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.hsypower.epct.AbstractDBUnitSpringContextTests;
import com.hsypower.epct.entity.User;
import com.hsypower.epct.service.IUserService;
import com.hsypower.epct.utils.DateTimeUtils;

@DatabaseSetup("UserServiceImplTest.xml")
public class UserServiceImplTest extends AbstractDBUnitSpringContextTests {

	private User user;

	@Autowired
	private IUserService userService;

	@Before
	public void setUp() {
		user = new User();
		user.setName("admin");
	}

	@Test
	public void testGetUserByName() {
		User user = userService.getUserByName("admin");

		assertNotNull(user);
		assertEquals("password", user.getPassword());
		assertTrue(user.isActivity());
		assertEquals("admin", user.getCreatedBy());
		assertEquals("2013-06-29 13:58:38",
				DateTimeUtils.format(user.getCreateOn()));
		assertNull(user.getModifiedBy());
		assertNull(user.getModifyOn());
	}

	@Test
	public void testGetUserByNameNotExist() {
		User user = userService.getUserByName("notexist");

		assertNull(user);
	}

	@Test
	public void testGetUserById() {
		User user = userService.getUserById(1);

		assertNotNull(user);
		assertEquals("admin", user.getName());
		assertEquals("password", user.getPassword());
		assertEquals("admin", user.getName());
		assertEquals("admin", user.getCreatedBy());
		assertEquals("2013-06-29 13:58:38",
				DateTimeUtils.format(user.getCreateOn()));
		assertNull(user.getModifiedBy());
		assertNull(user.getModifyOn());
		assertTrue(user.isActivity());
	}

	@Test
	public void testCreateNewUser() {
		User newUser = new User();
		newUser.setName("test2");
		newUser.setPassword("test2");
		newUser.setActivity(true);

		newUser = userService.createNewUser(newUser, user);
		assertNotNull(newUser.getId());
		assertEquals(user.getName(), newUser.getCreatedBy());
		assertNotNull(newUser.getCreateOn());
	}

	@Test
	public void testUpdateUser() {
		User newUser = userService.getUserById(1);
		newUser.setPassword("unknow");
		newUser.setActivity(false);
		userService.updateUser(newUser, user);

		newUser = userService.getUserById(1);
		assertEquals("unknow", newUser.getPassword());
		assertFalse(newUser.isActivity());
		assertEquals(user.getName(), newUser.getModifiedBy());
		assertNotNull(newUser.getModifyOn());
	}

	@Test
	public void testFindAll() {
		Iterator<User> users = userService.findAll();
		int total = 0;
		while (users.hasNext()) {
			total++;
			users.next();
		}

		assertEquals(2, total);
	}

}
