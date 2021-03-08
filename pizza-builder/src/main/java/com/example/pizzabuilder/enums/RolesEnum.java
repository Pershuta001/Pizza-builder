package com.example.pizzabuilder.enums;

import com.google.common.collect.Sets;
import lombok.Getter;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.pizzabuilder.enums.PermissionsEnum.*;

@Getter
public enum RolesEnum {

    GUEST(Sets.newHashSet(
            ANYBODY,
            PIZZA_PATTERN_READ,
            INGREDIENT_IN_PIZZA_READ,
            INGREDIENT_READ)),
    USER(Sets.newHashSet(ANYBODY,
            PIZZA_PATTERN_READ, PIZZA_PATTERN_CREATE, PIZZA_PATTERN_UPDATE, PIZZA_PATTERN_DELETE,
            INGREDIENT_IN_PIZZA_READ, INGREDIENT_IN_PIZZA_UPDATE, INGREDIENT_IN_PIZZA_CREATE, INGREDIENT_IN_PIZZA_DELETE,
            ORDER_READ, ORDER_CREATE, ORDER_DELETE,ORDER_UPDATE,
            PIZZA_IN_ORDER_READ, PIZZA_IN_ORDER_CREATE, PIZZA_IN_ORDER_UPDATE, PIZZA_IN_ORDER_DELETE,
            INGREDIENT_READ)),
    ADMIN(Sets.newHashSet(ANYBODY,
            PIZZA_PATTERN_READ, PIZZA_PATTERN_CREATE, PIZZA_PATTERN_UPDATE, PIZZA_PATTERN_DELETE,
            INGREDIENT_IN_PIZZA_READ, INGREDIENT_IN_PIZZA_UPDATE, INGREDIENT_IN_PIZZA_CREATE, INGREDIENT_IN_PIZZA_DELETE,
            ORDER_READ, ORDER_CREATE, ORDER_DELETE,ORDER_UPDATE,
            PIZZA_IN_ORDER_READ, PIZZA_IN_ORDER_CREATE, PIZZA_IN_ORDER_UPDATE, PIZZA_IN_ORDER_DELETE,
            INGREDIENT_READ, INGREDIENT_CREATE, INGREDIENT_UPDATE, INGREDIENT_DELETE,
            USER_READ, USER_UPDATE
            )),
    SUPER_ADMIN(Sets.newHashSet(ANYBODY,
            PIZZA_PATTERN_READ, PIZZA_PATTERN_CREATE, PIZZA_PATTERN_UPDATE, PIZZA_PATTERN_DELETE,
            INGREDIENT_IN_PIZZA_READ, INGREDIENT_IN_PIZZA_UPDATE, INGREDIENT_IN_PIZZA_CREATE, INGREDIENT_IN_PIZZA_DELETE,
            ORDER_READ, ORDER_CREATE, ORDER_DELETE,ORDER_UPDATE,
            PIZZA_IN_ORDER_READ, PIZZA_IN_ORDER_CREATE, PIZZA_IN_ORDER_UPDATE, PIZZA_IN_ORDER_DELETE,
            INGREDIENT_READ, INGREDIENT_CREATE, INGREDIENT_UPDATE, INGREDIENT_DELETE,
            USER_READ, USER_UPDATE, USER_DELETE, USER_CREATE
    ));

    private final Set<PermissionsEnum> permissions;

    RolesEnum(Set<PermissionsEnum> permissions) {
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return permissions;
    }

}
