package com.hdax.utils;

import javax.xml.transform.Result;
import java.sql.*;

/**
 * Created by Joe on 2021-07-27 0027
 */
public class BaseDao {
    static String driver = "com.mysql.cj.jdbc.Driver";
    String user = "root";
    String pwd = "root";
    String url = "jdbc:mysql://localhost:3306/vivo_mall?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8";

    public Connection conn;
    public PreparedStatement pstmt;
    public ResultSet rs;
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConn(){
        try {
            if(conn==null||conn.isClosed()){
                conn = DriverManager.getConnection(url,user,pwd);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }

    public void closeAll(Connection conn,Statement stmt,ResultSet rs){
        try {
            if(rs!=null) rs.close();
            if(stmt!=null) stmt.close();
            if(conn!=null) conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public int update(String sql,Object...obj){
        int row = 0;
        try {
            pstmt = getConn().prepareStatement(sql);
            for(int i = 0;i < obj.length;i++){
                pstmt.setObject(i+1,obj[i]);
            }
            row = pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            closeAll(conn,pstmt,null);
        }
        return row;
    }

    public ResultSet query(String sql, Object...obj){
        ResultSet rs = null;
        try {
            pstmt = getConn().prepareStatement(sql);
            for(int i = 0;i < obj.length;i++){
                pstmt.setObject(i+1,obj[i]);
            }
            rs = pstmt.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rs;
    }
}
