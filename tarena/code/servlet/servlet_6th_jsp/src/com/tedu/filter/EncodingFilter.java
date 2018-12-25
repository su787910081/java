package com.tedu.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 全站乱码处理过滤器
 * 
 * @author suyh
 *
 */
public class EncodingFilter implements Filter {

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 处理乱码问题
        // >> 请求参数乱码(POST)
        request.setCharacterEncoding("UTF-8");

        // >> 响应正文乱码
        response.setContentType("text/html;charset=UTF-8");

        HttpServletRequest myRequest = new MyHttpServletRequest((HttpServletRequest)request);

        // 放行过滤器
        chain.doFilter(myRequest, response);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

}

/**
 * 装饰者模式
 * 1. 写一个装饰类，要求和被装饰者所属的类实现同一个接口或者是继承自同一个 父类；
 *  ** 写一个类，继承一个装饰者(对被装饰都做了包装)
 * 2. 提供 构造函数，接收被装饰者并保存在类的内部
 * 3. 对于想要改造的方法直接进行改造，对于不想改造的方法，直接调用，原有对象上的方法。
 */

/**
 * 装饰类: MyHttpServletRequest
 * 被装饰者: request 对象
 * @author suyh
 *
 */
class MyHttpServletRequest extends HttpServletRequestWrapper
{
    HttpServletRequest request;
    
    public MyHttpServletRequest(HttpServletRequest request) {
        /*
         * 调用父类的构造方法，将request 对象传给父类
         * 让父类进行包装，然后在子类中直接 继承父类包装后的方法即可
         */
        super(request);
        this.request = request;
    }
    
    /**
     * 改造getParameter 方法，处理请求参数乱码(GET)
     */
    @Override
    public String getParameter(String name) {
        if (!"GET".equalsIgnoreCase(request.getMethod())) {
            return request.getParameter(name);
        }
        
//        String strValue = request.getParameter(name);
//        try {
//            strValue = new String(strValue.getBytes("ISO8859-1"), "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//            return null;
//        }
        
        // 我这里没有乱码，浏览器和服务器都是UTF-8 
        return request.getParameter(name);
    }
}





















