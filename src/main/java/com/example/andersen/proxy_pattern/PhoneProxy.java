package com.example.andersen.proxy_pattern;

public class PhoneProxy implements Phone {
    private static Phone phone;

    @Override
    public void call() {
        if (phone == null) {
            phone = new PhoneImpl();
        }
        phone.call();
    }
}
