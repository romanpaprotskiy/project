package com.unfu.project.entity.constants;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Role {
    GUEST, STUDENT, TEACHER, ADMIN;

    private static final Map<String, Role> CACHE = Stream.of(values())
            .collect(Collectors.toMap(Role::toString, e -> e));

    public static Role fromString(String authority) {
        return CACHE.get(authority);
    }
}
