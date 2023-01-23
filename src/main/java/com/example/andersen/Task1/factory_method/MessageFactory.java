package com.example.andersen.Task1.factory_method;

public class MessageFactory {
    public Messenger createMessage(String typeOfMessenger) {
        if (typeOfMessenger == null || typeOfMessenger.isEmpty()) {
            return null;
        }
        switch (typeOfMessenger){
            case "Instagram":
                return new Instagram();
            case "Telegram":
                return new Telegram();
            case "Facebook":
                return new Facebook();
            default:
                throw new IllegalArgumentException("unknown type of messenger "+typeOfMessenger);
        }

    }
}
