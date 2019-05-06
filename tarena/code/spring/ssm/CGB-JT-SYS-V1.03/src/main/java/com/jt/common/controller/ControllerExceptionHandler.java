package com.jt.common.controller;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.exception.ServiceException;
import com.jt.common.vo.JsonResult;


/** 借助注解(@ControllerAdvice)描述此类为全局异常处理类 
 * 整个系统所有的异常只要没有处理的都会到这里做最后的处理
 * */
@ControllerAdvice
public class ControllerExceptionHandler {
	/***
	 * @ExceptionHandler 用于描述这个方法能够 处理的异常.
	 * @param e
	 */
	@ExceptionHandler(ServiceException.class)
	@ResponseBody
	public JsonResult handleServiceException(ServiceException e) {
		e.printStackTrace();
		return new JsonResult(0, e.getMessage());
	}

	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public JsonResult handleRuntimeException(RuntimeException e) {
		e.printStackTrace();
		String msg = e.getMessage();

		if (e instanceof IncorrectCredentialsException) {
			msg = "密码错误";
		} else if (e instanceof AuthenticationException) {
			msg = "认证失败";
		} else if (e instanceof UnauthorizedException) {
			msg = "没有权限";
			System.out.println("没有权限");
		}

		return new JsonResult(0, msg);
	}
}
