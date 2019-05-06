package com.jt.sys.dao;

import java.util.List;
import java.util.Map;

import com.jt.common.vo.Node;
import com.jt.sys.pojo.SysMenu;

public interface SysMenuDao {
	List<Map<String, Object>> findObjects();

	int deleteMenu(Integer id);

	// 查询该菜单ID 有几个子菜单项
	int findChildNumber(Integer parentId);
	
	List<Node> findZtreeNodes();

	int insertObject(SysMenu menu);

	SysMenu findObject(Integer id);

	void updateObject(SysMenu menu);
}
