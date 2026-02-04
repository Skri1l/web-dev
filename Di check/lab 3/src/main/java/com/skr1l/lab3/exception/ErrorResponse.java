package com.skr1l.lab3.exception;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorResponse {

    private String timestamp =  LocalDateTime.now().toString();
    private int status;
    private String error;
    private List<String> details;

    public ErrorResponse(int status, String error, List<String> details) {
        this.timestamp = LocalDateTime.now().toString();
        this.status = status;
        this.error = error;
        this.details = details;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public List<String> getDetails() {
        return details;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
