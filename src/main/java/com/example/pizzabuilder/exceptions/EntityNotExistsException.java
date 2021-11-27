package com.example.pizzabuilder.exceptions;

import javax.servlet.http.HttpServletResponse;

public class EntityNotExistsException extends BaseException{
    public <T, K> EntityNotExistsException(T type, K by){
        super("Entity of " + type.getClass().toString() +
                " found by "+ by.getClass().toString()+ " "+ by + " does not exist!");
    }

    @Override
    public int getCode() {
        return HttpServletResponse.SC_BAD_REQUEST;
    }
}
