package com.jt.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SysUserRoleDao {
	int insertObject(@Param("userId") Integer userId, @Param("roleIds") Integer[] roleIds);
	
	List<Integer> findObjectByUserId(Integer userId);

	int deleteRolesByUserId(Integer userId);
}
