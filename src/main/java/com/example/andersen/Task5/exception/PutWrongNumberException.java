package com.example.andersen.Task5.exception;

public class PutWrongNumberException extends RuntimeException {
    public PutWrongNumberException(String message) {
        super(message);
    }

    public PutWrongNumberException() {
    }
}
