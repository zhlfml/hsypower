package com.hsypower.epct.service;

import java.util.Iterator;

import com.hsypower.epct.entity.News;
import com.hsypower.epct.entity.User;

public interface INewsService {

	News createNews(News news, User user);
	
	News updateNews(News news, User user);
	
	News getNewsById(long newsId);
	
	Iterator<News> findTopNews(int number);
	
	Iterator<News> findAll();

}
