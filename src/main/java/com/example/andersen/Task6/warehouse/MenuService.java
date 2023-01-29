package com.example.andersen.Task6.warehouse;


import com.example.andersen.Task5.service.BucketService;
import com.example.andersen.Task6.bucket.Bucket;
import com.example.andersen.Task6.bucket.BucketImpl;
import com.example.andersen.Task6.dao.Product;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;


@NoArgsConstructor
public class MenuService {
    private final Warehouse warehouse=new Warehouse();
    Scanner scanner = new Scanner(System.in);
    Bucket bucket = new BucketImpl();

    public void showListOfProducts() {
        System.out.println("LIST OF PRODUCTS:");
        Set<Product> productList = warehouse.getAllProducts().keySet();
        for (Product product : productList) {
            System.out.println(product);
        }
    }

    public void addProductToTheBucket() {
        System.out.print("Put id of chosen product: ");
        int chosen_productId = scanner.nextInt();
        System.out.println();
        System.out.print("Quantity of product you want order: ");
        int quantityOfProduct = scanner.nextInt();
        bucket.addProduct(chosen_productId, quantityOfProduct);
    }


    public void deleteProductFromBucket() {
        System.out.print("Put id fo product you want to delete: ");
        int delete_ProductId = scanner.nextInt();
        System.out.print("Quantity of product you want to delete: ");
        int quantityOfProduct = scanner.nextInt();
        bucket.deleteProduct(delete_ProductId, quantityOfProduct);
    }

    public void clearTheBucket() {
        bucket.clearBucket();
        System.out.println("Bucket is cleared");
    }

    public void buyProducts(){
        System.out.print("Total cost of your bucket is: "+bucket.total_cost()+ " UAH\n");
    }
    public  void saveToTheFile(){
        bucket.saveToFile("bucket");
    }
    public  void loadFile(){
        bucket.loadFile("bucket");
    }
}
