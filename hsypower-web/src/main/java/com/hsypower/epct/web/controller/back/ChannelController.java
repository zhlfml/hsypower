package com.hsypower.epct.web.controller.back;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hsypower.epct.entity.Channel;
import com.hsypower.epct.entity.User;
import com.hsypower.epct.service.IChannelService;

@RequestMapping("/admin/channel")
@Controller("backChannelController")
public class ChannelController extends BackController {

	@Autowired
	private IChannelService channelService;

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView("admin/channel/list");
		Iterator<Channel> channels = channelService.findAll();
		mav.addObject("channels", channels);

		return mav;
	}

	@RequestMapping("/saveSort")
	public String saveSort(@RequestParam("id") long[] ids,
			@RequestParam("sort") int[] sorts, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		for (int i = 0, length = ids.length; i < length; i++) {
			Channel channel = channelService.getChannelById(ids[i]);
			channel.setSort(sorts[i]);
			channelService.updateChannel(channel, user);
		}

		return "redirect:/admin/channel/list";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public ModelAndView goModify(@RequestParam("channelId") long channelId,
			Channel channel) {
		ModelAndView mav = new ModelAndView("admin/channel/modify");
		channel = channelService.getChannelById(channelId);
		mav.addObject(channel);

		return mav;
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(Channel channel, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		channelService.updateChannel(channel, user);

		return "redirect:/admin/channel/list";
	}
}
