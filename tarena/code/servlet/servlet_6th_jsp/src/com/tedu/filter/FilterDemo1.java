package com.tedu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FilterDemo1 implements Filter {

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

    /**
     * 对拦截的请求进行处理的核心方法
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("FilterDemo1.doFilter()");
        request.setCharacterEncoding("utf-8");
        String strCode = request.getParameter("code");
        if (!strCode.equals("天王盖地虎")) {
            // 跳转到error.jsp 页面提示用户暗号错误
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } else {
            // 放行过滤器，才可以执行后面的资源
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

}
