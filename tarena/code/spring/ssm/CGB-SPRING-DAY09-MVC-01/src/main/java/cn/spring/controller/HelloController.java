package cn.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

// Spring MVC 中的后端控制器
// 请求流程: 
// 1) client request --> tomcat
// 2) tomcat --> DispatcherServlet (web.xml)
// 3) DispatcherServlet --> (查找映射关系: spring_mvc.xml) --> HelloController
// 4) HelloController  -->  handleRequest(..)
// 5) ...
public class HelloController implements Controller {

	// 用于处理请求
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 构建模型和视图对象(作用: 封装数据)
		ModelAndView mv = new ModelAndView();
//		request.setAttribute(arg0, arg1);	// 类似此功能
		mv.addObject("msg", "Hello SpingMVC");
		// 设置呈现数据的页面(例如 hello.jsp)
		mv.setViewName("hello");
		return mv; // (数据，视图)
	}

}
