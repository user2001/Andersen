package com.example.andersen.Task6.dao;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class NotFood extends Product {

    @Override
    public String toString() {
        return "NotFood{" +
                "id=" + getId() +
                ", name='" + getName() +
                ", price=" + getPrice() +
                ", currency=" + getCurrency().getCurrency_name() +
                ", expirationDate=" + getExpirationDate() +
                ", createAtDate=" + getCreateAtDate() +
                '}';
    }
}
