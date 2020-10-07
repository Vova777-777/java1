package ru.progwards.java1.lessons.sets;

public class Product {
    private String code;
    public Product(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Product{" +
                "code='" + code + '\'' +
                '}';
    }
}
