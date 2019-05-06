package com.jt.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import com.jt.common.exception.ServiceException;
import com.jt.common.vo.PageObject;
import com.jt.sys.dao.SysUserDao;
import com.jt.sys.dao.SysUserRoleDao;
import com.jt.sys.pojo.SysUser;
import com.jt.sys.service.SysUserService;

@Service
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	@Qualifier("sysUserDao")
	private SysUserDao sysUserDao;
	@Autowired
	private SysUserRoleDao sysUserRoleDao;

	@Override
	public int updateObject(SysUser entity, String roleIds) {
		// 1.参数业务验证
		if (entity == null)
			throw new ServiceException("更新对象不能为空");
		if (entity.getId() == null)
			throw new ServiceException("更新用户时id不能为空");
		if (StringUtils.isEmpty(roleIds))
			throw new ServiceException("用户角色不能为空");
		// 2.更新数据
		// 2.1 更新用户基本信息
		encryptionEntityPassword(entity);
		int rows = sysUserDao.updateObject(entity);
		// 2.2删除用户角色关系数据
		sysUserRoleDao.deleteObject(entity.getId());
		// 2.3重新建立关系
		sysUserRoleDao.insertObject(entity.getId(), roleIds.split(","));
		return rows;
	}

	@Override
	public Map<String, Object> findObjectById(Integer id) {
		// 查找用户信息
		SysUser user = sysUserDao.findObjectById(id);
		// 查询角色信息
		List<Integer> roleIds = sysUserRoleDao.findRolesByUserId(id);
		// 封装数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sysUser", user);
		map.put("roleIds", roleIds);
		return map;
	}

	@Override
	public int saveObject(SysUser entity, String roleIds) {// 2,3,4,5,
		// 1.保存用户信息
		System.out.println("start.id=" + entity.getId());
		
		encryptionEntityPassword(entity);
		int rows = sysUserDao.insertObject(entity);
		System.out.println("end.id=" + entity.getId());
		// 2.保存关系数据(用户与角色关系数据)
		sysUserRoleDao.insertObject(entity.getId(), roleIds.split(","));
		return rows;
	}

	@Override
	public int validById(Integer id, Integer valid, String modifiedUser) {
		// 1.参数有效性验证
		if (id == null || id < 0)
			throw new ServiceException("id 值无效");
		if (valid == null || valid < 0)
			throw new ServiceException("状态值无效");
		// 2.修改状态信息
		int rows = sysUserDao.validById(id, valid, modifiedUser);
		return rows;
	}

	@Override
	public PageObject<SysUser> findPageObjects(String username, Integer pageCurrent) {
		// 1.查询当前页数据
		int pageSize = 3;
		int startIndex = (pageCurrent - 1) * pageSize;
		List<SysUser> records = sysUserDao.findPageObjects(username, startIndex, pageSize);
		// 2.根据条件查询总记录数
		int rowCount = sysUserDao.getRowCount(username);
		int pageCount = rowCount / pageSize;
		if (rowCount % pageSize != 0) {
			pageCount++;
		}
		// 3.封装数据(当前页数据,分页信息)
		PageObject<SysUser> pageObject = new PageObject<>();
		pageObject.setPageCount(pageCount);
		pageObject.setRowCount(rowCount);
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setRecords(records);

		return pageObject;
	}

	@Override
	public void login(String username, String password) {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated())
			return;
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try {
			subject.login(token);
		} catch (IncorrectCredentialsException ice) {
			ice.printStackTrace();
			throw new ServiceException("密码错误");
		} catch (AuthenticationException ae) {
			ae.printStackTrace();
			throw new ServiceException("认证失败");
		}
		
		System.out.println("验证通过: " + username);
	}

	private void encryptionEntityPassword(SysUser entity) {
		String uuid = UUID.randomUUID().toString();
		String uuidPwd = uuid + entity.getPassword();
		String newPwd = DigestUtils.md5DigestAsHex(uuidPwd.getBytes());
		entity.setPassword(newPwd);
		entity.setSalt(uuid);
	}
}
