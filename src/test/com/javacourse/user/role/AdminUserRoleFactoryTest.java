package com.javacourse.user.role;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;

public class AdminUserRoleFactoryTest {

    RoleFactory factory;

    @Before
    public void setUp() throws Exception {
        factory = new AdminUserRoleFactory();
    }

    @Test
    public void createRole_adminRole_returnsAdminEnumerableType() {
        assertSame(factory.createRole("admin", 1), Role.ADMIN);
        assertSame(factory.createRole("ADMIN", 1), Role.ADMIN);
        assertSame(factory.createRole("AdMiN", 1), Role.ADMIN);
    }

    @Test
    public void createRole_notAdminRole_returnsUserEnumerableType() {
        assertSame(factory.createRole("user", 1), Role.USER);
        assertSame(factory.createRole("", 1), Role.USER);
        assertSame(factory.createRole("123",1), Role.USER);
        assertSame(factory.createRole(null, 1), Role.USER);
    }
}