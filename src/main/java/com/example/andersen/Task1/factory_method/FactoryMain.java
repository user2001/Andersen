package com.example.andersen.Task1.factory_method;

public class FactoryMain {
    public static void main(String[] args) {
        MessageFactory messageFactory=new MessageFactory();
        Messenger messenger=messageFactory.createMessage("Instagram");
        messenger.sendMessage();
    }
}
