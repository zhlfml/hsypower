package com.hsypower.epct.web.interceptor;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hsypower.epct.entity.Channel;
import com.hsypower.epct.exception.AccessDeniedException;
import com.hsypower.epct.service.IChannelService;

public class CheckPathInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	protected IChannelService channelService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		String path = request.getServletPath();
		Iterator<Channel> channels = channelService.findAll();
		while(channels.hasNext()) {
			Channel channel = channels.next();
			if (path.indexOf(channel.getPath()) > -1) {
				if (!channel.isDisplay()) {
					throw new AccessDeniedException("Access denied :" + path);
				}
			}
		}

		return super.preHandle(request, response, handler);
	}

}
