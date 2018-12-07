package com.javacourse.role;

import com.sun.istack.internal.NotNull;

/**
 * Implementation of RoleFactory interface
 * for a two-user role scenario: ADMIN and USER
 * in conditions where anyone, who is not admin,
 * is granter user rights
 */
public class AdminUserRoleFactory implements RoleFactory {

    private final static String ADMIN = "admin";

    @Override
    public Role createRole(@NotNull String roleType, long id) {
        Role role;

        if(roleType.equalsIgnoreCase(ADMIN)){
            role = Role.ADMIN;
        }else
            role = Role.USER;

        role.setId(id);
        return role;
    }
}