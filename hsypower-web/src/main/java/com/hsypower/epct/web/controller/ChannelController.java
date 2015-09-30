package com.hsypower.epct.web.controller;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hsypower.epct.entity.News;
import com.hsypower.epct.entity.Product;
import com.hsypower.epct.entity.ProductCategory;
import com.hsypower.epct.entity.QualificationFileCategory;
import com.hsypower.epct.service.INewsService;
import com.hsypower.epct.service.IProductCategoryService;
import com.hsypower.epct.service.IProductService;
import com.hsypower.epct.service.IQualificationFileCategoryService;
import com.hsypower.epct.utils.ParamUtil;
import com.hsypower.epct.utils.Validator;

@Controller
public class ChannelController extends FrontController {

	@Autowired
	private INewsService newsService;

	@Autowired
	private IQualificationFileCategoryService qualificationFileCategoryService;

	@Autowired
	private IProductService productService;

	@Autowired
	private IProductCategoryService productCategoryService;

	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("channel/index");
		mav.addObject("current", "/index");
		this.addCommonObjects(mav);
		Iterator<Product> products = productService.findAll();
		mav.addObject("products", products);

		return mav;
	}

	@RequestMapping("/news")
	public ModelAndView news(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("channel/news");
		mav.addObject("current", "/news");
		this.addCommonObjects(mav);
		int items = ParamUtil.get(request, "items", 24);
		Iterator<News> topNews = newsService.findTopNews(items);
		mav.addObject("topNews", topNews);

		return mav;
	}

	@RequestMapping("/service")
	public ModelAndView service() {
		ModelAndView mav = new ModelAndView("channel/service");
		mav.addObject("current", "/service");
		this.addCommonObjects(mav);

		return mav;
	}

	@RequestMapping("/introduce")
	public ModelAndView introduce() {
		ModelAndView mav = new ModelAndView("channel/introduce");
		mav.addObject("current", "/introduce");
		this.addCommonObjects(mav);

		return mav;
	}

	@RequestMapping("/contact")
	public ModelAndView contact() {
		ModelAndView mav = new ModelAndView("channel/contact");
		mav.addObject("current", "/contact");
		this.addCommonObjects(mav);

		return mav;
	}

	@RequestMapping("/sales")
	public ModelAndView sales() {
		ModelAndView mav = new ModelAndView("channel/sales");
		mav.addObject("current", "/sales");
		this.addCommonObjects(mav);

		return mav;
	}

	@RequestMapping("/perfermance")
	public ModelAndView perfermance() {
		ModelAndView mav = new ModelAndView("channel/perfermance");
		mav.addObject("current", "/perfermance");
		this.addCommonObjects(mav);

		return mav;
	}

	@RequestMapping("/qualification/{categoryId}")
	public ModelAndView qualification(
			@PathVariable("categoryId") long categoryId) {
		ModelAndView mav = new ModelAndView("channel/qualification");
		mav.addObject("current", "/qualification");
		this.addCommonObjects(mav);
		QualificationFileCategory qualificationFileCategory = qualificationFileCategoryService
				.getQualificationFileCategoryById(categoryId);
		mav.addObject("qualificationFileCategory", qualificationFileCategory);
		if (Validator.isNull(qualificationFileCategory)) {
			mav.addObject("nextQualificationFileCategory", null);
			mav.addObject("next2QualificationFileCategory", null);
		} else {
			QualificationFileCategory nextQualificationFileCategory = qualificationFileCategoryService
					.findNextQualificationFileCategoryById(categoryId);
			mav.addObject("nextQualificationFileCategory",
					nextQualificationFileCategory);
			QualificationFileCategory next2QualificationFileCategory = qualificationFileCategoryService
					.findNextQualificationFileCategoryById(nextQualificationFileCategory
							.getId());
			mav.addObject("next2QualificationFileCategory",
					next2QualificationFileCategory);
		}

		return mav;
	}

	@RequestMapping("/qualification")
	public String qualification() {
		long qualificationFileCategoryId = 0;
		QualificationFileCategory nextQualificationFileCategory = qualificationFileCategoryService
				.findNextQualificationFileCategoryById(qualificationFileCategoryId);

		if (nextQualificationFileCategory != null) {
			qualificationFileCategoryId = nextQualificationFileCategory.getId();
		}

		return "forward:/qualification/" + qualificationFileCategoryId;
	}

	@RequestMapping("/product/{categoryId}")
	public ModelAndView product(@PathVariable("categoryId") long categoryId,
			HttpServletRequest request) {
		int pageNo = ParamUtil.getInteger(request, "pageNo");
		ModelAndView mav = new ModelAndView("channel/product");
		mav.addObject("current", "/product");
		this.addCommonObjects(mav);
		mav.addObject("currentCategory", categoryId);
		Iterator<ProductCategory> productCategories = productCategoryService
				.findAll();
		mav.addObject("productCategories", productCategories);
		Page<Product> page = productService.findProductsByCategoryId(
				categoryId, pageNo, 8);
		this.addPage(mav, page);

		return mav;
	}

	@RequestMapping("/product")
	public String product() {
		long productCategoryId = 0;
		Iterator<ProductCategory> productCategories = productCategoryService
				.findAll();

		if (productCategories.hasNext()) {
			productCategoryId = productCategories.next().getId();
		}

		return "forward:/product/" + productCategoryId;
	}

}
