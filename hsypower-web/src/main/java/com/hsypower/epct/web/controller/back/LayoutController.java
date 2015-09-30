package com.hsypower.epct.web.controller.back;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin/layout")
@Controller("backLayoutController")
public class LayoutController extends BackController {

	@RequestMapping("/top")
	public String top() {
		return "admin/layout/top";
	}

	@RequestMapping("/below")
	public String below() {
		return "admin/layout/below";
	}

	@RequestMapping("/menu")
	public String menu() {
		return "admin/layout/menu";
	}

	@RequestMapping("/main")
	public String main() {
		return "admin/layout/main";
	}
}
