package com.example.andersen.strategy;

public class AngryCatSound implements SoundBehaviour {
    @Override
    public void sound() {
        System.out.println("Meow!!!");
    }
}
