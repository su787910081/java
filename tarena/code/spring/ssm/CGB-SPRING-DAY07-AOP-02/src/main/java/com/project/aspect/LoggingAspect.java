package com.project.aspect;

import org.springframework.stereotype.Component;

// 此类型的对象要封装扩展业务
@Component
public class LoggingAspect {
	// 希望此方法在业务方法执行之前执行
	public void beforeMethod() {
		System.out.println("LoggingAspect.beforeMethod()");
	}
	// 希望此方法在业务方法执行之后执行
	public void afterMethod() {
		System.out.println("LoggingAspect.afterMethod()");
	}
}
