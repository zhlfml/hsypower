package com.hsypower.epct;

import java.util.Iterator;

import com.hsypower.epct.entity.User;

public interface IBaseService<E> {

	E delete(E e);
	
	E getById(long id);
	
	Iterator<E> findAll();

	E update(E e, User user);

	E createNew(E e, User user);
}
