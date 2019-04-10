package mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.project.sys.dao.SysUserDao;
import com.project.sys.entity.SysUser;

public class TestSysUser02 {
	private SqlSessionFactory factory;
	
	@Before
	public void init() throws IOException {
		InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
		factory = new SqlSessionFactoryBuilder().build(is);
	}
	
	@Test
	public void testFindUsers() {
		// 创建SQLSession 对象
		SqlSession session = factory.openSession();
		// 通过session 获取一个dao 对象
		SysUserDao sysUser = session.getMapper(SysUserDao.class);
		
		List<Map<String, Object>> list = sysUser.findUsers();
		System.out.println(list);
		
		// 释放资源
		session.close();
	}

	@Test
	public void testFindUserById() {
		SqlSession session = factory.openSession();
		SysUserDao sysUser = session.getMapper(SysUserDao.class);
		
		Map<String, Object> m = sysUser.findUserById(2);
		System.out.println(m);

		session.close();
	}
	
	@Test
	public void testFindObjectById() {
		SqlSession session = factory.openSession();
		SysUser obj = session.selectOne(
				"com.project.sys.dao.SysUserDao.findObjectById", 2);
		System.out.println(obj);

		session.close();
	}
	
	@Test
	public void testInsertObject() {
		SqlSession session = factory.openSession();
		SysUserDao sysUserDao = session.getMapper(SysUserDao.class);
		SysUser user = new SysUser();
		user.setUsername("suyh");
		user.setPassword("111111");
		user.setPhone("111");
		int result = sysUserDao.insertObject(user);
		Assert.assertEquals(1, result);
		session.commit();
		session.close();
	}
}
