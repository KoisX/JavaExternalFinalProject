package com.javacourse.user.role;

/**
 * Interface for implementing Role object creation using Factory pattern
 */
public interface RoleFactory {
    Role createRole(String roleType, long id);
    Role createRole(String roleType);
}
