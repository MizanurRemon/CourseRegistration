package org.example.Handler.Error;

public class ApiRequestException extends RuntimeException {
    public ApiRequestException(String message) {
        super(message);
    }


}
