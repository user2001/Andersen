package com.example.andersen.Task5.dao;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Food extends Product{
    private LocalDate expirationDate;

}
