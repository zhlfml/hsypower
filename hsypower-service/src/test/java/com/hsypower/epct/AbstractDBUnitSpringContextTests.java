package com.hsypower.epct;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;

@ActiveProfiles(profiles = { "test" })
@ContextConfiguration(locations = { "/applicationContext.xml" })
@TestExecutionListeners(listeners = { TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
public abstract class AbstractDBUnitSpringContextTests extends AbstractJUnit4SpringContextTests {
	
}
