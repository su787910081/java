package com.tedu.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 用于表示HTTP 请求信息
 */
public class HttpRequest {
	private String method;	 // 请求方式
	private String uri;	// 请求资源路径
	private String protocol;	// 协议及版本
	
	/*
	 * 用来封装请求中的所有参数信息
	 * GET /Login?username=zzz&password=111 HTTP/1.1
	 */
	private Map<String, String> parameters;
	
	/*
	 * 构造函数，用于对method、uri、protocol 进行初始化
	 */
	public HttpRequest(InputStream in) {
		try {
			BufferedReader br
				= new BufferedReader(
					new InputStreamReader(in)
				);	// 包装为字符流
			/*
			 * 读取请求行信息
			 * (GET /index.html HTTP/1.1)
			 */
			String line = br.readLine();
			System.out.println(line);
			if (line != null 
					&& line.length() > 0) {
				String[] data = line.split("\\s");
				method = data[0];
				uri = data[1];
				if (uri.equals("/")) {//设置默认主页
					uri = "/index.html";
				}
				protocol = data[2];
			}
			
			/*
			 * 对parameters 进行初始化
			 * 1. 判断uri 是否为null并且判断是否包含 '?'
			 * 2. 通过'?' 进行uri 进行切割，获取所有请求参数组成的字符串
			 * 3. 通过'&' 进行切割: username=zzz&password=111
			 * 4. 通过'=' 进行切割: username zzz, password 111
			 * GET /Login?username=zzz&password=111 HTTP/1.1
			 */
			parameters = new HashMap<String, String>();
			if (uri != null && uri.contains("?")) {
				String[] params = uri.split("\\?")[1].split("\\&");
				for(String param : params) {
					parameters.put(param.split("=")[0], param.split("=")[1]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getParameter(String name) {
		// username=zhangefei
		if (parameters == null) {
			return null;
		}
		
		return parameters.get(name);
	}
	
	public String getMethod() {
		return method;
	}
	public String getUri() {
		return uri;
	}
	public String getProtocol() {
		return protocol;
	}
	
	
}
