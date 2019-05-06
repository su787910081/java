package com.jt.sys.service;


import java.util.Map;

import com.jt.common.vo.PageObject;
import com.jt.sys.pojo.SysUser;

public interface SysUserService {
	// 根据id 查询用户信息以及用户关联的角色信息
	// @return 包含用户信息以及角色信息
	Map<String, Object> findObjectById(Integer userId);
	
	PageObject<SysUser> findPageObjects(String username, Integer currentPage);
	
	int validById(Integer id, Integer valid, String modifiedUser);
	
	int insertObject(SysUser entity, Integer[] roleIds);

	// 更新用户信息
	void updateObject(SysUser user, Integer[] roleIds);
}
