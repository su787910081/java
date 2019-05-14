package cn.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SysUserInterceptor implements HandlerInterceptor {

	// �˷�����Controller ҵ�񷽷�ִ��֮ǰִ��
	// @return ������������ִ�л�������
	// false: ��ʾ����
	// true: ��ʾ����
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("SysUserInterceptor.preHandle()");
		return true;
	}

	// �˷�����Controller ҵ�񷽷�ִ��֮��ִ��
	/***
	 * 
	 * @param request
	 * @param response
	 * @param handler ָ������Controller ����
	 * @param modelAndView
	 * @throws Exception
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("SysUserInterceptor.postHandle()");
		System.out.println("modelandview: " + modelAndView.getClass());
	}

	// �˷�����Controller ҵ�񷽷�ִ�н�����������ͼ����OK֮��ִ��
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("SysUserInterceptor.afterCompletion()");
	}

}