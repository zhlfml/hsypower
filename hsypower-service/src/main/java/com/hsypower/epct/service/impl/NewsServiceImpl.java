package com.hsypower.epct.service.impl;

import java.util.Date;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsypower.epct.dao.INewsDAO;
import com.hsypower.epct.entity.News;
import com.hsypower.epct.entity.User;
import com.hsypower.epct.service.INewsService;

@Service
public class NewsServiceImpl implements INewsService {

	@Autowired
	private INewsDAO newsDAO;

	@Override
	@Transactional
	public News createNews(News news, User user) {
		news.setCreatedBy(user.getName());
		news.setCreateOn(new Date());

		return newsDAO.save(news);
	}

	@Override
	@Transactional
	public News updateNews(News news, User user) {
		news.setModifiedBy(user.getName());
		news.setModifyOn(new Date());

		return newsDAO.save(news);
	}

	@Override
	public News getNewsById(long newsId) {
		return newsDAO.findOne(newsId);
	}

	@Override
	public Iterator<News> findTopNews(int number) {
		return newsDAO
				.findTopNews(
						new PageRequest(0, number, new Sort(Direction.DESC,
								"createOn"))).iterator();
	}

	@Override
	public Iterator<News> findAll() {
		return newsDAO.findAll().iterator();
	}

}
