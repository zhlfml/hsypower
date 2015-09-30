package com.hsypower.epct.service.impl;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hsypower.epct.AbstractDBUnitSpringContextTests;
import com.hsypower.epct.entity.Audit;
import com.hsypower.epct.service.IAuditService;

public class AuditServiceImplTest extends AbstractDBUnitSpringContextTests {

	@Autowired
	private IAuditService auditService;

	@Test
	public void createNewAudit() {
		Audit audit = new Audit();
		audit.setLocalIp("127.0.0.1");
		audit.setRemoteIp("211.121.112.0");
		audit.setReferer("http://my.com");
		audit.setUserAgent("Firefox Ubuntu");
		audit.setAccessDate(new Date());
		auditService.createNewAudit(audit);

		assertNotNull(audit.getId());
	}

}
