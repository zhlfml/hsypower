package com.hsypower.epct.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.hsypower.epct.entity.Channel;

public interface IChannelDAO extends CrudRepository<Channel, Long> {
	
	Channel getChannelByPath(String path);
	
	List<Channel> findAll(Sort sort);
	
	@Query("from Channel o where o.navigate = 1 and o.display = 1 order by o.sort")
	List<Channel> findChannelsAtNavigate();

	@Query("from Channel o where o.leftSide = 1 and o.display = 1 order by o.sort")
	List<Channel> findChannelsAtLeftSide();

	@Query("from Channel o where o.bottom = 1 and o.display = 1 order by o.sort")
	List<Channel> findChannelsAtBottom();
}
