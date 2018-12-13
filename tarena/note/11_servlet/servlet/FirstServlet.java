
package com.tedu.servlet;
import java.util.*;
import java.io.*;
import javax.servlet.*;

public class FirstServlet extends GenericServlet
{
    public void service(ServletRequest req, ServletResponse res)
        throws ServletException, java.io.IOException {
        /* send time string */
        String dateStr = new Date().toLocaleString();
        res.getWriter().write("time: " + dateStr);
    }
}
