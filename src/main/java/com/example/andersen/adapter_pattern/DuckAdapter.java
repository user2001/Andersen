package com.example.andersen.adapter_pattern;

public class DuckAdapter implements ToyDuck {
    Duck duck;

    public DuckAdapter(Duck duck) {
        this.duck = duck;
    }

    @Override
    public void squeak() {
        duck.makeSound();
    }
}
