package com.jt.sys.service;

import java.util.List;

import com.jt.common.vo.CheckBox;
import com.jt.common.vo.PageObject;
import com.jt.sys.pojo.SysRole;

public interface SysRoleService {
	List<SysRole> findPageObjects(String name);
	PageObject<SysRole> findPageObjects(Integer pageCurrent, String name);
	
	List<CheckBox> findRoleNames();
	int deleteObjects(String[] ids);
	int saveObject(SysRole entity);
	int deleteObject(Integer id);
	SysRole findObjectById(String id);
	int updateObject(SysRole entity);
}



