package com.imooc.jdbc.servlet;

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
@WebServlet(name = "RegisterPromptServlet")
public class RegisterPromptServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathName = request.getServletPath();
        if (Objects.equals("/regPrompt.do",pathName)){
            request.getRequestDispatcher("/WEB-INF/views/biz/register.jsp").forward(request, response);
        }else {
            request.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(request, response);
        }
    }
}
