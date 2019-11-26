package com.iversonx.demo.initializer;


import com.iversonx.demo.Application;
import com.iversonx.demo.servlet.DemoServlet;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.util.Set;

/**
 * @author Lijie
 * @version 1.0
 * @date 2019/11/26 17:07
 */
public class WebServletContainerInitializer implements ServletContainerInitializer {
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        System.out.println("Startup....");
        // ctx.addServlet("DemoServlet", DemoServlet.class).addMapping("/");
    }
}
