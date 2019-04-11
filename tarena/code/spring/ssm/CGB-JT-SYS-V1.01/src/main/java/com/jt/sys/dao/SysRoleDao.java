package com.jt.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jt.sys.pojo.SysRole;

public interface SysRoleDao {

	List<SysRole> findPageObjects();
	SysRole findObjectById(String id);
	int deleteObjects(@Param("ids") String[] ids);
	int deleteObject(Integer id);
	
	int insertObject(SysRole entity);
//	int updateObject(SysRole entity);
//	SysRole findObjectById(Integer id);
}





