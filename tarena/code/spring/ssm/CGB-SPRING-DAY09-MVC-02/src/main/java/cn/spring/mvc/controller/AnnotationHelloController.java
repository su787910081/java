package cn.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//http://localhost:80/[项目名称]/hello/doSayHello.do
// hello --> annotationHelloController
@RequestMapping("/hello/")
@Controller
public class AnnotationHelloController {

	// @param Model 用于封装数据
	// http://localhost:80/[项目名称]/[类映射@RequestMapping]/doSayHello.do
	// SimpleUrlHandlerMapping
	// 通过@RequestMapping 注解定义映射关系
	@RequestMapping(value="doSayHello")
	public String doSayHello(Model model) {
		model.addAttribute("msg", "Hello AnnotationHelloController");
		System.out.println("model: " + model);
		return "hello"; // 此字符串会交给视图解析器
	}

	// http://localhost:80/[项目名称]/[类映射@RequestMapping]/doSaveMsg.do?msg=message
	@RequestMapping("doSaveMsg")
	public ModelAndView doSaveMsg(String msg) {
		System.out.println("msg = " + msg);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("success");
		mv.addObject("msg", msg);
		return mv; // /WEB-INF/pages/success.jsp
	}

	// http://localhost:80/[项目名称]/[类映射@RequestMapping]/doSayMsg/message.do
	// http://localhost:80/[项目名称]/[类映射@RequestMapping]/doSayMsg/aaaaaaa.do
	// @RequestMapping("doSayMsg/{?}")
	// public ModelAndView doSayMsg(@PathVariable("?") String msg) {
	// {abc}: abc 是一个占位符，它可以是任意一个字符串，能通过@PathVariable("abc") 来得到这个位置的值
	@RequestMapping("doSayMsg/{abc}")	// rest 风格 
	public ModelAndView doSayMsg(@PathVariable("abc") String msg) {
		System.out.println("msg = " + msg);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("success");
		mv.addObject("msg", msg);
		return mv; // /WEB-INF/pages/success.jsp
	}
}
