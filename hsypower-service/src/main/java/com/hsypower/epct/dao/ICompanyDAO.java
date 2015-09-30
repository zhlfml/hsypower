package com.hsypower.epct.dao;

import org.springframework.data.repository.CrudRepository;

import com.hsypower.epct.entity.Company;

public interface ICompanyDAO extends CrudRepository<Company, Long> {
	
}
