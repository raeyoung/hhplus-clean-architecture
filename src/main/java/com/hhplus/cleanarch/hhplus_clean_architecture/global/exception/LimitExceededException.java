package com.hhplus.cleanarch.hhplus_clean_architecture.global.exception;

public class LimitExceededException extends RuntimeException {
    public LimitExceededException(String message) {
        super(message);
    }
}
