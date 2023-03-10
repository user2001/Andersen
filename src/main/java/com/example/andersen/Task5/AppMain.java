package com.example.andersen.Task5;

import com.example.andersen.Task5.exception.PutWrongNumberException;
import com.example.andersen.Task5.service.MenuService;
import com.example.andersen.Task5.service.MenuServiceImpl;
import lombok.AllArgsConstructor;

import java.util.Scanner;

@AllArgsConstructor
public class AppMain {
    private static final MenuService menuService = new MenuServiceImpl();

    public static void main(String[] args) {
        boolean manuNeeded = true;
        while (manuNeeded) {
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
        try {
            switch (choose) {
                case 1 -> menuService.showListOfProducts();
                case 2 -> menuService.addProductToTheBucket();
                case 3 -> menuService.deleteProductFromBucket();
                case 4 -> menuService.clearTheBucket();
                case 0 -> System.exit(0);
                default -> throw new PutWrongNumberException();
            }
        } catch (PutWrongNumberException e) {
            System.out.println(e.getMessage());
            showMenu();
        }
    }
}
