package com.hrms.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class AuthorizationFailedException extends RuntimeException{
    public AuthorizationFailedException(String message){
        super(message);
    }
}
