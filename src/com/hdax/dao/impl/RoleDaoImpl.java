package com.hdax.dao.impl;

import com.hdax.dao.RoleDao;
import com.hdax.entity.UserRole;
import com.hdax.utils.BaseDao;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joe on 2021-07-27 0027
 */
public class RoleDaoImpl extends BaseDao implements RoleDao {
    @Override
    public List<UserRole> getAll() {
        String sql = "select * from user_role";
        List<UserRole> roles = new ArrayList<>();
        ResultSet rs = this.query(sql);
        try {
            while(rs.next()){
                UserRole role = new UserRole();
                role.setRoleId(rs.getInt("roleid"));
                role.setRoleName(rs.getString("roleName"));
                roles.add(role);
            }
            closeAll(conn,pstmt,rs);
            return roles;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
