package com.example.masho.coffeeordershop;

/**
 * Created by Masho on 10/9/2018.
 */

public class ModelMyOrders {
    private String Name;
    private String price;
    private String whippedCream;
    private String ChocolateCream;


    public ModelMyOrders(String name, String price, String whippedCream, String chocolateCream) {
        Name = name;
        this.price = price;
        this.whippedCream = whippedCream;
        ChocolateCream = chocolateCream;
    }
    public ModelMyOrders() {}

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price= price;
    }

    public String getWhippedCream() {
        return whippedCream;
    }

    public void setWhippedCream(String whippedCream) {
        this.whippedCream = whippedCream;
    }

    public String getChocolateCream() {
        return ChocolateCream;
    }

    public void setChocolateCream(String chocolateCream) {
        ChocolateCream = chocolateCream;
    }

}
