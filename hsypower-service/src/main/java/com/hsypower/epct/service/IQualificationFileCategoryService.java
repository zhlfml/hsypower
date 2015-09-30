package com.hsypower.epct.service;

import java.util.Iterator;

import com.hsypower.epct.entity.QualificationFileCategory;
import com.hsypower.epct.entity.User;

public interface IQualificationFileCategoryService {

	QualificationFileCategory createNewQualificationFileCategory(
			QualificationFileCategory qualificationFileCategory, User user);

	QualificationFileCategory updateQualificationFileCategory(
			QualificationFileCategory qualificationFileCategory, User user);

	QualificationFileCategory getQualificationFileCategoryById(
			long qualificationFileCategoryId);
	
	QualificationFileCategory findNextQualificationFileCategoryById(
			long qualificationFileCategoryId);

	Iterator<QualificationFileCategory> findAll();
}
