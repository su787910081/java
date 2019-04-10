package com.jt.sys.service;

import java.util.List;

import com.jt.sys.pojo.SysRole;

public interface SysRoleService {

	List<SysRole> findPageObjects();
	
	int deleteObjects(String[] ids);
}
