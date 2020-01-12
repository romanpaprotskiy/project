package com.unfu.project.entity.constants;

public enum Role {
    GUEST, STUDENT, TEACHER, ADMIN;

    public static Role fromString(String authority) {
        Role[] values = values();
        for (Role role : values) {
            if (role.name().equals(authority)) return role;
        }
        return null;
    }
}
