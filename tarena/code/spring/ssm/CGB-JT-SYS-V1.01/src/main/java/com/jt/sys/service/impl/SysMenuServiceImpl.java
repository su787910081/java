package com.jt.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.common.exception.ServiceException;
import com.jt.common.vo.Node;
import com.jt.sys.dao.SysMenuDao;
import com.jt.sys.pojo.SysMenu;
import com.jt.sys.service.SysMenuService;

@Service
public class SysMenuServiceImpl implements SysMenuService {
	@Autowired
	private SysMenuDao sysMenuDao;

	@Override
	public List<Map<String, Object>> findObjects() {
		return sysMenuDao.findObjects();
	}

	@Override
	public void deleteMenu(Integer id) {
		if (id == null || id <= 0) {
			throw new ServiceException("菜单id 值无效");
		}
		
		int childMenu = sysMenuDao.findChildNumber(id);
		if (childMenu != 0) {
			throw new ServiceException("请先删除子菜单");
		}
		
		int rows = sysMenuDao.deleteMenu(id);
		if (rows == 0) {
			throw new ServiceException("此菜单可能已经不存在");
		}
	}

	@Override
	public List<Node> findZtreeNodes() {
		return sysMenuDao.findZtreeNodes();
	}

	@Override
	public void SaveObject(SysMenu menu) {
		if (menu == null) {
			throw new ServiceException("保存的数据不能为空");
		}
		
		int rows = sysMenuDao.insertObject(menu);
		if (rows != 1) {
			throw new ServiceException("数据保存失败");
		}
	}

	@Override
	public SysMenu findObject(Integer id) {
		if (id == null || id <= 0) {
			throw new ServiceException("无效菜单ID");
		}
		
		return sysMenuDao.findObject(id);
	}

	@Override
	public void updateObject(SysMenu menu) {
		if (menu == null) {
			throw new ServiceException("菜单数据不能为null");
		}
		
		if (menu.getId() == null || menu.getId() <= 0) {
			throw new ServiceException("菜单ID 无效");
		}
		
		sysMenuDao.updateObject(menu);
	}
}
