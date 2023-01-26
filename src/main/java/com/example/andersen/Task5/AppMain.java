package com.example.andersen.Task5;

import com.example.andersen.Task5.service.MenuService;
import com.example.andersen.Task5.service.MenuServiceImpl;
import lombok.AllArgsConstructor;

import java.util.Scanner;

@AllArgsConstructor
public class AppMain {
    private static final MenuService menuService = new MenuServiceImpl();

    public static void main(String[] args) {
        while (true) {
            showMenu();
        }
    }

    public static void showMenu() {

        System.out.println("MENU:");
        System.out.println("Show product list - put 1");
        System.out.println("Add chosen product to the bucket - put 2");
        System.out.println("Delete(particular) product from the bucket - put 3");
        System.out.println("Clear the bucket - put 4");
        System.out.println("Exit  - put 0");

        Scanner scanner = new Scanner(System.in);
        int choose = scanner.nextInt();

        switch (choose) {
            case 1 -> menuService.showListOfProducts();
            case 2 -> menuService.addProductToTheBucket();
            case 3 -> System.out.println("some logic, Delete(particular) product from the bucket");
            case 4 -> System.out.println("some logic, Clear the bucket");
            case 0 -> System.exit(0);
            default -> {
                System.out.println("You put wrong number, please try again");
                showMenu();
            }
        }
    }
}
