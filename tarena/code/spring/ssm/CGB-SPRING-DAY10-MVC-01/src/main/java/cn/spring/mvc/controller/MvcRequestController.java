package cn.spring.mvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.spring.mvc.entity.Message;

@RequestMapping("/req/")
@Controller
public class MvcRequestController {

	@RequestMapping("doShowUI")
	public String doShowUI() {
		
		return "request";
	}

	// 借助 servlet 原生API 处理请求参数
	// HttpServletRequest 对应的jar 包在哪？  tomcat/jar
	// 什么场景下直接使用HttpServletRequest 对象
	// 获取请求数据？
	// 当需要获取请求头，请求体等内部数据时，还要对请求数据进行编码等数据处理，此时可考虑使用HttpServletRequest
	@RequestMapping("doSaveObject")
	public String doSaveObject(HttpServletRequest request) {
		String id = request.getParameter("id");
		String content = request.getParameter("content");
		System.out.println("id = " + id);
		System.out.println("content = " + content);
		
		return "request";
	}
	
	// 直接借助请求参数名(id=100)相同的变量(Integer id)接收请求数据[id].
	// 底层会做哪些事情?
	// 1) 通过反射获取方法对象
	// 2) 通过反射获取参数信息(名字，类型)
	// 3) 获取与请求参数名字对应的方法参数信息
	// 4) 根据对应的参数信息进行类型转换，以及赋值操作
	// 需要注意: 
	// 1) 当借助方法参数接收页面数据时，参数类型最好为对象类型。
	// 2) 当方法参数名与请求参数名不一致时，使用 @RequestParam(name="tid") 来指定接收哪个参数的值
	// 3) 当方法中某个参数必须要求在页面中有对应参数时，使用 @RequestParam(required=true) 来指定
	@RequestMapping("doSaveObject02")
	public String doSaveObject02(@RequestParam(name="tid", required=true) Integer id, String content) {
		System.out.println("ttid = " + id);
		System.out.println("ttcontent = " + content);
		return "request";
	}
	
	// 当请求参数比较多的情况。可考虑使用JaveBean 对象来接收
	// 问题1: 请求中的参数信息是如何封装到Message 对象中的？
	//	a). 基于无参构造函数构建msg 对象(如果没有将会报错)
	// 	b). 调用msg 对象的set 方法来对这个bean 对象进行数据注入(id <--> setId)
	@RequestMapping("doSaveObject03")
	public String doSaveObject03(Message msg) {
		System.out.println(msg);
		return "request";
	}
	
	@RequestMapping("doUpdateObject")
	public String doUpdateObject(Integer id) {
		System.out.println("MvcRequestController.doUpdateObject(), id=" + id);
		return "request";
	}
	
	@RequestMapping("doDeleteObject/{idParam}")
	public String doDeleteObject(@PathVariable("idParam") Integer id) {
		System.out.println("MvcRequestController.doDeleteObject(), id=" + id);
		return "request";
	}
	
	// @ResponseBody：它将返回一个数据内容。而不是一个页面
	// @ResponseBody注解作用：该注解作用于将Controller的方法返回的对象，
	// 通过适当的HttpMessageConverter转换为指定格式后，写入到Response对象的body数据区，
	// 使用场景：返回的数据不是Html标签的页面，而是其他数据格式的数据时，（如Json、xml等）使用；
	@RequestMapping("doFindObjects")
	@ResponseBody
	public String doFindObjects(Integer pageCurrent) {
		return "pageCurrent: " + pageCurrent;
	}
	
	
	// @RequestHeader 标识请求头中某一个数据
	@RequestMapping("doWithHeader")
	@ResponseBody
	public String doWithHeader(@RequestHeader String Accept) {
		return "obtain header accept = " + Accept;
	}
	
	// 获取到所有Header 中的数据
	@RequestMapping("doWithEntry")
	@ResponseBody
	public String doWithEntry(HttpEntity<String> entity) {
		return "headers = " + entity.getHeaders();
	}
	
	// 获取Cookie 中的值，需要用到@CookieValue 注解
	@RequestMapping("doWithCookie")
	@ResponseBody
	public String doWithCookie(@CookieValue(name="JSESSIONID") String jsid) {
		return "cookie.JSESSIONID: " + jsid;
	}
}





