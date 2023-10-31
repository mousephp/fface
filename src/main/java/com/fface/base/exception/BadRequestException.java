package com.fface.base.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class BadRequestException extends RuntimeException {
    @Getter
    private int code;

    public BadRequestException(String message) {
        super(message);
        this.code = HttpStatus.BAD_REQUEST.value();
    }

    public BadRequestException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
