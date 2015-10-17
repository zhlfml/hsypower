package com.hsypower.epct.web.controller.back;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hsypower.epct.entity.QualificationFile;
import com.hsypower.epct.entity.User;
import com.hsypower.epct.service.IQualificationFileCategoryService;
import com.hsypower.epct.service.IQualificationFileService;
import com.hsypower.epct.utils.Validator;

@RequestMapping("/admin/qualificationFile")
@Controller("backQualificationFileController")
public class QualificationFileController extends BackController {

	@Autowired
	private IQualificationFileService qualificationFileService;

	@Autowired
	private IQualificationFileCategoryService qualificationFileCategoryService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView goAdd(QualificationFile qualificationFile) {
		ModelAndView mav = new ModelAndView("admin/qualificationFile/edit");
		mav.addObject("qualificationFileCategories",
				qualificationFileCategoryService.findAll());

		return mav;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String doAdd(@Valid QualificationFile qualificationFile,
			BindingResult result, HttpServletRequest request)
			throws IllegalStateException, IOException {
		if (result.hasErrors()) {
			return "admin/qualificationFile/edit";
		}
		User user = (User) request.getSession().getAttribute("user");
		qualificationFile.setFilePath(uploadFile(qualificationFile.getFile()));
		qualificationFileService.createNewQualificationFile(qualificationFile,
				user);

		return "redirect:/admin/qualificationFile/list";
	}

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView("admin/qualificationFile/list");
		mav.addObject("qualificationFiles", qualificationFileService.findAll());

		return mav;
	}

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public ModelAndView goModify(@RequestParam long qualificationFileId,
			QualificationFile qualificationFile) {
		ModelAndView mav = new ModelAndView("admin/qualificationFile/edit");
		qualificationFile = qualificationFileService
				.getQualificationFileById(qualificationFileId);
		mav.addObject("qualificationFile", qualificationFile);
		mav.addObject("qualificationFileCategories",
				qualificationFileCategoryService.findAll());

		return mav;
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String doModify(@Valid QualificationFile qualificationFile,
			BindingResult result, HttpServletRequest request)
			throws IllegalStateException, IOException {
		if (result.hasErrors()) {
			return "admin/qualificationFile/edit";
		}
		User user = (User) request.getSession().getAttribute("user");
		String filePath = uploadFile(qualificationFile.getFile());
		if (Validator.isNotNull(filePath)) {
			qualificationFile.setFilePath(filePath);
		}
		qualificationFileService.updateQualificationFile(qualificationFile,
				user);

		return "redirect:/admin/qualificationFile/list";
	}

}
