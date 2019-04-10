package com.project.sys.vo;

/**
 * vo(Value Object)：值对象，用于封装内存中数据的一个对象
 * 一般与数据库表没有对应关系
 * @author suyh
 *
 */
public class Account {
	private String uname;
	private String pwd;
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	@Override
	public String toString() {
		return "Account [uname=" + uname + ", pwd=" + pwd + "]";
	}
	
}
