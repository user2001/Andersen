package com.example.andersen.Task6.dao;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Food extends Product implements Serializable {

    @Override
    public String toString() {
        return "Food{" +
                "id=" + getId() +
                ", name='" + getName() +
                ", price=" + getPrice() +
                ", currency=" + getCurrency().getCurrency_name() +
                ", expirationDate=" + getExpirationDate() +
                ", createAtDate=" + getCreateAtDate() +
                '}';
    }
}
