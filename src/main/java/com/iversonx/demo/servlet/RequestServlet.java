package com.iversonx.demo.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * @author Lijie
 * @version 1.0
 * @date 2019/11/28 10:41
 */
@WebServlet(name = "RequestServlet",
        urlPatterns = "/request", loadOnStartup = 1)
public class RequestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 以字符串形式返回请求参数的值，如果参数不存在，则返回null。

        String username = req.getParameter("username");
        Enumeration<String> names = req.getParameterNames();
        List<String> list = new ArrayList<>();
        while (names.hasMoreElements()) {
            list.add(names.nextElement());
        }

        Map<String, String[]> parameterMap = req.getParameterMap();

        String[] values = req.getParameterValues("values");


        ServletOutputStream output = resp.getOutputStream();
        output.println("username is " + username);
        output.println("username attribute is " + req.getAttribute("username"));
        output.println("names is " + list);
        output.println("parameterMap is " + parameterMap);
        output.print("values is ");
        output.print("[");
        for(String item : values) {
            output.print(item + ",");
        }
        output.print("]");
        output.flush();
    }
}
