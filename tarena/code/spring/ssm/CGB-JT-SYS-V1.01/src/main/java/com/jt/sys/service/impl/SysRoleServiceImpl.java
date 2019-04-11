package com.jt.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.sys.dao.SysRoleDao;
import com.jt.sys.pojo.SysRole;
import com.jt.sys.service.SysRoleService;

@Service
public class SysRoleServiceImpl implements SysRoleService {
	@Autowired
	private SysRoleDao sysRoleDao;

	@Override
	public List<SysRole> findPageObjects() {
		List<SysRole> list = sysRoleDao.findPageObjects();
		return list;
	}

	@Override
	public int deleteObjects(String[] ids) {
		return sysRoleDao.deleteObjects(ids);
	}

	@Override
	public int saveObject(SysRole entity) {
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
}
