package com.hdax.Servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hdax.entity.Adminuser;
import com.hdax.services.UserServiceDao;
import com.hdax.services.impl.UserServiceDaoImpl;
import com.hdax.utils.PageBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Joe on 2021-07-27 0027
 */
@WebServlet(name = "ServletQueryAllUser", urlPatterns = "/ServletQueryAllUser")
public class ServletQueryAllUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String roleId = request.getParameter("roleId");
        int rId = 0;
        if(roleId!=null&&roleId.trim().length()>0){
            rId = Integer.valueOf(roleId);
        }
        String userName = request.getParameter("userName");
        String currPage = request.getParameter("page");
        String pageSize = request.getParameter("limit");
        int pageNum = 1;
        int size = 5;
        if(currPage!=null&&currPage.trim().length()>0){
            pageNum = Integer.valueOf(currPage);
        }
        if(currPage!=null&&currPage.trim().length()>0){
            size = Integer.valueOf(pageSize);
        }

        UserServiceDao userService = new UserServiceDaoImpl();
        PageBean pageBean = userService.queryAllUser(rId,userName,pageNum,size);
        String json = JSON.toJSONString(pageBean);
        out.print(json);
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
