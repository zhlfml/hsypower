package com.hsypower.epct.service;

import com.hsypower.epct.entity.Company;
import com.hsypower.epct.entity.User;

public interface ICompanyService {

	Company createNewCompany(Company company, User user);
	
	Company updateCompany(Company company, User user);
	
	Company getDefaultCompany();
}
