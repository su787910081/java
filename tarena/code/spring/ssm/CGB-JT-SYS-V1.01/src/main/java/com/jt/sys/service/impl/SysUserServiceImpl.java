package com.jt.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.common.exception.ServiceException;
import com.jt.common.vo.PageObject;
import com.jt.sys.dao.SysUserDao;
import com.jt.sys.dao.SysUserRoleDao;
import com.jt.sys.pojo.SysUser;
import com.jt.sys.service.SysUserService;

@Service
public class SysUserServiceImpl implements SysUserService {
	@Autowired
	private SysUserDao sysUserDao;
	
	@Autowired
	private SysUserRoleDao sysUserRoleDao;

	@Override
	public PageObject<SysUser> findPageObjects(String username, Integer currentPage) {
		int pageSize = 3;
		int rowCount = sysUserDao.getRowCount(username);	// 总记录数
		int startIndex = (currentPage - 1) * pageSize;
		int pageCount = 0;	// 总页数
		pageCount = rowCount / pageSize + (rowCount % pageSize == 0 ? 0 : 1);
		
		List<SysUser> users = sysUserDao.findPageObjects(username, startIndex, pageSize);
		PageObject<SysUser> pou = new PageObject<SysUser>();
		pou.setPageCount(pageCount);
		pou.setPageCurrent(currentPage);
		pou.setRowCount(rowCount);
		pou.setRecords(users);
		return pou;
	}

	@Override
	public int validById(Integer id, Integer valid, String modifiedUser) {
		if (id == null || id < 0) 
			throw new ServiceException("id 值无效: " + id);
		if (valid == null || valid < 0)
			throw new ServiceException("状态值无效: " + valid);
		
		int rows = sysUserDao.validById(id, valid, modifiedUser);
		
		return rows;
	}

	@Override
	public int insertObject(SysUser entity, Integer[] roleIds) {
		sysUserDao.insertObject(entity);
		if (roleIds != null && roleIds.length != 0) {
			sysUserRoleDao.insertObject(entity.getId(), roleIds);	
		}
		return 0;
	}

	@Override
	public Map<String, Object> findObjectById(Integer userId) {
		SysUser user = sysUserDao.findObjectById(userId);
		List<Integer> roleIds = sysUserRoleDao.findObjectByUserId(userId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sysUser", user);
		map.put("roleIds", roleIds);
		return map;
	}

	@Override
	public void updateObject(SysUser user, Integer[] roleIds) {
		System.out.println("SysUserServiceImpl.updateObject()");
		
		if (user == null || user.getId() == null) {
			throw new ServiceException("必须指定用户id");
		}
		if (roleIds == null || roleIds.length == 0) {
			throw new ServiceException("角色信息不能为空");
		}
		
		sysUserDao.updateObjectById(user);
		sysUserRoleDao.deleteRolesByUserId(user.getId());
		sysUserRoleDao.insertObject(user.getId(), roleIds);
	}
}
