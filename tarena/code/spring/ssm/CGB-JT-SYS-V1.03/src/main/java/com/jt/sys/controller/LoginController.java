package com.jt.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.JsonResult;
import com.jt.sys.service.SysUserService;

@RequestMapping("/")
@Controller
public class LoginController {
	@Autowired
	SysUserService sysUserService;

	@RequestMapping("loginUI")
	public String loginUI() {
		return "login";
	}

	@RequestMapping("doLogin")
	@ResponseBody
	public JsonResult doLogin(String username, String password) {
		System.out.println("LoginController.doLogin(), username: " 
				+ username + ", password: " + password);
		sysUserService.login(username, password);

		return new JsonResult(1, "登录成功");
	}
}
