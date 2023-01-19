package com.example.andersen.strategy;

public abstract class Cat {
    public abstract void display();

    EatBehavior eatBehavior;
    SoundBehaviour soundBehaviour;

    public Cat() {
    }

    public void doEat() {
        eatBehavior.eat();
    }

    public void doSound() {
        soundBehaviour.sound();
    }
}
