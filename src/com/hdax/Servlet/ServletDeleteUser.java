package com.hdax.Servlet;

import com.alibaba.fastjson.JSONArray;
import com.hdax.services.UserServiceDao;
import com.hdax.services.impl.UserServiceDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Joe on 2021-07-27 0027
 */
@WebServlet(name = "ServletDeleteUser", urlPatterns = "/ServletDeleteUser")
public class ServletDeleteUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String uidStr = request.getParameter("id");
        JSONArray array = JSONArray.parseArray(uidStr);
        UserServiceDao userServiceDao = new UserServiceDaoImpl();
        String str = "";
        for(int i = 0;i < array.size();i++){
            str+=array.get(i);
            if(i<array.size()-1){
                str+=',';
            }
        }
        boolean res = userServiceDao.deleteUser(str);
        out.print(res);
        out.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
