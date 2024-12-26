package com.hhplus.cleanarch.hhplus_clean_architecture.global.exception;

public class AlreadyAppliedException extends RuntimeException {
    public AlreadyAppliedException(String message) {
        super(message);
    }
}