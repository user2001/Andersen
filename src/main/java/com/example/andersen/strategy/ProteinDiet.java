package com.example.andersen.strategy;

public class ProteinDiet implements EatBehavior{
    @Override
    public void eat() {
        System.out.println("Special cat food");
    }
}
