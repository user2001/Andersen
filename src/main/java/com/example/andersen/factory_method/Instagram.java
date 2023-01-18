package com.example.andersen.factory_method;

public class Instagram implements Messenger{
    @Override
    public void sendMessage() {
        System.out.println("Send message via Instagram");
    }
}
