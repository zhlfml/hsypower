package com.hsypower.epct.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.hsypower.epct.AbstractDBUnitSpringContextTests;
import com.hsypower.epct.entity.Company;
import com.hsypower.epct.entity.User;
import com.hsypower.epct.service.ICompanyService;
import com.hsypower.epct.utils.DateTimeUtils;


public class CompanyServiceImplTest extends AbstractDBUnitSpringContextTests {

	private User user;
	
	@Autowired
	private ICompanyService companyService;
	
	@Before
	public void setUp() {
		user = new User();
		user.setName("admin");
	}
	
	@Test
	public void testCreateNewCompany() {
		Company company = new Company();
		company.setAddress("江苏南京溧水");
		company.setDescription("电力公司");
		company.setEmail("a@b.cn");
		company.setIntroduce("百年企业，视质量为生命。");
		company.setKeywords("江苏、电力");
		company.setName("南京华世远电力技术有限公司");
		company.setSaleTel("025-57345632");
		company.setTax("025-57345622");
		company.setTechServiceTel("025-57349622");
		company.setTelephone("025-57345621");
		company.setWebsite("www.hsypower-epct.com");
		company.setZipcode("211200");
		company.setCustomerService("产品质量与售后服务承诺");
		company.setSalesNetwork("全国各地");
		company.setPerfermance("公司近期部分中标项目");
		
		company = companyService.createNewCompany(company, user);
		assertNotNull(company.getId());
		assertEquals(user.getName(), company.getCreatedBy());
		assertNotNull(company.getCreateOn());
	}
	
	@Test
	@DatabaseSetup("CompanyServiceImplTest.xml")
	public void testUpdateCompany() {
		Company company = companyService.getDefaultCompany();
		company.setKeywords("江苏、电力、专业");
		company.setWebsite("new.hsypower-epct.com");
		
		company = companyService.updateCompany(company, user);
		company = companyService.getDefaultCompany();
		assertEquals("江苏、电力、专业", company.getKeywords());
		assertEquals("new.hsypower-epct.com", company.getWebsite());
		assertEquals(user.getName(), company.getModifiedBy());
		assertNotNull(company.getModifyOn());
	}
	
	@Test
	@DatabaseSetup("CompanyServiceImplTest.xml")
	public void testGetDefaultCompany() {
		Company company = companyService.getDefaultCompany();
		
		assertNotNull(company);
		assertEquals("江苏南京溧水", company.getAddress());
		assertEquals("admin", company.getCreatedBy());
		assertEquals("2013-06-29 13:58:38", DateTimeUtils.format(company.getCreateOn()));
		assertEquals("电力公司", company.getDescription());
		assertEquals("a@b.cn", company.getEmail());
		assertEquals("百年企业，视质量为生命。", company.getIntroduce());
		assertEquals("江苏、电力", company.getKeywords());
		assertNull(company.getModifiedBy());
		assertNull(company.getModifyOn());
		assertEquals("南京华世远电力技术有限公司", company.getName());
		assertEquals("025-57345632", company.getSaleTel());
		assertEquals("025-57345622", company.getTax());
		assertEquals("025-57349622", company.getTechServiceTel());
		assertEquals("025-57345621", company.getTelephone());
		assertEquals("www.hsypower-epct.com", company.getWebsite());
		assertEquals("211200", company.getZipcode());
		assertEquals("产品质量与售后服务承诺", company.getCustomerService());
		assertEquals("全国各地", company.getSalesNetwork());
		assertEquals("公司近期部分中标项目", company.getPerfermance());
	}

}
