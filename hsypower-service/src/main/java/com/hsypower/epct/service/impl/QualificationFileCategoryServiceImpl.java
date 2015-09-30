package com.hsypower.epct.service.impl;

import java.util.Date;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsypower.epct.dao.IQualificationFileCategoryDAO;
import com.hsypower.epct.entity.QualificationFileCategory;
import com.hsypower.epct.entity.User;
import com.hsypower.epct.service.IQualificationFileCategoryService;

@Service
public class QualificationFileCategoryServiceImpl implements
		IQualificationFileCategoryService {

	@Autowired
	private IQualificationFileCategoryDAO qualificationFileCategoryDAO;

	@Override
	@Transactional
	public QualificationFileCategory createNewQualificationFileCategory(
			QualificationFileCategory qualificationFileCategory, User user) {
		qualificationFileCategory.setCreatedBy(user.getName());
		qualificationFileCategory.setCreateOn(new Date());

		return qualificationFileCategoryDAO.save(qualificationFileCategory);
	}

	@Override
	@Transactional
	public QualificationFileCategory updateQualificationFileCategory(
			QualificationFileCategory qualificationFileCategory, User user) {
		qualificationFileCategory.setModifiedBy(user.getName());
		qualificationFileCategory.setModifyOn(new Date());

		return qualificationFileCategoryDAO.save(qualificationFileCategory);
	}

	@Override
	public QualificationFileCategory getQualificationFileCategoryById(
			long qualificationFileCategoryId) {
		return qualificationFileCategoryDAO
				.findOne(qualificationFileCategoryId);
	}

	@Override
	public QualificationFileCategory findNextQualificationFileCategoryById(
			long qualificationFileCategoryId) {
		Iterator<QualificationFileCategory> allQualificationFileCategories = this
				.findAll();
		boolean firstIterator = true;
		QualificationFileCategory firstQualificationFileCategory = null;
		while (allQualificationFileCategories.hasNext()) {
			QualificationFileCategory qualificationFileCategory = allQualificationFileCategories
					.next();
			if (firstIterator) {
				firstIterator = false;
				firstQualificationFileCategory = qualificationFileCategory;
			}
			if (qualificationFileCategory.getId() > qualificationFileCategoryId) {
				return qualificationFileCategory;
			}
		}

		return firstQualificationFileCategory;
	}

	@Override
	public Iterator<QualificationFileCategory> findAll() {
		return qualificationFileCategoryDAO.findAll(new Sort("id")).iterator();
	}

}
