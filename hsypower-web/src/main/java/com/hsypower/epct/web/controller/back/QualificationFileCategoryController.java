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

import com.hsypower.epct.entity.QualificationFileCategory;
import com.hsypower.epct.entity.User;
import com.hsypower.epct.service.IQualificationFileCategoryService;
import com.hsypower.epct.utils.Validator;

@RequestMapping("/admin/qualificationFileCategory")
@Controller("backQualificationFileCategoryController")
public class QualificationFileCategoryController extends BackController {

	@Autowired
	private IQualificationFileCategoryService qualificationFileCategoryService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String goAdd(QualificationFileCategory qualificationFileCategory) {
		return "admin/qualificationFileCategory/edit";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String doAdd(QualificationFileCategory qualificationFileCategory,
			BindingResult result, HttpServletRequest request)
			throws IllegalStateException, IOException {
		if (Validator.isNull(qualificationFileCategory.getName())) {
			result.rejectValue("name",
					"error.qualificationFileCategory.name.null", "资质文件类名不能为空");
		}
		if (result.hasErrors()) {
			return "admin/qualificationFileCategory/edit";
		}
		User user = (User) request.getSession().getAttribute("user");
		qualificationFileCategory
				.setFilePath(uploadFile(qualificationFileCategory.getFile()));
		qualificationFileCategoryService.createNewQualificationFileCategory(
				qualificationFileCategory, user);

		return "redirect:/admin/qualificationFileCategory/list";
	}

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView(
				"/admin/qualificationFileCategory/list");
		mav.addObject("qualificationFileCategories",
				qualificationFileCategoryService.findAll());

		return mav;
	}

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public ModelAndView goModify(
			@RequestParam long qualificationFileCategoryId,
			QualificationFileCategory qualificationFileCategory) {
		ModelAndView mav = new ModelAndView(
				"admin/qualificationFileCategory/edit");
		qualificationFileCategory = qualificationFileCategoryService
				.getQualificationFileCategoryById(qualificationFileCategoryId);
		mav.addObject("qualificationFileCategory", qualificationFileCategory);

		return mav;
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String doModify(QualificationFileCategory qualificationFileCategory,
			BindingResult result, HttpServletRequest request)
			throws IllegalStateException, IOException {
		if (Validator.isNull(qualificationFileCategory.getName())) {
			result.rejectValue("name",
					"error.qualificationFileCategory.name.null", "资质文件类名不能为空");
		}
		if (result.hasErrors()) {
			return "admin/qualificationFileCategory/edit";
		}
		User user = (User) request.getSession().getAttribute("user");
		String filePath = uploadFile(qualificationFileCategory.getFile());
		if (Validator.isNotNull(filePath)) {
			qualificationFileCategory.setFilePath(filePath);
		}
		qualificationFileCategoryService.updateQualificationFileCategory(
				qualificationFileCategory, user);

		return "redirect:/admin/qualificationFileCategory/list";
	}

}
