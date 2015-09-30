package com.hsypower.epct.service;

import java.util.Iterator;

import com.hsypower.epct.entity.QualificationFile;
import com.hsypower.epct.entity.User;

public interface IQualificationFileService {

	QualificationFile createNewQualificationFile(
			QualificationFile qualificationFile, User user);

	QualificationFile updateQualificationFile(
			QualificationFile qualificationFile, User user);

	QualificationFile getQualificationFileById(long qualificationFileId);

	void deleteQualificationFileById(long qualificationFileId);

	Iterator<QualificationFile> findAll();
}
