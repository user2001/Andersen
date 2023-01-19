package com.example.andersen.adapter_pattern;

public class AdapterMain {
    public static void main(String[] args) {
        OrdinaryDuck ordinaryDuck = new OrdinaryDuck();
        ToyDuck toyDuck = new PlasticDuck();
        ToyDuck duckAdapter = new DuckAdapter(ordinaryDuck);
        System.out.println("Ordinary duck: ");
        ordinaryDuck.swim();
        ordinaryDuck.makeSound();

        System.out.println("ToyDuck:");
        toyDuck.squeak();

        System.out.println("Duck adapter:");
        duckAdapter.squeak();
    }
}
