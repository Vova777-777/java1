package ru.progwards.java1.lessons.files;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    public String shopId;  // идентификатор магазина
    public String orderId;  // идентификатор заказа
    public String customerId; // идентификатор покупателя
    public LocalDateTime datetime; //дата-время заказа (из атрибутов файла - дата последнего изменения)
    public List<OrderItem> items;  //список позиций в заказе, отсортированный по наименованию товара
    public double sum  = 0;  // - сумма стоимости всех позиций в заказе

    @Override
    public String toString() {
        return "Order{" +
                "shopId='" + shopId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", datetime=" + datetime +
                ", items=" + items +
                ", sum=" + sum +
                '}';
    }
}


