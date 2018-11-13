package com.imooc.jdbc.servlet;

import com.imooc.jdbc.bean.PersonalMessage;
import com.imooc.jdbc.service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Created by TangTian on 2018/11/12.
 */
@WebServlet(name = "PersonalMessageServlet")
public class PersonalMessageServlet extends HttpServlet {
    private MessageService messageService;

    @Override

    public void init() throws ServletException {
        messageService = new MessageService();
        super.init();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathName = request.getServletPath();
        if (Objects.equals("/my/message/list.do", pathName)) {
            String pusername = (String) request.getSession().getAttribute("pusername");
            List<PersonalMessage> pMessages = messageService.getPessages(pusername);
            request.getSession().setAttribute("pmessages", pMessages);
            request.getRequestDispatcher("/WEB-INF/views/biz/personal_message_list.jsp").forward(request, response);
        } else if (Objects.equals("/message/list.do", pathName)) {
            response.sendRedirect("/WEB-INF/views/biz/message_list.jsp");
        }
    }
}
