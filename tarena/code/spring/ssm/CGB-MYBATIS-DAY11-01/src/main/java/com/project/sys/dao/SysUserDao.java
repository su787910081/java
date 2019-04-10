package com.project.sys.dao;

import java.util.List;
import java.util.Map;

import com.project.sys.entity.SysUser;

public interface SysUserDao {

	List<Map<String, Object>> findUsers();
	
	Map<String, Object> findUserById(Integer id);
	
	int insertObject(SysUser entity);
}
