package com.tedu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FilterDemo2 implements Filter {

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("FilterDemo2.doFilter()");
        
        // 1. 获取暗号
        request.setCharacterEncoding("utf-8");  // 设置解析时使用的编码
        String strCode2 = request.getParameter("code2");
        if (!strCode2.equals("宝塔镇河妖")) {
            // 跳转到错误页面
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }
        
        // 过滤器放行，继续访问后面的资源
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

}
