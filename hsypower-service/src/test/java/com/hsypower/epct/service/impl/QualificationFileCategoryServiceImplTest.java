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
import com.hsypower.epct.entity.QualificationFileCategory;
import com.hsypower.epct.entity.User;
import com.hsypower.epct.service.IQualificationFileCategoryService;
import com.hsypower.epct.utils.DateTimeUtils;

@DatabaseSetup("QualificationFileCategoryServiceImplTest.xml")
public class QualificationFileCategoryServiceImplTest extends
		AbstractDBUnitSpringContextTests {

	private User user;

	@Autowired
	private IQualificationFileCategoryService qualificationFileCategoryService;

	@Before
	public void setUp() {
		user = new User();
		user.setName("admin");
	}

	@Test
	public void testCreateNewQualificationFileCategory() {
		QualificationFileCategory qualificationFileCategory = new QualificationFileCategory();
		qualificationFileCategory.setName("公司基本资料");
		qualificationFileCategory.setFilePath("qualificationFile/gsjbzl.jepg");

		qualificationFileCategoryService.createNewQualificationFileCategory(
				qualificationFileCategory, user);
		assertNotNull(qualificationFileCategory.getId());
		assertEquals(user.getName(), qualificationFileCategory.getCreatedBy());
		assertNotNull(qualificationFileCategory.getCreateOn());
	}

	@Test
	public void testUpdateQualificationFileCategory() {
		QualificationFileCategory qualificationFileCategory = qualificationFileCategoryService
				.getQualificationFileCategoryById(1);
		qualificationFileCategory.setName("公司设计、安装资质");

		qualificationFileCategory = qualificationFileCategoryService
				.updateQualificationFileCategory(qualificationFileCategory,
						user);
		assertEquals("公司设计、安装资质", qualificationFileCategory.getName());
		assertEquals(user.getName(), qualificationFileCategory.getModifiedBy());
		assertNotNull(qualificationFileCategory.getModifyOn());
	}

	@Test
	public void testGetQualificationFileCategoryById() {
		QualificationFileCategory qualificationFileCategory = qualificationFileCategoryService
				.getQualificationFileCategoryById(1);

		assertEquals("公司基本资料", qualificationFileCategory.getName());
		assertEquals("qualificationFile/gsjbzl.jepg",
				qualificationFileCategory.getFilePath());
		assertEquals("admin", qualificationFileCategory.getCreatedBy());
		assertEquals("2013-06-29 13:58:38",
				DateTimeUtils.format(qualificationFileCategory.getCreateOn()));
		assertNull(qualificationFileCategory.getModifiedBy());
		assertNull(qualificationFileCategory.getModifyOn());
	}

	@Test
	public void testFindNextQualificationFileCategoryById() {
		QualificationFileCategory nextQualificationFileCategory = qualificationFileCategoryService
				.findNextQualificationFileCategoryById(3);

		assertEquals("公司基本资料", nextQualificationFileCategory.getName());
		assertEquals("qualificationFile/gsjbzl.jepg",
				nextQualificationFileCategory.getFilePath());
		assertEquals("admin", nextQualificationFileCategory.getCreatedBy());
		assertEquals("2013-06-29 13:58:38",
				DateTimeUtils.format(nextQualificationFileCategory
						.getCreateOn()));
		assertNull(nextQualificationFileCategory.getModifiedBy());
		assertNull(nextQualificationFileCategory.getModifyOn());
	}

	@Test
	public void testFindAllQualificationFileCategories() {
		Iterator<QualificationFileCategory> qualificationFileCategories = qualificationFileCategoryService
				.findAll();

		assertTrue(qualificationFileCategories.hasNext());
		qualificationFileCategories.next();
		assertTrue(qualificationFileCategories.hasNext());
		qualificationFileCategories.next();
		assertTrue(qualificationFileCategories.hasNext());
		qualificationFileCategories.next();
		assertFalse(qualificationFileCategories.hasNext());
	}

}
