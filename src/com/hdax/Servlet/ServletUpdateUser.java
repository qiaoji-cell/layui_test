package com.hdax.Servlet;

import com.hdax.dao.AdminUserDao;
import com.hdax.entity.Adminuser;
import com.hdax.entity.UserRole;
import com.hdax.services.UserServiceDao;
import com.hdax.services.impl.UserServiceDaoImpl;
import com.hdax.utils.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Joe on 2021-07-29 0029
 */
@WebServlet(name = "ServletUpdateUser", urlPatterns = "/ServletUpdateUser")
public class ServletUpdateUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String userName = request.getParameter("userName");
        String pwd = request.getParameter("pwd");
        String sex = request.getParameter("sex");
        String role = request.getParameter("roleId");
        String uid = request.getParameter("uid");

        UserServiceDao dao = new UserServiceDaoImpl();

        Adminuser user = new Adminuser();
        user.setaUname(userName);
        user.setaUpwd(pwd);
        user.setaSex(Util.beInt(sex));
        user.setRoleId(Util.beInt(role));
        user.setaUid(Util.beInt(uid));
        boolean res = dao.updateUser(user);
        out.print(res);
        out.flush();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
