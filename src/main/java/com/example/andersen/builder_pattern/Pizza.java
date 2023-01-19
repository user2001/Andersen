package com.example.andersen.builder_pattern;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Pizza {
    private String ingredient;

    public static PizzaBuilder base() {
        return new PizzaBuilder();
    }

    public static class PizzaBuilder {
        Pizza pizza;

        private PizzaBuilder() {
            pizza = new Pizza();
        }

        public PizzaBuilder(Pizza pizza) {
            this.pizza = pizza;
        }

        public PizzaBuilder addIngredient(String ingredient) {
            pizza.ingredient = ingredient;
            return this;
        }

        public Pizza build() {
            return pizza;
        }
    }

}

class Oven {
    public static Pizza cook() {

        return Pizza.base()
                .addIngredient("cheese")
                .addIngredient("tomato")
                .addIngredient("meat")
                .build();
    }
}
