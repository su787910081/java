package com.project.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.project.sys.entity.SysUser;
import com.project.sys.vo.Account;

public interface SysUserDao {

	List<Map<String, Object>> findUsers();
	
	Map<String, Object> findUserById(Integer id);
	
	int insertObject(SysUser entity);
	
	List<Account> findNameAndPwd();
	
	// 假如使用'$'取参数的值时，要使用注解'@Param' 注解对参数进行声明
	// 当使用'#' 获取参数值时，假如接口方法中有多个参数时，需要使用'@Param' 注解进行声明
	List<SysUser> findUsers(@Param("columnName") String colName, @Param("phone") String ph);
	
//	Account findUser(String name);
}
