package com.javacourse.user.role;

/**
 * Implementation of RoleFactory interface
 * for a two-user role scenario: ADMIN and USER
 * in conditions where anyone, who is not admin,
 * is granter user rights
 */
public class AdminUserRoleFactory implements RoleFactory {

    private final static String ADMIN = "admin";

    @Override
    public Role createRole(String roleType, long id) {
        Role role;

        if(roleType==null)
            return Role.USER;

        if(roleType.equalsIgnoreCase(ADMIN)){
            role = Role.ADMIN;
        }else
            role = Role.USER;

        role.setId(id);
        return role;
    }

    /**
     * Overloaded version of the interface method.
     * It can only be used, when we do not care about
     * the id (DB primary key) of the role
     * @param roleType
     * @return
     */
    @Override
    public Role createRole(String roleType) {
        Role role;
        if(roleType==null)
            return Role.USER;
        if(roleType.equalsIgnoreCase(ADMIN)){
            role = Role.ADMIN;
        }else
            role = Role.USER;
        return role;
    }
}
