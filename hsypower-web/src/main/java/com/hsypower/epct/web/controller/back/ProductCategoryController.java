package com.hsypower.epct.web.controller.back;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hsypower.epct.entity.ProductCategory;
import com.hsypower.epct.entity.User;
import com.hsypower.epct.service.IProductCategoryService;
import com.hsypower.epct.utils.Validator;

@RequestMapping("/admin/productCategory")
@Controller("backProductCategoryController")
public class ProductCategoryController extends BackController {

	@Autowired
	private IProductCategoryService productCategoryService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String goAdd(ProductCategory productCategory) {
		return "admin/productCategory/edit";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String doAdd(ProductCategory productCategory, BindingResult result,
			HttpServletRequest request) {
		if (Validator.isNull(productCategory.getName())) {
			result.rejectValue("name", "error.productCategory.name.null",
					"产品类型名称不能为空");
		}
		if (result.hasErrors()) {
			return "admin/productCategory/edit";
		}
		User user = (User) request.getSession().getAttribute("user");
		productCategoryService.createNewProductCategory(productCategory, user);

		return "redirect:/admin/productCategory/list";
	}

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView("admin/productCategory/list");
		mav.addObject("productCategories", productCategoryService.findAll());

		return mav;
	}

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public ModelAndView goModify(@RequestParam long productCategoryId,
			ProductCategory productCategory) {
		ModelAndView mav = new ModelAndView("admin/productCategory/edit");
		productCategory = productCategoryService
				.getProductCategoryById(productCategoryId);
		mav.addObject("productCategory", productCategory);

		return mav;
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String doModify(ProductCategory productCategory,
			BindingResult result, HttpServletRequest request) {
		if (Validator.isNull(productCategory.getName())) {
			result.rejectValue("name", "error.productCategory.name.null",
					"产品类型名称不能为空");
		}
		if (result.hasErrors()) {
			return "admin/productCategory/edit";
		}
		User user = (User) request.getSession().getAttribute("user");
		productCategoryService.updateProductCategory(productCategory, user);

		return "redirect:/admin/productCategory/list";
	}

}
