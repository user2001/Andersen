package com.example.andersen.Task1.proxy_pattern;

public class PhoneImpl implements Phone {
    public PhoneImpl() {
        startCall();
    }

    @Override
    public void call() {
        System.out.println("Call started");
    }

    private void startCall() {
        System.out.println("I have to make a call");
    }
}
