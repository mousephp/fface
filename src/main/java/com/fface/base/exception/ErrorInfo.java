package com.fface.base.exception;

import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;

public class ErrorInfo {
    private final String url;
    private final String message;
    private LocalDateTime dateTime;

    public ErrorInfo(String url, String message, LocalDateTime dateTime) {
        this.url = url;
        this.message = message;
        this.dateTime = dateTime;
    }

    public ErrorInfo(HttpServletRequest request, Exception ex, LocalDateTime dateTime) {
        this.url = request.getRequestURL().toString();
        this.message = ex.getMessage();
        this.dateTime = dateTime;
    }

    public ErrorInfo(HttpServletRequest request, String message, LocalDateTime dateTime) {
        this.url = request.getRequestURL().toString();
        this.message = message;
        this.dateTime = dateTime;
    }

    public String getUrl() {
        return url;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}