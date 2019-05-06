package com.jt.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jt.sys.pojo.SysUser;

public interface SysUserDao {
	int insertObject(SysUser entity);

	// 根据ID查询用户信息
	SysUser findObjectById(Integer id);
	
	List<SysUser> findPageObjects(
			@Param("username") String username,
			@Param("startIndex") Integer startIndex,
			@Param("pageSize") Integer pageSize);

	int getRowCount(@Param("username") String username);
	
	int validById(@Param("id") Integer id, @Param("valid") Integer valid, 
			@Param("modifiedUser") String modifiedUser);

	// 更新用户信息
	void updateObjectById(SysUser user);
}




