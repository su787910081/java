package cn.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SysUserInterceptor implements HandlerInterceptor {

	// 此方法在Controller 业务方法执行之前执行
	// @return 决定对请求是执行还是拦截
	// false: 表示拦截
	// true: 表示放行
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("SysUserInterceptor.preHandle()");
		return true;
	}

	// 此方法在Controller 业务方法执行之后执行
	/***
	 * 
	 * @param request
	 * @param response
	 * @param handler 指向具体的Controller 对象
	 * @param modelAndView
	 * @throws Exception
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("SysUserInterceptor.postHandle()");
		System.out.println("modelandview: " + modelAndView.getClass());
	}

	// 此方法在Controller 业务方法执行结束，并且视图解析OK之后执行
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("SysUserInterceptor.afterCompletion()");
	}

}
