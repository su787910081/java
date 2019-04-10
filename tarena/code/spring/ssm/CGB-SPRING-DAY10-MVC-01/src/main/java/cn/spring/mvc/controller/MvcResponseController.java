package cn.spring.mvc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RequestMapping("/resp/")
@Controller
public class MvcResponseController {

	@RequestMapping("doModelAndView")
	public ModelAndView doModelAndView(ModelAndView mv) {
		mv.addObject("msg", "Hello suyh");
		mv.setViewName("response");
		return mv;
	}
	
	@RequestMapping("doModel")
	public String doModel(Model model) {
		System.out.println("model=" + model.getClass());
		model.addAttribute("msg", "hello model");
		return "response";
	}
	
	@RequestMapping("doMap")
	public String doMap(@RequestParam Map<String, Object> map, HttpSession session) {
		map.put("msg", "map value");
		session.setAttribute("user", "suyh");
//		return "response";	// 请求转发
		return "redirect:doWithUI.do";	// 请求重定向
	}
	
	@RequestMapping("doWithUI")
	public String withUI() {
		return "response";
	}
	
	@RequestMapping("doJson")
	@ResponseBody
	public Map<String, Object> doMapToJson() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", 10);
		map.put("content", "content-A");
		return map;
	}
	
	@RequestMapping("doList")
	@ResponseBody
	public List<Map<String, Object>> doListToJson() {
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> map1 = new HashMap<>();
		map1.put("id", 1);
		map1.put("content", "content-A");
		Map<String, Object> map2 = new HashMap<>();
		map2.put("id", 2);
		map2.put("content", "content-B");
		list.add(map1);
		list.add(map2);
		
		return list;
	}
	
	@RequestMapping("doListJson")
	@ResponseBody
	public String doListJson() {
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> map1 = new HashMap<>();
		map1.put("id", 1);
		map1.put("content", "content-A");
		Map<String, Object> map2 = new HashMap<>();
		map2.put("id", 2);
		map2.put("content", "content-B");
		list.add(map1);
		list.add(map2);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonStr;
		try {
			jsonStr = mapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
			return "failed json";
		}
//		System.out.println(jsonStr);

		return jsonStr;
	}
}
