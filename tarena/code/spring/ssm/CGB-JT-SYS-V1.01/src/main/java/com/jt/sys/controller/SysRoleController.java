package com.jt.sys.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.CheckBox;
import com.jt.common.vo.JsonResult;
import com.jt.common.vo.PageObject;
import com.jt.sys.pojo.SysRole;
import com.jt.sys.service.SysRoleService;

@RequestMapping("/role/")
@Controller
public class SysRoleController {

	@Autowired
	private SysRoleService sysRoleService;

	@RequestMapping("listUI")
	public String listUI() {
		return "sys/show_list.html";
	}

	@RequestMapping("editUI")
	public String editUI() {
		return "sys/role_edit.html";
	}

	@RequestMapping("doRoleTable")
	public String doRoleTable() {
		return "sys/role_table.html";
	}
	
	@RequestMapping("doRoleList")
	public String doRoleList() {
		System.out.println("SysRoleController.doRoleList()");
		return "suyh/roleList.jsp";
	}

	
	// #################################################################
	
	
	
	@RequestMapping("doDeleteObject")
	@ResponseBody
	public int doDeleteObject(Integer id) {
		System.out.println("SysRoleController.doDeleteObject()");
		System.out.println(id);
		return sysRoleService.deleteObject(id);
	}

	@RequestMapping(value = "doDeleteObjectByArray",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult doDeleteObjectByArray(@RequestParam(value = "checkedIds[]")String[] checkedIds) {
		sysRoleService.deleteObjects(checkedIds);
		return new JsonResult(1, "delete ok by array");
	}

	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(@RequestParam("pageCurrent") Integer pageCurrent, String name) {
		System.out.println("SysRoleController.doFindPageObjects()");
		System.out.println("name: " + name);
		PageObject<SysRole> po = sysRoleService.findPageObjects(pageCurrent, name);
		return new JsonResult(1, "query ok", po);
	} // {pageCount:10,..., records:[{记录数据}, {}, ...]}

	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindpageObject(String id) {
		SysRole sysRole = sysRoleService.findObjectById(id);
		return new JsonResult(1, "findObjectById ok", sysRole);
	}


	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(SysRole entity) {
		entity.setCreatedUser("admin-suyh");
		entity.setModifiedUser("admin-suyh");
		sysRoleService.saveObject(entity);
		return new JsonResult(1, "save ok");
	}

	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(SysRole entity) {
		sysRoleService.updateObject(entity);
		return new JsonResult(1, "update ok");
	}
	
	@RequestMapping("doFindObjects")
	@ResponseBody
	public JsonResult doFindObjects() {
		List<CheckBox> list = sysRoleService.findRoleNames();
		return new JsonResult(1, "find roles ok", list);
	}

}




