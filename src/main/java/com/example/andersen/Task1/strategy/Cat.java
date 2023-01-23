package com.example.andersen.Task1.strategy;

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
