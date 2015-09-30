package com.hsypower.epct.service;

import java.util.Iterator;

import com.hsypower.epct.entity.Channel;
import com.hsypower.epct.entity.User;

public interface IChannelService {

	Channel createNewChannel(Channel channel, User user);

	Channel updateChannel(Channel channel, User user);

	Channel getChannelById(long channelId);
	
	Channel getChannelByPath(String path);

	Iterator<Channel> findAll();

	Iterator<Channel> findChannelsAtNavigate();

	Iterator<Channel> findChannelsAtLeftSide();

	Iterator<Channel> findChannelsAtBottom();
	
	int countChannelsAtNavigate();
}
