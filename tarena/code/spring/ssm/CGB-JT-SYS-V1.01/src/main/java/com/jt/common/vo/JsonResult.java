package com.jt.common.vo;

/***
 * 借助此类对控制层返回的数据进行统一封装
 * 便于在客户端 对数据进行更好处理。
 * @author suyh
 *
 */
public class JsonResult {

	private int state = 1;	// 状态码 state = 1, 业务成功. state = 0, 业务失败
	private String message = "OK"; // 与状态码对应的具体消息(例如：404 <==> 页面不存在) 
	private Object data;	// 借助此发展封装服务端返回的具体数据，例如查询的结果
	public JsonResult() {
		super();
	}
	public JsonResult(int state, String message) {
		super();
		this.state = state;
		this.message = message;
	}
	public JsonResult(int state, String message, Object data) {
		super();
		this.state = state;
		this.message = message;
		this.data = data;
	}
	public int getState() {
		return state;
	}
	public String getMessage() {
		return message;
	}
	public Object getData() {
		return data;
	}
}
