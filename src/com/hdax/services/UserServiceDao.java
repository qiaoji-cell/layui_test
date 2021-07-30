package com.hdax.services;

import com.hdax.entity.Adminuser;
import com.hdax.utils.PageBean;

import java.util.List;

/**
 * Created by Joe on 2021-07-27 0027
 */
public interface UserServiceDao {
    public PageBean queryAllUser(int roleId, String userName, int currPage, int pageSize);

    public boolean deleteUser(String uid);

    public boolean addUser(Adminuser user);

    public boolean updateUser(Adminuser user);
}
