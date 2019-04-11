package com.jt.sys.service;

import java.util.List;

import com.jt.sys.pojo.SysRole;

public interface SysRoleService {

	List<SysRole> findPageObjects();
	SysRole findObjectById(String id);
	
	int deleteObjects(String[] ids);
	
	int deleteObject(Integer id);

	int saveObject(SysRole entity);
}
