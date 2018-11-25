package com.tedu.http;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import com.tedu.common.HttpContext;

/**
 * 用于表示HTTP 响应信息
 */
public class HttpResponse {
	private String protocol;// 协议及版本 
	private int status;// 状态码
	private String contentType;// 响应数据的类型
	private int contentLength;// 响应数据的长度
	
	// 是否发送过状态行和响应头false 表示没有发送
	private boolean hasPrintHeader = false;
	
	// 用来存储常用 的状态码和描述信息
	Map<Integer, String> statusMap;
	
	// 指向客户端的输出流，用于向客户端 发送数据
	private OutputStream out;
	/** 构造函数，用于接收指向客户端的输出流
	 */
	public HttpResponse(OutputStream out) {
		this.out = out;
		// 对statusMap 进行初始化
		statusMap = new HashMap<Integer, String>();
		statusMap.put(HttpContext.STATUS_CODE_OK, HttpContext.STATUS_REASON_OK);
		statusMap.put(HttpContext.STATUS_CODE_NOT_FOUND, HttpContext.STATUS_REASON_NOT_FOUND);
		statusMap.put(HttpContext.STATUS_CODE_ERROR, HttpContext.STATUS_REASON_ERROR);
	}
	/** 用于返回指向客户端的输出流
	 * @return OutputStream
	 */
	public OutputStream getOutputStream() {
		/* 在获取指向客户端输出流之前，先将
		 *  状态行和响应头发送给浏览器.
		 */
		if (!hasPrintHeader) {// 如果还没有发送过
			PrintStream ps = 
					new PrintStream(this.out);
			// 发送状态行信息(HTTP/1.1 200 OK)
			ps.println(protocol + " " + status + " " + statusMap.get(status));
			// 发送响应头信息(Content-Type/content-Length)
			ps.println("Content-Type:" + contentType);
			ps.println("Content-Length:" + contentLength);
			// 发送一个空行
			ps.println("");
			
			hasPrintHeader = true;//表示已经发送过了
		}
		
		return out;
	}
	
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public int getContentLength() {
		return contentLength;
	}
	public void setContentLength(int contentLength) {
		this.contentLength = contentLength;
	}
}
