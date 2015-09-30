package com.hsypower.epct.service.impl;

import java.util.Date;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsypower.epct.dao.IQualificationFileDAO;
import com.hsypower.epct.entity.QualificationFile;
import com.hsypower.epct.entity.User;
import com.hsypower.epct.service.IQualificationFileService;

@Service
public class QualificationFileServiceImpl implements IQualificationFileService {

	@Autowired
	private IQualificationFileDAO qualificationFileDAO;

	@Override
	@Transactional
	public QualificationFile createNewQualificationFile(
			QualificationFile qualificationFile, User user) {
		qualificationFile.setCreatedBy(user.getName());
		qualificationFile.setCreateOn(new Date());

		return qualificationFileDAO.save(qualificationFile);
	}

	@Override
	@Transactional
	public QualificationFile updateQualificationFile(
			QualificationFile qualificationFile, User user) {
		qualificationFile.setModifiedBy(user.getName());
		qualificationFile.setModifyOn(new Date());

		return qualificationFileDAO.save(qualificationFile);
	}

	@Override
	public QualificationFile getQualificationFileById(long qualificationFileId) {
		return qualificationFileDAO.findOne(qualificationFileId);
	}

	@Override
	@Transactional
	public void deleteQualificationFileById(long qualificationFileId) {
		qualificationFileDAO.delete(qualificationFileId);
	}

	@Override
	public Iterator<QualificationFile> findAll() {
		return qualificationFileDAO.findAll().iterator();
	}

}
