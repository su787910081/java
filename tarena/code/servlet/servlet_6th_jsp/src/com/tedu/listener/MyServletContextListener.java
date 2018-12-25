package com.tedu.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {

    /**
     * 该方法将会在ServletContext 对象销毁时调用
     * ServletContext 对象在服务器移出WEB 应用时销毁
     */
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        System.out.println("MyServletContextListener.contextDestroyed()   我轻轻的又走了...");
    }

    /**
     * 该方法将会在ServletContext 对象创建时调用
     * ServletContext 对象在服务器加载WEB 应用之后创建
     */
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        System.out.println("MyServletContextListener.contextInitialized()   我轻轻的来了...");
    }

}
