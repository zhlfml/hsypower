package com.hsypower.epct.web.controller.back;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hsypower.epct.entity.News;
import com.hsypower.epct.entity.User;
import com.hsypower.epct.service.INewsService;
import com.hsypower.epct.utils.FileUtil;
import com.hsypower.epct.utils.Validator;

@RequestMapping("/admin/news")
@Controller("backNewsController")
public class NewsController extends BackController {

	@Autowired
	private INewsService newsService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String goAdd(News news) {
		return "admin/news/edit";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String doAdd(@Valid News news, BindingResult result,
			HttpServletRequest request) {
		if (result.hasErrors()) {
			return "admin/news/edit";
		}
		User user = (User) request.getSession().getAttribute("user");
		newsService.createNews(news, user);

		return "redirect:/admin/news/list";
	}

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView("admin/news/list");
		mav.addObject("newses", newsService.findAll());

		return mav;
	}

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public ModelAndView goModify(@RequestParam long newsId, News news) {
		ModelAndView mav = new ModelAndView("admin/news/edit");
		news = newsService.getNewsById(newsId);
		mav.addObject("news", news);

		return mav;
	}

	@RequestMapping("/modify/{newsId}")
	public String doModify(@PathVariable long newsId,
			@RequestParam boolean publish, HttpServletRequest request) {
		News news = newsService.getNewsById(newsId);
		news.setPublish(publish);
		User user = (User) request.getSession().getAttribute("user");
		newsService.updateNews(news, user);
        FileUtil.rmdir(servletContext.getRealPath("/") + "/static/news_detail/"
                + news.getId() + ".shtml");

		return "redirect:/admin/news/list";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String doModify(@Valid News news, BindingResult result,
			HttpServletRequest request) {
		if (Validator.isNull(news.getName())) {
			result.rejectValue("name", "error.news.name.null", "标题不能为空");
		}
		if (Validator.isNull(news.getContent())) {
			result.rejectValue("name", "error.news.content.null", "内容不能为空");
		}
		if (result.hasErrors()) {
			return "admin/news/edit";
		}
		User user = (User) request.getSession().getAttribute("user");
		newsService.updateNews(news, user);
		FileUtil.rmdir(servletContext.getRealPath("/") + "/static/news_detail/"
				+ news.getId() + ".shtml");

		return "redirect:/admin/news/list";
	}

}
