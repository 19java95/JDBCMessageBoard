package com.imooc.jdbc.servlet;


import com.imooc.jdbc.bean.User;
import com.imooc.jdbc.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * Created by TangTian on 2018/11/12.
 */
@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private UserService userService;
    @Override
    public void init() throws ServletException {
        super.init();
        userService = new UserService();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String passName = request.getServletPath();
        if (Objects.equals("/denglu.do",passName)){
            boolean result = userService.addUser(new User(username,password));
            if (result){
                request.getRequestDispatcher("/login.do").forward(request,response);
            }else {
                request.getRequestDispatcher("/WEB-INF/views/biz/register.jsp").forward(request,response);
            }
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }


}
