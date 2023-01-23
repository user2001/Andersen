package com.example.andersen.Task1.strategy;

public class ProteinDiet implements EatBehavior {
    @Override
    public void eat() {
        System.out.println("Special cat food");
    }
}
