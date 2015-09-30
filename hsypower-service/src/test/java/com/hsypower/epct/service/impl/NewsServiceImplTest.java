package com.hsypower.epct.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.hsypower.epct.AbstractDBUnitSpringContextTests;
import com.hsypower.epct.entity.News;
import com.hsypower.epct.entity.User;
import com.hsypower.epct.service.INewsService;
import com.hsypower.epct.utils.DateTimeUtils;

public class NewsServiceImplTest extends AbstractDBUnitSpringContextTests {

	private User user;

	@Autowired
	private INewsService newsService;

	@Before
	public void setUp() {
		user = new User();
		user.setName("admin");
	}

	@Test
	public void testCreateNews() {
		News news = new News();
		news.setName("南京电监办到我司参观审查");
		news.setContent("5月8日，南京电监办两位专家到我司对公司资质进行现场核查。");
		news.setPublish(true);

		news = newsService.createNews(news, user);
		assertNotNull(news.getId());
		assertEquals(user.getName(), news.getCreatedBy());
		assertNotNull(news.getCreateOn());
	}

	@Test
	@DatabaseSetup("NewsServiceImplTest.xml")
	public void testUpdateNews() {
		News news = newsService.getNewsById(1);
		news.setContent("修改内容");

		newsService.updateNews(news, user);
		assertEquals("修改内容", news.getContent());
		assertEquals(user.getName(), news.getModifiedBy());
		assertNotNull(news.getModifyOn());
	}

	@Test
	@DatabaseSetup("NewsServiceImplTest.xml")
	public void testGetNewsById() {
		News news = newsService.getNewsById(1);

		assertNotNull(news);
		assertEquals("南京电监办到我司参观审查", news.getName());
		assertEquals("5月8日，南京电监办两位专家到我司对公司资质进行现场核查。", news.getContent());
		assertTrue(news.isPublish());
		assertEquals("admin", news.getCreatedBy());
		assertEquals("2013-06-29 13:58:38",
				DateTimeUtils.format(news.getCreateOn()));
		assertNull(news.getModifiedBy());
		assertNull(news.getModifyOn());
	}

	@Test
	@DatabaseSetup("NewsServiceImplTest-findTop30.xml")
	public void testFindTopNews() {
		Iterator<News> newses = newsService.findTopNews(30);

		int total = 0;
		News news = null;
		while (newses.hasNext()) {
			total++;
			news = newses.next();
		}

		assertEquals(30, total);
		assertEquals("2013-06-26 13:58:38",
				DateTimeUtils.format(news.getCreateOn()));
	}

	@Test
	@DatabaseSetup("NewsServiceImplTest-findTop30.xml")
	public void testFindAll() {
		Iterator<News> newses = newsService.findAll();

		int total = 0;
		while (newses.hasNext()) {
			total++;
			newses.next();
		}

		assertEquals(36, total);
	}

}
