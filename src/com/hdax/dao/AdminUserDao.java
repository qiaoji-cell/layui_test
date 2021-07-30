package com.hdax.dao;

import com.hdax.entity.Adminuser;

import java.util.List;

/**
 * Created by Joe on 2021-07-27 0027
 */
public interface AdminUserDao {
    public List<Adminuser> getAll(int roleId,String username,int currPage,int pageIndex);

    public int addUser(Adminuser user);

    public int updateUser(Adminuser user);

    public int delete(String userId);

    public int getCount(String username);
}
