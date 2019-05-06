package com.jt.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jt.common.vo.CheckBox;
import com.jt.sys.pojo.SysRole;

public interface SysRoleDao {

	List<SysRole> findPageObjects(@Param("name") String name);
	
	/***
	 * @param name 按名字查询时的查询参数
	 * @param startIndex 分页查询时起始页的位置
	 * @param pageSize 每页最多显示多少提交记录
	 * @return
	 */
	List<SysRole> findPageObjects(@Param("name") String name, 
			@Param("startIndex") Integer startIndex, 
			@Param("pageSize") Integer pageSize);
	
	List<CheckBox> findRoleNames();
	
	// 统计记录数
	int getRowCount(@Param("name") String name);

	SysRole findObjectById(String id);
	int deleteObjects(@Param("ids") String[] ids);
	int deleteObject(Integer id);
	
	int insertObject(SysRole entity);
	int updateObject(SysRole entity);
//	SysRole findObjectById(Integer id);
}





