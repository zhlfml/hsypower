package com.hsypower.epct.web.interceptor;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hsypower.epct.entity.User;
import com.hsypower.epct.utils.Validator;
import me.thomas.security.NoPermissionException;
import me.thomas.security.def.PrincipalType;
import me.thomas.security.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hsypower.epct.entity.Channel;
import com.hsypower.epct.exception.AccessDeniedException;
import com.hsypower.epct.service.IChannelService;

public class CheckPathInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	protected IChannelService channelService;
	@Autowired
	protected IPermissionService permissionService;
	
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

		String userId = "guest";
		User user = (User) request.getSession().getAttribute("user");
		if (Validator.isNotNull(user)) {
			userId = String.valueOf(user.getId());
		}

		boolean hasPermission = permissionService.hasPermission(PrincipalType.USER, userId, path.substring(1, path.indexOf('/', 1)), "VIEW");
		if (!hasPermission) {
			throw new NoPermissionException("No permission to access :" + path);
		}

		return super.preHandle(request, response, handler);
	}

}
