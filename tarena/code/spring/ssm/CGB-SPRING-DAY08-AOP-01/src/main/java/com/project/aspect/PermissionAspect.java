package com.project.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.project.service.Permission;


// 执行权限检测的切面
@Aspect
@Component
public class PermissionAspect {
	/**
	 * 
	 * @param joinpoint 表示一个连接点,这个连接点对应的是要执行的那个核心业务方法对象
	 */
	@Before("execution(* com.project.service..*.*(..))")
	public void checkPermission(JoinPoint joinpoint) {
		System.out.println("权限检测");
		
		// 获取连接点对应的方法签名信息(对应核心方法信息)
		Signature s = joinpoint.getSignature();
		String methodName = s.getName();
		System.out.println("methodName = " + methodName);
		Object[] args = joinpoint.getArgs();
		Class<?>[] params = new Class<?>[args.length];
		for (int i = 0; i < args.length; i++) {
			Object a = args[i];
			System.out.println("a = " + a);
			System.out.println("a.class: " + a.getClass());
			params[i] = a.getClass();
		}
		
		// 这里获取到的是那个目标对象，而非代理对象
		Class<?> targetCls = joinpoint.getTarget().getClass();
		System.out.println("target class: " + targetCls);
		Method m = null;
		try {
			if (params == null || params.length == 0) {
				m = targetCls.getDeclaredMethod(methodName);
			} else {
				m = targetCls.getDeclaredMethod(methodName, params);	
			}
			System.out.println("method: " + m);
		} catch (Exception e) {
			e.printStackTrace();
			return ;
		}
		if (m.isAnnotationPresent(Permission.class)) {
			System.out.println("执行此方法需要权限检测: " + m.getName());
		} else {
			System.out.println("执行此方法不需要权限检测: " + m.getName());
		}
	}
}
