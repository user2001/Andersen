package com.example.andersen.Task1.strategy;

public class AngryCatSound implements SoundBehaviour {
    @Override
    public void sound() {
        System.out.println("Meow!!!");
    }
}
