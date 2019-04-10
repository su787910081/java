package com.jt.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jt.sys.pojo.SysRole;

public interface SysRoleDao {

	List<SysRole> findPageObjects();
	int deleteObjects(@Param("ids") String[] ids);
//	int insertObject(SysRole entity);
//	int updateObject(SysRole entity);
//	SysRole findObjectById(Integer id);
}





