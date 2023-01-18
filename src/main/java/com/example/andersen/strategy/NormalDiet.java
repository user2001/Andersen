package com.example.andersen.strategy;

public class NormalDiet implements EatBehavior{

    @Override
    public void eat() {
        System.out.println("Typically cat food");
    }
}