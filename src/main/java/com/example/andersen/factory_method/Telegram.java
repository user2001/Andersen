package com.example.andersen.factory_method;

public class Telegram implements Messenger{
    @Override
    public void sendMessage() {
        System.out.println("Send message via Telegram");
    }
}
