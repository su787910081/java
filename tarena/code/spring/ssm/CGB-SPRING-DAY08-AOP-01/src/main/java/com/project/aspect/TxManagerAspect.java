package com.project.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TxManagerAspect {

	@Before("within(com.project.service..*)")
	public void beforeAdvice() {
		System.out.println("TxManagerAspect.beforeAdvice()");
	}
	
	@AfterReturning("within(com.project.service.impl.UserServiceImpl)")
	public void returnAdvice() {
		System.out.println("TxManagerAspect.returnAdvice()");
	}
	
	@AfterThrowing("within(com.project.service.impl.UserServiceImpl)")
	public void afterThrowing() {
		System.out.println("TxManagerAspect.afterThrowing()");
	}
	
	@After("within(com.project.service..*)")
	public void afterAdvice() {
		System.out.println("TxManagerAspect.afterAdvice()");
	}
}
