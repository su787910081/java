package com.jt.sys.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.JsonResult;
import com.jt.common.vo.Node;
import com.jt.sys.pojo.SysMenu;
import com.jt.sys.service.SysMenuService;

@RequestMapping("/menu/")
@Controller
public class SysMenuController {
	@Autowired
	private SysMenuService sysMenuService;

	@RequestMapping("listUI")
	public String listUI() {
		System.out.println("SysMenuController.listUI()");
		return "sys/menu_list.html";
	}
	
	@RequestMapping("editUI")
	public String editUI() {
		System.out.println("SysMenuController.editUI()");
		return "sys/menu_edit.html";
	}
	
	@RequestMapping("doFindObject")
	@ResponseBody
	public JsonResult doFindObject(Integer id) {
		System.out.println("SysMenuController.doFindObject(), id: " + id);
		SysMenu menu = sysMenuService.findObject(id);
		return new JsonResult(1, "find menu ok", menu);
	}

	@RequestMapping("doFindObjects")
	@ResponseBody
	public JsonResult doFindObjects() {
		List<Map<String, Object>> list = sysMenuService.findObjects();
		return new JsonResult(1, "find object ok", list);
	}
	
	@RequestMapping("doDeletemenu")
	@ResponseBody
	public JsonResult doDeletemenu(Integer id) {
		System.out.println("SysMenuController.doDeletemenu(), id: " + id);
		
		sysMenuService.deleteMenu(id);
		return new JsonResult(1, "do delete menu ok");
	}
	
	@RequestMapping("treeUI")
	@ResponseBody
	public JsonResult treeUI() {
		System.out.println("SysMenuController.treeUI()");
		List<Node> list = sysMenuService.findZtreeNodes();
		return new JsonResult(1, "treeUI ok", list);
	}
	
	@RequestMapping("SaveObject")
	@ResponseBody
	public JsonResult SaveObject(SysMenu menu) {
		System.out.println("SysMenuController.doAddMenu(), menu: " + menu);
		
		sysMenuService.SaveObject(menu);
		return new JsonResult(1, "add menu ok");
	}
	
	@RequestMapping("doUpdateMenu")
	@ResponseBody
	public JsonResult doUpdateMenu(SysMenu menu) {
		System.out.println("SysMenuController.doUpdateMenu(), id: " + menu);
		sysMenuService.updateObject(menu);
		return new JsonResult(1, "update menu ok");
	}
}
