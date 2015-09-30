package com.hsypower.epct.web.controller;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hsypower.epct.entity.News;
import com.hsypower.epct.entity.Product;
import com.hsypower.epct.entity.ProductCategory;
import com.hsypower.epct.exception.AccessDeniedException;
import com.hsypower.epct.exception.NoSuchResourceException;
import com.hsypower.epct.service.INewsService;
import com.hsypower.epct.service.IProductCategoryService;
import com.hsypower.epct.service.IProductService;

@Controller
public class DetailController extends FrontController {

	@Autowired
	private INewsService newsService;

	@Autowired
	private IProductService productService;

	@Autowired
	private IProductCategoryService productCategoryService;
	
	@RequestMapping("/news_detail/{newsId}")
	public ModelAndView news(@PathVariable("newsId") long newsId) {
		ModelAndView mav = new ModelAndView("detail/news_detail");
		mav.addObject("current", "/news");
		News news = newsService.getNewsById(newsId);
		if (news == null) {
			throw new NoSuchResourceException("News " + newsId + " does not exist.");
		}
		if (!news.isPublish()) {
			throw new AccessDeniedException("News " + newsId + " access denied.");
		}
		mav.addObject("news", news);
		this.addCommonObjects(mav);

		return mav;
	}

	@RequestMapping("/product_detail/{productId}")
	public ModelAndView product(@PathVariable("productId") long productId) {
		ModelAndView mav = new ModelAndView("detail/product_detail");
		Iterator<ProductCategory> productCategories = productCategoryService
				.findAll();
		mav.addObject("productCategories", productCategories);
		Product product = productService.getProductById(productId);
		if (product == null) {
			throw new NoSuchResourceException("Product which id is " + productId + " does not exist.");
		}
		mav.addObject("product", product);
		mav.addObject("currentCategory", product.getProductCategory().getId());
		Product prevProduct = productService.getPrevProductById(product
				.getProductCategory().getId(), productId);
		mav.addObject("prevProduct", prevProduct);
		Product nextProduct = productService.getNextProductById(product
				.getProductCategory().getId(), productId);
		mav.addObject("nextProduct", nextProduct);
		this.addCommonObjects(mav);

		return mav;
	}
	
}
