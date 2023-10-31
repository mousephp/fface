package com.fface.base.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Noti Not Found")
public class NotificationNotFoundException extends Exception{
    public NotificationNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
