package ru.chelogaev.dm.measurementrestapp.models;

public class ErrorResponseModel {
    private String error;

    private String timestamp;

    public ErrorResponseModel(String error, String currentDateTime) {
        this.error = error;
        this.timestamp = currentDateTime;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
