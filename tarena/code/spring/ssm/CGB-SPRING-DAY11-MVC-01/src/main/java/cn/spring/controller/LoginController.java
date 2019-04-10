package cn.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/sys/")
@Controller
public class LoginController {

	@RequestMapping("doLogin")
	@ResponseBody
	public String doLogin() {
		System.out.println("doLogin");
		return "login  OK";
	}
}
