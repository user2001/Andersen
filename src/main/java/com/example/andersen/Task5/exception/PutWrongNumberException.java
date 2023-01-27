package com.example.andersen.Task5.exception;

public class PutWrongNumberException extends RuntimeException {
    public PutWrongNumberException(String num) {
        System.out.printf("Product with id: %s doesn't exist. Try again",num);
    }

    public PutWrongNumberException() {
        System.out.println("Invalid amount of product");
    }
}
