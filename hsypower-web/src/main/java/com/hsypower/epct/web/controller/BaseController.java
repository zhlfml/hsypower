package com.hsypower.epct.web.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.ServletContext;

import org.springframework.data.domain.Page;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hsypower.epct.BaseEntity;
import com.hsypower.epct.utils.Constants;
import com.hsypower.epct.utils.PropertyUtil;
import com.hsypower.epct.web.handler.ExceptionHandlers;

public abstract class BaseController extends ExceptionHandlers implements
		ServletContextAware {

	protected ServletContext servletContext;

	protected void addPage(ModelAndView mav, Page<? extends BaseEntity> page) {
		mav.addObject("page", page);
		mav.addObject("hasPreviousPage", page.hasPrevious());
		mav.addObject("hasNextPage", page.hasNext());
	}

	protected String uploadFile(CommonsMultipartFile file)
			throws IllegalStateException, IOException {
		
		if (file.getSize() > 0) {
			String uploadDest = PropertyUtil
					.getString(Constants.UPLOAD_FILE_DESTINATION);
			String savePath = servletContext.getRealPath("/") + uploadDest;
			String fileExt = file.getOriginalFilename()
					.substring(file.getOriginalFilename().lastIndexOf(".") + 1)
					.toLowerCase();
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			String newFileName = df.format(new Date()) + "_"
					+ new Random().nextInt(1000) + "." + fileExt;
			File dest = new File(savePath, newFileName);
			dest.mkdirs();
			file.transferTo(dest);
			
			return (uploadDest + newFileName);
		}
		
		return null;
	}

	protected void addCommonObjects(ModelAndView mav) {
		// don't need to implement here
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

}
