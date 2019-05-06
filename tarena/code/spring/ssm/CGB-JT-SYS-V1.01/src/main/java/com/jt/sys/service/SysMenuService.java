package com.jt.sys.service;

import java.util.List;
import java.util.Map;

import com.jt.common.vo.Node;
import com.jt.sys.pojo.SysMenu;

public interface SysMenuService {
	List<Map<String, Object>> findObjects();

	void deleteMenu(Integer id);
	
	List<Node> findZtreeNodes();

	void SaveObject(SysMenu menu);

	SysMenu findObject(Integer id);

	void updateObject(SysMenu menu);
}
