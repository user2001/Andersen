package com.example.andersen.Task5;

import java.util.Scanner;

public class AppMain {
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
        //some logic to show the bucket
        System.out.println("Exit  - put 0");

        Scanner scanner = new Scanner(System.in);
        int choose = scanner.nextInt();

        switch (choose) {
            case 1 -> System.out.println("Some logic of Show product list");
            case 2 -> System.out.println("some logic, Add chosen product to the bucket");
            case 3 -> System.out.println("some logic, Delete(particular) product from the bucket");
            case 4 -> System.out.println("some logic, Clear the bucket");
            case 0 -> System.out.println("Exit");
            default -> System.exit(0);
        }
    }
}
