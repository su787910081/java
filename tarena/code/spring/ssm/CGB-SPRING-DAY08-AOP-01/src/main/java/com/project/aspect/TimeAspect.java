package com.project.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeAspect {

	@Around("bean(userServiceImpl)")
	public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("TimeAspect.aroundAdvice()");
		try {
			System.out.println("@Before");
			// 执行目标方法
			Object result = joinPoint.proceed();
			System.out.println("@After");
			// 返回正确的执行结果.
			return result;
		} catch (Throwable e) {
			e.printStackTrace();
			System.out.println("@AfterThrowing");
			// 将异常继续抛出
			throw e;
		}
	}
}
