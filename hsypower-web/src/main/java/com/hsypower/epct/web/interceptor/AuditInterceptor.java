package com.hsypower.epct.web.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hsypower.epct.utils.GetterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hsypower.epct.entity.Audit;
import com.hsypower.epct.service.IAuditService;
import com.hsypower.epct.utils.IpUtil;
import com.hsypower.epct.utils.Validator;

public class AuditInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private IAuditService auditService;

	@Override
	public boolean preHandle(final HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		String url = request.getScheme() + "://" + request.getServerName();
        final String referer = GetterUtil.getString(request.getHeader("Referer"));

		if (!referer.startsWith(url) && handler instanceof HandlerMethod) {
			final String remoteIp = IpUtil.getRemoteIp(request);
			final String userAgent = request.getHeader("User-Agent");

			(new Thread() {
				@Override
				public void run() {
					Audit audit = new Audit();
					audit.setReferer(referer);
					audit.setLocalIp(IpUtil.getLocalIp2());
					if (Validator.isNull(audit.getLocalIp())) {
						audit.setLocalIp(IpUtil.getLocalIp());
					}
					audit.setRemoteIp(remoteIp);
					audit.setUserAgent(userAgent);
					audit.setAccessDate(new Date());

					auditService.createNewAudit(audit);
				}
			}).start();
		}

		return super.preHandle(request, response, handler);
	}

}
