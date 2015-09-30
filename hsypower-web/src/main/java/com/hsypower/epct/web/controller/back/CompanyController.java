package com.hsypower.epct.web.controller.back;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hsypower.epct.entity.Company;
import com.hsypower.epct.entity.User;
import com.hsypower.epct.service.ICompanyService;

@RequestMapping("/admin/company")
@Controller("backCompanyController")
public class CompanyController extends BackController {

	@Autowired
	private ICompanyService companyService;

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView goEdit(Company company, @RequestParam String part) {
		company = companyService.getDefaultCompany();

		return new ModelAndView("admin/company/edit_" + part, "company",
				company);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView doEdit(Company company, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		companyService.updateCompany(company, user);

		String part = request.getParameter("part");
		return new ModelAndView("admin/company/edit_" + part, "company",
				company);
	}

}
