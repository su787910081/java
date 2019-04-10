package com.project.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogginAspect {
	
	@Before("bean(userServiceImpl)")
	public void beforeAdvice() {
		System.out.println("log.beforeAdvice");
	}
	
	@After("bean(userServiceImpl)")
	public void afterAdvice() {
		System.out.println("log.afterAdvice");
	}
}
