package com.example.pizzabuilder.enums;

public enum PermissionsEnum {

    ANYBODY("anybody"),

    PIZZA_PATTERN_READ("pizza_pattern:read"),
    PIZZA_PATTERN_CREATE("pizza_pattern:create"),
    PIZZA_PATTERN_UPDATE("pizza_pattern:update"),
    PIZZA_PATTERN_DELETE("pizza_pattern:delete"),

    INGREDIENT_READ("ingredient:read"),
    INGREDIENT_CREATE("ingredient:create"),
    INGREDIENT_UPDATE("ingredient:update"),
    INGREDIENT_DELETE("ingredient:delete"),

    ORDER_READ("order:read"),
    ORDER_CREATE("order:create"),
    ORDER_UPDATE("order:update"),
    ORDER_DELETE("order:delete"),

    PIZZA_IN_ORDER_READ("pizza_in_order:read"),
    PIZZA_IN_ORDER_CREATE("pizza_in_order:create"),
    PIZZA_IN_ORDER_UPDATE("pizza_in_order:update"),
    PIZZA_IN_ORDER_DELETE("pizza_in_order:delete"),

    INGREDIENT_IN_PIZZA_READ("ingredient_in_pizza:read"),
    INGREDIENT_IN_PIZZA_CREATE("ingredient_in_pizza:create"),
    INGREDIENT_IN_PIZZA_UPDATE("ingredient_in_pizza:update"),
    INGREDIENT_IN_PIZZA_DELETE("ingredient_in_pizza:delete"),

    USER_READ("user:read"),
    USER_CREATE("user:create"),
    USER_UPDATE("user:update"),
    USER_DELETE("user:delete");

    private final String permission;

    PermissionsEnum(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
