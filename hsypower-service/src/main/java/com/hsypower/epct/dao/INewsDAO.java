package com.hsypower.epct.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.hsypower.epct.entity.News;

public interface INewsDAO extends CrudRepository<News, Long> {
	
	@Query(value = "select o from News o where o.publish = 1")
	Page<News> findTopNews(Pageable pageable);

}
