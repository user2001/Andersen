package com.example.andersen.strategy;

public class RelaxCatSound implements SoundBehaviour{
    @Override
    public void sound() {
        System.out.println("Purr");
    }
}
