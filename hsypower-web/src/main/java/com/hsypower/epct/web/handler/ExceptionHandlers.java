package com.hsypower.epct.web.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

import com.hsypower.epct.exception.AccessDeniedException;
import com.hsypower.epct.exception.NoSuchResourceException;

public abstract class ExceptionHandlers {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@ExceptionHandler
	public ModelAndView handle(NoSuchResourceException nsre) {
		logger.error(nsre.getMessage(), nsre);

		ModelAndView mav = new ModelAndView("error/error");
		mav.addObject("cause", "访问的资源不存在");
		this.addCommonObjects(mav);

		return mav;
	}
	
	@ExceptionHandler
	public ModelAndView handle(AccessDeniedException ade) {
		logger.error(ade.getMessage(), ade);

		ModelAndView mav = new ModelAndView("error/error");
		mav.addObject("cause", "该资源已不可访问");
		this.addCommonObjects(mav);

		return mav;
	}
	
	@ExceptionHandler
	public ModelAndView handle(MaxUploadSizeExceededException musee) {
		logger.error(musee.getMessage(), musee);

		ModelAndView mav = new ModelAndView("error/maxUploadExceeded");
		mav.addObject("cause", "上传文件超过长度(200KB)");

		return mav;
	}
	
	protected abstract void addCommonObjects(ModelAndView mav);
}
