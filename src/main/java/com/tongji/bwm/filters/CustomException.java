package com.tongji.bwm.filters;

public class CustomException extends RuntimeException {

    protected String customMessage;
    protected String customError;

    public CustomException() {
    }

    public CustomException(String customMessage, String customError) {
        this.customMessage = customMessage;
        this.customError = customError;
    }

    public String getCustomMessage() {
        return customMessage;
    }

    public String getCustomError() {
        return customError;
    }
}
