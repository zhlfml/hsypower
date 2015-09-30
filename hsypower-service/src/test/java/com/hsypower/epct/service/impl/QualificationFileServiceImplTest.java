package com.hsypower.epct.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.hsypower.epct.AbstractDBUnitSpringContextTests;
import com.hsypower.epct.entity.QualificationFile;
import com.hsypower.epct.entity.QualificationFileCategory;
import com.hsypower.epct.entity.User;
import com.hsypower.epct.service.IQualificationFileCategoryService;
import com.hsypower.epct.service.IQualificationFileService;
import com.hsypower.epct.utils.DateTimeUtils;

@DatabaseSetup("QualificationFileServiceImplTest.xml")
public class QualificationFileServiceImplTest extends
		AbstractDBUnitSpringContextTests {

	private User user;

	@Autowired
	private IQualificationFileService qualificationFileService;

	@Autowired
	private IQualificationFileCategoryService qualificationFileCategoryService;

	@Before
	public void setUp() {
		user = new User();
		user.setName("admin");
	}

	@Test
	public void testCreateNewQualificationFile() {
		QualificationFileCategory qualificationFileCategory = qualificationFileCategoryService
				.getQualificationFileCategoryById(1);
		QualificationFile qualificationFile = new QualificationFile();
		qualificationFile.setName("企业法人营业执照");
		qualificationFile.setFilePath("qualificationFile/qyfzyyzz.jepg");
		qualificationFile
				.setQualificationFileCategory(qualificationFileCategory);

		qualificationFileService.createNewQualificationFile(qualificationFile,
				user);
		assertNotNull(qualificationFile.getId());
		assertEquals(user.getName(), qualificationFile.getCreatedBy());
		assertNotNull(qualificationFile.getCreateOn());
		assertEquals(qualificationFileCategory.getName(), qualificationFile
				.getQualificationFileCategory().getName());
	}

	@Test
	public void testUpdateQualificationFile() {
		QualificationFile qualificationFile = qualificationFileService
				.getQualificationFileById(1);
		qualificationFile.setName("税务登记证");

		qualificationFile = qualificationFileService.updateQualificationFile(
				qualificationFile, user);
		assertEquals("税务登记证", qualificationFile.getName());
		assertEquals(user.getName(), qualificationFile.getModifiedBy());
		assertNotNull(qualificationFile.getModifyOn());
	}

	@Test
	public void testGetQualificationFileById() {
		QualificationFile qualificationFile = qualificationFileService
				.getQualificationFileById(1);

		assertEquals("企业法人营业执照", qualificationFile.getName());
		assertEquals("admin", qualificationFile.getCreatedBy());
		assertEquals("2013-06-29 13:58:38",
				DateTimeUtils.format(qualificationFile.getCreateOn()));
		assertNull(qualificationFile.getModifiedBy());
		assertNull(qualificationFile.getModifyOn());
	}

	@Test
	public void testDeleteQualificationFileById() {
		qualificationFileService.deleteQualificationFileById(1);
		QualificationFile qualificationFile = qualificationFileService
				.getQualificationFileById(1);

		assertNull(qualificationFile);
	}

	@Test
	public void testFindAll() {
		Iterator<QualificationFile> qualificationFiles = qualificationFileService
				.findAll();
		int total = 0;
		while (qualificationFiles.hasNext()) {
			total++;
			qualificationFiles.next();
		}

		assertEquals(2, total);
	}

}
