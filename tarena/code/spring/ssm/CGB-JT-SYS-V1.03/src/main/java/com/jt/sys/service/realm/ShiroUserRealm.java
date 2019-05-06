package com.jt.sys.service.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jt.sys.dao.SysUserDao;
import com.jt.sys.pojo.SysUser;


@Component
public class ShiroUserRealm extends AuthorizingRealm {
	@Autowired
	private SysUserDao sysUserDao;

	// 授权(权限)检测
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		System.out.println("ShiroUserRealm.doGetAuthorizationInfo()");
		// 1. 获取用户权限(用户-->角色-->资源)
		// 1.1 获取用户信息(session)
		SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("user");
		// 1.2 根据用户信息获取资源的访问权限
		List<String> list = sysUserDao.findUserPermissions(user.getId());
		Set<String> set = new HashSet<>(list);	// 去重
		System.out.println("permissions: " + set);
		
		// 2. 封装用户权限(AuthorizationInfo)
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(set);
		
		return info;
	}

	// 认证检测(用户身份是否存在，密码是否正确)
	// 1. subject.login(token)
	// 2. securityManager
	// 3. Authentication
	// 4. Realm.doGetAuthenticationInfo
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("ShiroUserRealm.doGetAuthenticationInfo()");
		
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();
		SysUser user = sysUserDao.findObjectByUserName(username);
		
		ByteSource byteSource = ByteSource.Util.bytes(user.getSalt().getBytes());
		
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
				// 用户身份，已加密的密码，盐值对应的ByteSource, realm 的名字
				user.getUsername(), user.getPassword(), byteSource, getName());
		
		// 4. 存储用户信息(session)
		SecurityUtils.getSubject().getSession().setAttribute("user", user);
		
		return info;	// 此对象返回给认证了？ 认证器对象
	}

}
