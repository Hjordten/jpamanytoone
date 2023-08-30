package com.example.jpamanytoone.error;

public class CustomErrorResponse {
    private String errorMessage;

    public CustomErrorResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
