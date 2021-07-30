package com.hdax.Servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.hdax.entity.Adminuser;
import com.hdax.services.UserServiceDao;
import com.hdax.services.impl.UserServiceDaoImpl;
import com.hdax.utils.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.BeanInfo;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Joe on 2021-07-28 0028
 */
@WebServlet(name = "ServletAddUser", urlPatterns = "/ServletAddUser")
public class ServletAddUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("userName");
        String password = request.getParameter("pwd");
        String role = request.getParameter("roleId");
        String sex = request.getParameter("sex");
        int roleId = 1;
        if(!Util.isNull(role)){
            roleId = Util.beInt(role);
        }
        Adminuser user = new Adminuser();
        user.setaUname(username);
        user.setaUpwd(password);
        user.setRoleId(roleId);
        user.setaSex(Util.beInt(sex));
        UserServiceDao dao = new UserServiceDaoImpl();
        boolean res = dao.addUser(user);
     
        out.print(res);
        out.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
