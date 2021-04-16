package com.example.pizzabuilder.exceptions;


import javax.servlet.http.HttpServletResponse;

public class WrongRestrictionException extends BaseException{
    public WrongRestrictionException(){
        super("Wrong restriction");
    }

    @Override
    public int getCode() {
        return HttpServletResponse.SC_BAD_REQUEST;
    }


}
