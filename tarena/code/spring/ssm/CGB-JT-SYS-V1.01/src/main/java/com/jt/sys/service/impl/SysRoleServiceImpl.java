package com.jt.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.common.exception.ServiceException;
import com.jt.common.vo.CheckBox;
import com.jt.common.vo.PageObject;
import com.jt.sys.dao.SysRoleDao;
import com.jt.sys.pojo.SysRole;
import com.jt.sys.service.SysRoleService;

@Service
public class SysRoleServiceImpl implements SysRoleService {
	@Autowired
	private SysRoleDao sysRoleDao;

	@Override
	public List<SysRole> findPageObjects(String name) {
		return sysRoleDao.findPageObjects(name);
	}

	@Override
	public int deleteObjects(String[] ids) {
		return sysRoleDao.deleteObjects(ids);
	}

	@Override
	public int saveObject(SysRole entity) {
		if ("admin".equals(entity.getName())){
			throw new ServiceException("role's name already exists");
		}
		return sysRoleDao.insertObject(entity);
	}

	@Override
	public int deleteObject(Integer id) {
		return sysRoleDao.deleteObject(id);
	}

	@Override
	public SysRole findObjectById(String id) {
		return sysRoleDao.findObjectById(id);
	}

	@Override
	public int updateObject(SysRole entity) {
		if (entity == null) 
			throw new RuntimeException("更新对象不能为空");
		if (entity.getId() == null)
			throw new RuntimeException("更新对象ID不能为空");
		
		return sysRoleDao.updateObject(entity);
	}

	@Override
	public PageObject<SysRole> findPageObjects(Integer pageCurrent, String name) {
		if (pageCurrent < 1) {
			throw new RuntimeException("页码不能小于1");
		}
		// 当前面数据
		int pageSize = 3;
		int startIndex = (pageCurrent - 1) * pageSize;
		List<SysRole> records = sysRoleDao.findPageObjects(name, startIndex, pageSize);
		// 总记录数
		int rows = sysRoleDao.getRowCount(name);
		// 总页数
		int pageCount = rows / pageSize + (rows % pageSize == 0 ? 0 : 1);
		
		// 封装数据
		PageObject<SysRole> po = new PageObject<SysRole>();
		po.setPageCurrent(pageCurrent);
		po.setRecords(records);
		po.setPageCount(pageCount);
		po.setRowCount(rows);
		return po;
	}

	@Override
	public List<CheckBox> findRoleNames() {
		return sysRoleDao.findRoleNames();
	}
}
