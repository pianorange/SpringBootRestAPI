package com.practice.rest.api.util;

import org.springframework.http.HttpStatus;

public class CustomErrorType {

    String msg = null;

    public CustomErrorType(String msg){
        this.msg = msg;
    }
}
