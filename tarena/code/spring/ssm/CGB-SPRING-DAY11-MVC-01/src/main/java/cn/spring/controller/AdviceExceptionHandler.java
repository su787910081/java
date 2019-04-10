package cn.spring.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

// 说明：使用@ControllerAdvice 注解描述的类，一般作为全局的异常处理类
@ControllerAdvice
public class AdviceExceptionHandler {
	@ExceptionHandler(Throwable.class)
	@ResponseBody
	public String handlerException(Throwable e) {
		return e.getMessage();
	}
}
