package com.jt.sys.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.JsonResult;
import com.jt.common.vo.PageObject;
import com.jt.sys.pojo.SysUser;
import com.jt.sys.service.SysUserService;

@RequestMapping("/user/")
@Controller
public class SysUserController {
	@Autowired
	private SysUserService sysUserService;

	@RequestMapping("listUI")
	public String listUI() {
		return "sys/show_list.html";
	}

	@RequestMapping("editUI")
	public String editUI() {
		return "sys/user_edit.html";
	}

	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(String username, Integer pageCurrent) {
		PageObject<SysUser> pou = sysUserService.findPageObjects(username, pageCurrent);

		return new JsonResult(1, "fin page objects ok", pou);
	}

	@RequestMapping("doValidById")
	@ResponseBody
	public JsonResult doValidById(Integer id, Integer valid) {
		sysUserService.validById(id, valid, "admin");

		return new JsonResult(1, "do valid by id OK");
	}
	
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(SysUser user, @RequestParam(value="checkedIds[]") Integer[] roleIds) {
		
		sysUserService.insertObject(user, roleIds);
		return new JsonResult(1, "add ok");
	}
	
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(SysUser user, @RequestParam(value="checkedIds[]") Integer[] roleIds) {
		sysUserService.updateObject(user, roleIds);

		return new JsonResult(1, "update object OK");
	}
	
	
	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(@RequestParam("id") Integer userId) {
		Map<String, Object> map = sysUserService.findObjectById(userId);
		return new JsonResult(1, "find user info ok", map);
	}
	
	
}





