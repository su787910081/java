package com.jt.sys.controller;

import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		return "sys/role_list.html";
	}

	@RequestMapping("doRoleTable")
	public String doRoleTable() {
		return "sys/role_table.html";
	}

	@RequestMapping(value = "doDeleteObjectByString")
	@ResponseBody
	public String doDeleteObjectByString(String checkedIds) {
		System.out.println("SysRoleController.doDeleteObjectByString()");
		System.out.println("checkedIds: " + checkedIds);
		String[] arrIds = checkedIds.split(",");
		System.out.println(Arrays.toString(arrIds));
		for (String str : arrIds) {
			System.out.println(str);
		}
		
		sysRoleService.deleteObjects(arrIds);
		return "delete ok";
	}

	@RequestMapping(value = "doDeleteObjectByArray",method = RequestMethod.POST)
	@ResponseBody
	public String doDeleteObjectByArray(@RequestParam(value = "checkedIds[]")String[] checkedIds) {
		System.out.println("SysRoleController.doDeleteObjectByArray()");
		System.out.println("checkedIds: " + Arrays.toString(checkedIds));
		System.out.println(checkedIds);
		return "delete ok";
	}

	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public List<SysRole> doFindPageObjects(@RequestParam("pageCurrent") String pageCurrent) {

		List<SysRole> list = sysRoleService.findPageObjects();
		System.out.println("SysRoleController.doFindPageObjects(), pageCurrent: " + pageCurrent);
		System.out.println(list);
		for (SysRole role : list) {
			System.out.println(role);
		}
		return list;
	}

	@RequestMapping("doRoleList")
	public String doRoleList() {
		System.out.println("SysRoleController.doRoleList()");
		return "suyh/roleList.jsp";
	}

}
