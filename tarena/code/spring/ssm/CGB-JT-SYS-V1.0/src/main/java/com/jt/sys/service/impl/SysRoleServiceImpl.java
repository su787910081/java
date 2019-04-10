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
}
