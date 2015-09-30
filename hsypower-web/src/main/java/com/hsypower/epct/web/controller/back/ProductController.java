package com.hsypower.epct.web.controller.back;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hsypower.epct.entity.Product;
import com.hsypower.epct.entity.User;
import com.hsypower.epct.service.IProductCategoryService;
import com.hsypower.epct.service.IProductService;
import com.hsypower.epct.utils.FileUtil;
import com.hsypower.epct.utils.Validator;

@RequestMapping("/admin/product")
@Controller("backProductController")
public class ProductController extends BackController {

	@Autowired
	private IProductService productService;

	@Autowired
	private IProductCategoryService productCategoryService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView goAdd(Product product) {
		ModelAndView mav = new ModelAndView("admin/product/edit");
		mav.addObject("productCategories", productCategoryService.findAll());

		return mav;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String doAdd(Product product, BindingResult result,
			HttpServletRequest request) throws IllegalStateException,
			IOException {
		if (Validator.isNull(product.getName())) {
			result.rejectValue("name", "error.product.name.null", "产品名称不能为空");
		}
		if (result.hasErrors()) {
			return "admin/product/edit";
		}
		User user = (User) request.getSession().getAttribute("user");
		product.setIcon(uploadFile(product.getFile()));
		productService.createNewProduct(product, user);

		return "redirect:/admin/product/list";
	}

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView("admin/product/list");
		mav.addObject("products", productService.findAll());

		return mav;
	}

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public ModelAndView goModify(@RequestParam long productId, Product product) {
		ModelAndView mav = new ModelAndView("admin/product/edit");
		product = productService.getProductById(productId);
		mav.addObject("product", product);
		mav.addObject("productCategories", productCategoryService.findAll());

		return mav;
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String doModify(Product product, BindingResult result,
			HttpServletRequest request) throws IllegalStateException,
			IOException {
		if (Validator.isNull(product.getName())) {
			result.rejectValue("name", "error.product.name.null", "产品名称不能为空");
		}
		if (result.hasErrors()) {
			return "admin/product/edit";
		}
		User user = (User) request.getSession().getAttribute("user");
		String icon = uploadFile(product.getFile());
		if (Validator.isNotNull(icon)) {
			product.setIcon(icon);
		}
		productService.updateProduct(product, user);
		FileUtil.rmdir(servletContext.getRealPath("/")
				+ "/static/product_detail/" + product.getId() + ".shtml");

		return "redirect:/admin/product/list";
	}

}
