package com.javacourse.role;

import com.javacourse.shared.Entity;

/**
 * A model class for role database table
 */
public enum Role implements Entity {

    ADMIN("admin"),
    USER("user");

    private long id;
    private String name;

    Role(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Role{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
