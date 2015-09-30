package com.hsypower.epct.service.impl;

import java.util.Date;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsypower.epct.dao.ICompanyDAO;
import com.hsypower.epct.entity.Company;
import com.hsypower.epct.entity.User;
import com.hsypower.epct.service.ICompanyService;

@Service
public class CompanyServiceImpl implements ICompanyService {

	@Autowired
	private ICompanyDAO companyDAO;
	
	@Override
	@Transactional
	public Company createNewCompany(Company company, User user) {
		company.setCreatedBy(user.getName());
		company.setCreateOn(new Date());
		
		return companyDAO.save(company);
	}

	@Override
	@Transactional
	public Company updateCompany(Company company, User user) {
		company.setModifiedBy(user.getName());
		company.setModifyOn(new Date());
		
		return companyDAO.save(company);
	}

	@Override
	public Company getDefaultCompany() {
		Iterator<Company> it = companyDAO.findAll().iterator();
		return it.hasNext() ? it.next() : new Company();
	}

}
