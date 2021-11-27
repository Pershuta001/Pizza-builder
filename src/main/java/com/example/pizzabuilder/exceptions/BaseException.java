package com.example.pizzabuilder.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.MessageSource;

import java.util.List;
import java.util.Locale;
@Getter
@Setter
public abstract class BaseException extends Exception {

    private int code;
    private String messageCode;

    public BaseException(){
        this("errors.BaseException");
    }

    public BaseException(String message){
        super(message);
        this.messageCode = message;
    }
}