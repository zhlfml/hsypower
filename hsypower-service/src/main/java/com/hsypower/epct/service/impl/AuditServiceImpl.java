package com.hsypower.epct.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsypower.epct.dao.IAuditDAO;
import com.hsypower.epct.entity.Audit;
import com.hsypower.epct.service.IAuditService;

@Service
public class AuditServiceImpl implements IAuditService {

	@Autowired
	private IAuditDAO auditDAO;
	
	@Override
	public void createNewAudit(Audit audit) {
		auditDAO.save(audit);
	}

}
