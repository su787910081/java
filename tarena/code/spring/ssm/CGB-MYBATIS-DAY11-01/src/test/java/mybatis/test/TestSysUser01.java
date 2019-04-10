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

import com.project.sys.entity.SysUser;

public class TestSysUser01 {
	private SqlSessionFactory factory;
	
	@Before
	public void init() throws IOException {
		InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
//		InputStream is = getClass().getClassLoader().getResourceAsStream("mybatis-config.xml");
		factory = new SqlSessionFactoryBuilder().build(is);
	}
	
	@Test
	public void testFindUsers() {
		Assert.assertNotNull(factory);
		// 创建SQLSession 对象
		SqlSession session = factory.openSession();
		List<Map<String, Object>> list = session.selectList(
				"com.project.sys.dao.SysUserDao.findUsers");
		System.out.println(list);
		
		// 释放资源
		session.close();
	}
	
	@Test
	public void testFindUserById() {
		SqlSession session = factory.openSession();
		Map<String, Object> m = session.selectOne(
				"com.project.sys.dao.SysUserDao.findUserById", 1);
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
}
