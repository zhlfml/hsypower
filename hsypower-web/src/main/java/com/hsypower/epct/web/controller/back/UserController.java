package com.hsypower.epct.web.controller.back;

import java.util.Iterator;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hsypower.epct.entity.User;
import com.hsypower.epct.service.IUserService;
import com.hsypower.epct.utils.Validator;

@RequestMapping("/admin/user")
@Controller("backUserController")
public class UserController extends BackController {

	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String goAdd(User user) {
		return "admin/user/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String doAdd(User newUser, BindingResult result, HttpSession session) {
		User operator = (User) session.getAttribute("user");
		if (Validator.isNotNull(userService.getUserByName(newUser.getName()))) {
			result.rejectValue("name", "error.user.name.exist", "用户名已存在");
		}
		if (!newUser.getPassword().equals(newUser.getConfirmPwd())) {
			result.rejectValue("confirmPwd", "error.user.confirmPwd.wrong",
					"两次密码输入不匹配");
		}
		if (result.hasErrors()) {
			return "admin/user/add";
		}
		userService.createNewUser(newUser, operator);

		return "redirect:/admin/user/list";
	}

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView("admin/user/list");
		Iterator<User> users = userService.findAll();
		mav.addObject("users", users);

		return mav;
	}

	@RequestMapping("/modify")
	public String doModify(@RequestParam("userId") long userId,
			@RequestParam("activity") boolean activity, HttpSession session) {
		User operator = (User) session.getAttribute("user");
		User user = userService.getUserById(userId);
		user.setActivity(activity);
		userService.updateUser(user, operator);

		return "redirect:/admin/user/list";
	}

	@RequestMapping(value = "/changePwd", method = RequestMethod.GET)
	public String goChangePwd(User newUser, HttpSession session) {
		User user = (User) session.getAttribute("user");
		newUser.setId(user.getId());

		return "admin/user/changePwd";
	}

	@RequestMapping(value = "/changePwd", method = RequestMethod.POST)
	public String doChangePwd(User newUser, BindingResult result,
			HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (!user.getPassword().equals(newUser.getOldPwd())) {
			result.rejectValue("oldPwd", "error.user.oldPwd.wrong", "原密码不正确");
		}
		if (user.getPassword().equals(newUser.getPassword())) {
			result.rejectValue("password", "error.user.password.equals.oldPwd",
					"新密码不能与原密码相同");
		}
		if (!newUser.getPassword().equals(newUser.getConfirmPwd())) {
			result.rejectValue("confirmPwd", "error.user.confirmPwd.wrong",
					"两次密码输入不匹配");
		}
		if (!result.hasErrors()) {
			user.setPassword(newUser.getPassword());
			userService.updateUser(user, user);
			result.rejectValue("id", "success.user.change.password", "密码修改成功");
		}

		return "admin/user/changePwd";
	}
}
