package ru.progwards.java1.lessons.files;

public class OrderItem {
    public String googsName; // наименование товара
    public int count;  // количество
    public double price;  // цена за единицу

    @Override
    public String toString() {
        return "OrderItem{" +
                "googsName='" + googsName + '\'' +
                ", count=" + count +
                ", price=" + price +
                '}';
    }
}
