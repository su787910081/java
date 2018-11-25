package com.tedu.common;

/**
 * 用于保存和HTTP 协议相关的
 *  一些参数信息
 */
public class HttpContext {
	// 表示服务器成功的处理请求
	public static final int STATUS_CODE_OK = 200;
	public static final String STATUS_REASON_OK = "OK";
	
	// 表示客户端请求的资源不存在
	public static final int STATUS_CODE_NOT_FOUND = 404;
	public static final String STATUS_REASON_NOT_FOUND = "Not Found";
	
	// 表示服务器内部错误
	public static final int STATUS_CODE_ERROR = 500;
	public static final String STATUS_REASON_ERROR = "Internal Server Error";
}
