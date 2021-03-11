package com.example.pizzabuilder.exceptions;

import org.springframework.context.MessageSource;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;

public class WrongRestrictionException extends BaseException{
    public WrongRestrictionException(){
        super("Wrong restriction");
    }

    @Override
    public int getCode() {
        return HttpServletResponse.SC_BAD_REQUEST;
    }

    @Override
    public String formMessage(MessageSource messageSource, Locale locale) {
        return messageSource.getMessage("errors.WrongRestrictionException", null, locale);
    }

    @Override
    public List<String> formListErrors(MessageSource messageSource, Locale locale) {
        return null;
    }
}
