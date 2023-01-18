package com.example.andersen.adapter_pattern;

public class OrdinaryDuck implements Duck{
    @Override
    public void makeSound() {
        System.out.println("Kria-kria");
    }

    @Override
    public void swim() {
        System.out.println("Duck swimming");
    }
}
