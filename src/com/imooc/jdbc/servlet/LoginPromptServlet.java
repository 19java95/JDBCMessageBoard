package com.imooc.jdbc.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 跳转到登录页面的SERVLET
 *
 * Created by TangTian on 2018/11/11.
 */
@WebServlet(name = "LoginPromptServlet")
public class LoginPromptServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/views/biz/login.jsp").forward(request, response);
    }
}
