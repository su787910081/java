package com.jt.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class IndexController {

	@RequestMapping("indexUI")
	public String indexUI() {
		return "starter.html";
	}

	@RequestMapping("pageUI")
	public String pageUI() {
		System.out.println("IndexController.pageUI()");
		return "sys/common/page.html";
	}
}
