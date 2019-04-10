package cn.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/sys/")
@Controller
public class SysUserController {

	@RequestMapping("doSaveObject")
	@ResponseBody
	public String doSaveObject() {
		System.out.println("SysUserController.doSaveObject()");
		return "Save OK";
	}
	
	// 定义Controller 中的异常处理方法，这些方法需要借助ExceptionHandler 注解进行描述，
	// 注解 中的内容表示我这个方法能够处理的异常(包括这个异常的子类类型)
	@ExceptionHandler(value=Exception.class)
	@ResponseBody
	public String handleException(Exception e) {
		return e.getMessage();
	}
}
