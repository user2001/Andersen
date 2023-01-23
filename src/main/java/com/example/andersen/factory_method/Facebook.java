package com.example.andersen.factory_method;

public class Facebook implements Messenger{
    @Override
    public void sendMessage() {
        System.out.println("Send message via Facebook");
    }
}
