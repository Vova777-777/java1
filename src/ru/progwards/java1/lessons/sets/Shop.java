package ru.progwards.java1.lessons.sets;


import java.util.List;

public class Shop {
    private List<Product> products;
    public Shop(List<Product> products){
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }
}
