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
        assertSame(factory.createRole("admin", anyLong()), Role.ADMIN);
        assertSame(factory.createRole("ADMIN", anyLong()), Role.ADMIN);
        assertSame(factory.createRole("AdMiN", anyLong()), Role.ADMIN);
    }

    @Test
    public void createRole_notAdminRole_returnsUserEnumerableType() {
        assertSame(factory.createRole("user", anyLong()), Role.USER);
        assertSame(factory.createRole("", anyLong()), Role.USER);
        assertSame(factory.createRole("123", anyLong()), Role.USER);
        assertSame(factory.createRole(null, anyLong()), Role.USER);
    }
}