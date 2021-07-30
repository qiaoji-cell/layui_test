package com.hdax.services.impl;

import com.hdax.dao.AdminUserDao;
import com.hdax.dao.impl.AdminUserDaoImpl;
import com.hdax.entity.Adminuser;
import com.hdax.services.UserServiceDao;
import com.hdax.utils.PageBean;

import java.util.List;

/**
 * Created by Joe on 2021-07-27 0027
 */
public class UserServiceDaoImpl implements UserServiceDao {
    AdminUserDao userDao = new AdminUserDaoImpl();

    @Override
    public PageBean queryAllUser(int roleId,String roleName,int currPage,int pageSize) {
        int totalCount = userDao.getCount(roleName);
        PageBean<Adminuser> userPage = new PageBean<>(currPage,pageSize,totalCount);
        List<Adminuser> users = userDao.getAll(roleId,roleName,userPage.getStartIndex(),userPage.getPageSize());
        userPage.setDataList(users);
        return userPage;
    }

    @Override
    public boolean deleteUser(String uid) {
        return userDao.delete(uid)>0?true:false;
    }

    @Override
    public boolean addUser(Adminuser user) {
        return userDao.addUser(user)>0?true:false;
    }

    @Override
    public boolean updateUser(Adminuser user) {
        return userDao.updateUser(user)>0?true:false;
    }


}
