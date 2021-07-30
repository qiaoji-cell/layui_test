package com.hdax.dao.impl;

import com.hdax.dao.AdminUserDao;
import com.hdax.entity.Adminuser;
import com.hdax.entity.UserRole;
import com.hdax.utils.BaseDao;
import com.hdax.utils.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joe on 2021-07-27 0027
 */
public class AdminUserDaoImpl extends BaseDao implements AdminUserDao {
    @Override
    public List<Adminuser> getAll(int roleId,String username,int pageIndex,int pageSize){
        String sql = "select * from adminuser u left join user_role r on u.roleid=r.roleid where 1=1";
        if(roleId>0){
            sql += " and r.roleId ="+roleId;
        }else if(!Util.isNull(username)){
            sql +=" and u.a_uname like'%"+username+"%'";
        }
        sql+=" limit "+pageIndex+","+pageSize;
        List<Adminuser> adminusers = new ArrayList<>();
            ResultSet rs = this.query(sql);
        try {
            while(rs.next()){
                Adminuser user = new Adminuser();
                user.setaUid(rs.getInt("a_uid"));
                user.setaSex(rs.getInt("a_sex"));
                user.setaUname(rs.getString("a_uname"));
                user.setaUpwd(rs.getString("a_upwd"));
                UserRole role = new UserRole();
                role.setRoleId(rs.getInt("r.roleid"));
                role.setRoleName(rs.getString("r.roleName"));
                user.setRole(role);
                adminusers.add(user);
            }
            closeAll(conn,pstmt,rs);
            return adminusers;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public int addUser(Adminuser user) {
        String sql = "insert into adminuser(a_uname,a_upwd,roleId,a_sex) values(?,?,?,?)";
        return this.update(sql,user.getaUname(),user.getaUpwd(),user.getRoleId(),user.getaSex());
    }

    @Override
    public int updateUser(Adminuser user) {
        String sql = "update adminuser set a_uname=?,a_upwd=?,a_sex=?,roleId=? where a_uid = ?";
        return this.update(sql,user.getaUname(),user.getaUpwd(),user.getaSex(),user.getRoleId(),user.getaUid());
    }

    @Override
    public int delete(String userId) {
        String sql = "delete from adminuser where a_uid in ("+userId+")";
        return this.update(sql);
    }

    @Override
    public int getCount(String username) {
        String sql = "select count(1) from adminuser where 1=1 ";
        if(!Util.isNull(username)){
            sql +=" and a_uname like'%"+username+"%'";
        }
        int row = 0;
        ResultSet rs = this.query(sql);
        try {
            while(rs.next()){
                row = rs.getInt(1);
            }
            closeAll(conn,pstmt,rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return row;
    }
}
