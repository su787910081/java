package com.jt.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.sys.pojo.SysRole;
import com.jt.sys.service.SysRoleService;

@RequestMapping("/role/")
@Controller
public class SysRoleController {
	
	@Autowired
	private SysRoleService sysRoleService;
	
	@RequestMapping("listUI")
	public String listUI() {
		return "sys/role_list";
	}
	
	@RequestMapping("mainUI")
	public String mainUI() {
		return "sys/main";
	}
	
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public List<SysRole> doFindPageObjects(@RequestParam("pageCurrent") String pageCurrent) {
		System.out.println("SysRoleController.doFindPageObjects(), pageCurrent: " + pageCurrent);
		List<SysRole> list = sysRoleService.findPageObjects();
		
		return list;
	}
	
	@RequestMapping("doFindGet")
	public String doFindGet() {
		return "sys/rolesGet";
	}
	
	@RequestMapping("doFindPost")
	public String doFindPost() {
		return "sys/rolesPost";
	}
	
	@RequestMapping("doFindPostTable")
	public String doFindPostTable() {
		return "sys/rolesPostTable";
	}
	

}
