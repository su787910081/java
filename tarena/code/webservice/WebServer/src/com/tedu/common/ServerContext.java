package com.tedu.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 用来存储和服务器相关的一些 配置信息
 */
public class ServerContext {
	// 表示使用的协议及版本
	public static String protocol;
	// 表示服务器监听的端口
	public static int port;
	// 表示线程池的大小
	public static int maxThread;
	// 表示服务器存放对外访问资源的目录
	public static String webRoot;
	
	/* 用来存储浏览器请求资源的后缀名和对应的
	 * 响应数据的类型
	 */
	public static Map<String, String> types;
	
	// 表示404 错误页面
	public static String notFoundPage;

	/**
	 * 静态代码块，在程序启动时加载server.xml 文件中的内容，读取其中的配置信息，对 上面的变量进行初始化
	 */
	static {
		init();
	}

	private static void init() {
		try {
			/*
			 * 解析server.xml 文件
			 */
			SAXReader reader = new SAXReader();
			// 利用解析器解析XML 文件，返回document 对象
			Document dom = reader.read("config/server.xml");
			Element rootElement = dom.getRootElement();
			Element serviceElement = rootElement.element("service");
			Element connectorElement = serviceElement.element("connector");
			protocol = connectorElement.attributeValue("protocol");
			port = Integer.parseInt(connectorElement.attributeValue("port"));
			maxThread = Integer.parseInt(connectorElement.attributeValue("maxThread"));
			// 服务器存放资源的目录
			// webRoot = serviceElement.element("webroot").getText(); //
			// 此行与下面一行是一样的效果
			webRoot = serviceElement.elementText("webroot");
			
			// 对types 进行初始化
			types = new HashMap<String, String>();
			List<Element> list = rootElement.element("type-mappings").elements();
			for (Element e : list) {
				// e ==> <type-mapping ext="ico" type="image/ico"/>
				types.put(e.attributeValue("ext"), e.attributeValue("type"));
			}
			
			// 设置404页面
			notFoundPage = rootElement.element("service").elementText("not-found-page");

			System.out.println("protocol: " + protocol + ", port: " + port + ", maxThread: " + maxThread + ", webRoot: "
					+ webRoot);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
