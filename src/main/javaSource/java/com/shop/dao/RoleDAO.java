package com.shop.dao;

import com.shop.entities.Role;

import java.util.List;

/**
 * Created by ostap on 3/1/17.
 */
public interface RoleDAO {
    Role findRoleByName(String name);

    void saveRole(Role role);

    List<Role> findRolesByName(String name);
}
