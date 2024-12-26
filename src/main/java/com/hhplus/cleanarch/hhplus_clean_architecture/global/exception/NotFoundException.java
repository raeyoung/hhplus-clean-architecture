package com.hhplus.cleanarch.hhplus_clean_architecture.global.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
