package com.ray.controller;


import com.ray.controller.frontend.HeadLineOperationController;
import com.ray.controller.frontend.MainPageController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 参照Spring MVC，仅通过DispatcherServlet进行请求派发
 * <p>
 * 拦截所有请求
 * 解析请求
 * 派发给对应得Controller里面得方法进行处理
 *
 * @author 张烈文
 */
@WebServlet("/")
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("request Path is:" + req.getServletPath());
        System.out.println("request method is:" + req.getMethod());

        if (req.getServletPath() == "/frontend/getmainpageinfo" && req.getMethod() == "GET") {
            new MainPageController().getMainPageInfo(req, resp);
        } else if (req.getServletPath() == "superadmin/addheadline" && req.getMethod() == "POST") {
            new HeadLineOperationController().addHeadLine(req, resp);
        }
    }
}
