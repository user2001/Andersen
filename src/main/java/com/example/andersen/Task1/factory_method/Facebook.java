package com.example.andersen.Task1.factory_method;

public class Facebook implements Messenger{
    @Override
    public void sendMessage() {
        System.out.println("Send message via Facebook");
    }
}
