package com.hdax.dao;

import com.hdax.entity.UserRole;

import java.util.List;

/**
 * Created by Joe on 2021-07-27 0027
 */
public interface RoleDao {
    public List<UserRole> getAll();
}
