package com.example.demo.ant;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.models.Users;

public class UnusedValidator implements ConstraintValidator<Unused, String> {
    @Autowired
    UsersService usersservice;

    public void initialize(Unused constraintAnnotation) {
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {

        Users account = (Users)usersservice.finder(value); // ここのvalueは入力値になります
        if(account == null){
            return true;
        }
        return false;
    }
}
