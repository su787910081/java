package cn.tedu.project.msg.entity;

// 实体对象: 实现与msg 表中数据的映射
public class Message {
	private int id;
	private String content;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
