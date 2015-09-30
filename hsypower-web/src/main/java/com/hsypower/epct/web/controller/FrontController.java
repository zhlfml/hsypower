package com.hsypower.epct.web.controller;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.hsypower.epct.entity.Channel;
import com.hsypower.epct.entity.Company;
import com.hsypower.epct.service.IChannelService;
import com.hsypower.epct.service.ICompanyService;

public class FrontController extends BaseController {

	@Autowired
	protected ICompanyService companyService;
	
	@Autowired
	protected IChannelService channelService;
	
	@Override
	protected void addCommonObjects(ModelAndView mav) {
		Company company = companyService.getDefaultCompany();
		Iterator<Channel> navigate = channelService.findChannelsAtNavigate();
		Iterator<Channel> leftSide = channelService.findChannelsAtLeftSide();
		Iterator<Channel> bottom = channelService.findChannelsAtBottom();
		int numOfNavi = channelService.countChannelsAtNavigate();
		
		mav.addObject("company", company);
		mav.addObject("navigate", navigate);
		mav.addObject("leftSide", leftSide);
		mav.addObject("bottom", bottom);
		mav.addObject("numOfNavi", numOfNavi);
	}
	
}
