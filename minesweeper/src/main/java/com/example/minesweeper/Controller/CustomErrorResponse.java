package com.example.minesweeper.Controller;

public class CustomErrorResponse {
    private String error;

    public CustomErrorResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}