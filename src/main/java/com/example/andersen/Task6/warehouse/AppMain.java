package com.example.andersen.Task6.warehouse;

import com.example.andersen.Task5.exception.PutWrongNumberException;

import java.util.Scanner;

public class AppMain {
    private static final MenuService menuService = new MenuService();

    public static void main(String[] args) {
        boolean menuNeeded = true;
        while (menuNeeded) {
            showMenu();
        }
    }

    public static void showMenu() {

        System.out.println("MENU:");
        System.out.println("Show product list - put 1");
        System.out.println("Add chosen product to the bucket - put 2");
        System.out.println("Delete(particular) product from the bucket - put 3");
        System.out.println("Clear the bucket - put 4");
        System.out.println("Get total price - put 5");
        System.out.println("Exit  - put 0");

        Scanner scanner = new Scanner(System.in);
        int choose = scanner.nextInt();
        try {
            switch (choose) {
                case 1 -> menuService.showListOfProducts();
                case 2 ->
                        menuService.addProductToTheBucket();
                case 3 -> menuService.deleteProductFromBucket();
                case 4 -> menuService.clearTheBucket();
                case 5->menuService.buyProducts();
                case 0 -> {menuService.saveToTheFile();
                    System.exit(0);
                }
                default -> throw new PutWrongNumberException();
            }
        } catch (PutWrongNumberException e) {
            System.out.println(e.getMessage());
            showMenu();
        }
    }
}
