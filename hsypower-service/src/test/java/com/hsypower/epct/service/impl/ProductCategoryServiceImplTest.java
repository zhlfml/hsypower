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
import com.hsypower.epct.entity.ProductCategory;
import com.hsypower.epct.entity.User;
import com.hsypower.epct.service.IProductCategoryService;
import com.hsypower.epct.utils.DateTimeUtils;

@DatabaseSetup("ProductServiceImplTest.xml")
public class ProductCategoryServiceImplTest extends
		AbstractDBUnitSpringContextTests {

	private User user;

	@Autowired
	private IProductCategoryService productCategoryService;

	@Before
	public void setUp() {
		user = new User();
		user.setName("admin");
	}

	@Test
	public void testCreateNewProductCategory() {
		ProductCategory productCategory = new ProductCategory();
		productCategory.setName("01.低压成套设备");

		productCategoryService.createNewProductCategory(productCategory, user);
		assertNotNull(productCategory.getId());
		assertEquals(user.getName(), productCategory.getCreatedBy());
		assertNotNull(productCategory.getCreateOn());
	}

	@Test
	public void testUpdateProductCategory() {
		ProductCategory productCategory = productCategoryService
				.getProductCategoryById(1);
		productCategory.setName("01.低压成套设备2");

		productCategory = productCategoryService.updateProductCategory(
				productCategory, user);
		assertEquals("01.低压成套设备2", productCategory.getName());
		assertEquals(user.getName(), productCategory.getModifiedBy());
		assertNotNull(productCategory.getModifyOn());
	}

	@Test
	public void testGetProductCategoryById() {
		ProductCategory productCategory = productCategoryService
				.getProductCategoryById(1);

		assertEquals("01.低压成套设备", productCategory.getName());
		assertEquals("admin", productCategory.getCreatedBy());
		assertEquals("2013-06-29 13:58:38",
				DateTimeUtils.format(productCategory.getCreateOn()));
		assertNull(productCategory.getModifiedBy());
		assertNull(productCategory.getModifyOn());
	}

	@Test
	public void testFindAllProductCategory() {
		Iterator<ProductCategory> productCategory = productCategoryService
				.findAll();

		assertTrue(productCategory.hasNext());
		productCategory.next();
		assertTrue(productCategory.hasNext());
		productCategory.next();
		assertTrue(productCategory.hasNext());
		productCategory.next();
		assertTrue(productCategory.hasNext());
		productCategory.next();
		assertTrue(productCategory.hasNext());
		productCategory.next();
		assertFalse(productCategory.hasNext());
	}

}
