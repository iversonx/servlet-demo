package com.iversonx.demo.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// @WebServlet(name = "DemoServlet", urlPatterns = "/servlet/demo")
public class DemoServlet extends HttpServlet {
    public DemoServlet() {
        super();
        System.out.println("DemoServlet()");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("init()");
        super.init();
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("service()");
        super.service(req, res);
    }

    @Override
    public void destroy() {
        System.out.println("destroy()");
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet()");
        ServletOutputStream outputStream = resp.getOutputStream();
        outputStream.println("Hello Servlet");
        outputStream.flush();
    }
}
