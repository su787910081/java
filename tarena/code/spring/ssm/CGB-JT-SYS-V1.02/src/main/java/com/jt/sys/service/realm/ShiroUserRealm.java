package com.jt.sys.service.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
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

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		System.out.println("ShiroUserRealm.doGetAuthorizationInfo()");
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("ShiroUserRealm.doGetAuthenticationInfo()");
		
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		SysUser user = sysUserDao.findObjectByUserName(upToken.getUsername());
		// 盐值
		ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());
		// 自动完成密码比对
		// 通过AuthenticatingReal 的credentialsMatcher 属性来进行的密码的比对
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
				upToken.getUsername(), user.getPassword(), credentialsSalt, getName());
		SecurityUtils.getSubject().getSession().setAttribute("currentUser", user);
		return info;
	}

}
