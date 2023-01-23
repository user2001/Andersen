package com.example.andersen.proxy_pattern;

public class ProxyMain {
    public static void main(String[] args) {
        Phone phone=new PhoneProxy();
        phone.call();
    }
}
