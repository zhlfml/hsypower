package com.hsypower.epct.service.impl;

import java.util.Date;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsypower.epct.dao.IChannelDAO;
import com.hsypower.epct.entity.Channel;
import com.hsypower.epct.entity.User;
import com.hsypower.epct.service.IChannelService;

@Service
public class ChannelServiceImpl implements IChannelService {

	@Autowired
	private IChannelDAO channelDAO;

	@Override
	@Transactional
	public Channel createNewChannel(Channel channel, User user) {
		channel.setCreatedBy(user.getName());
		channel.setCreateOn(new Date());

		return channelDAO.save(channel);
	}

	@Override
	@Transactional
	public Channel updateChannel(Channel channel, User user) {
		channel.setModifiedBy(user.getName());
		channel.setModifyOn(new Date());

		return channelDAO.save(channel);
	}

	@Override
	public Channel getChannelById(long channelId) {
		return channelDAO.findOne(channelId);
	}
	
	@Override
	public Channel getChannelByPath(String path) {
		return channelDAO.getChannelByPath(path);
	}

	@Override
	public Iterator<Channel> findAll() {
		return channelDAO.findAll(new Sort("sort")).iterator();
	}

	@Override
	public Iterator<Channel> findChannelsAtNavigate() {
		return channelDAO.findChannelsAtNavigate().iterator();
	}

	@Override
	public Iterator<Channel> findChannelsAtLeftSide() {
		return channelDAO.findChannelsAtLeftSide().iterator();
	}

	@Override
	public Iterator<Channel> findChannelsAtBottom() {
		return channelDAO.findChannelsAtBottom().iterator();
	}

	@Override
	public int countChannelsAtNavigate() {
		return channelDAO.findChannelsAtNavigate().size();
	}

}
