package ru.progwards.java1.lessons.sets;

import java.util.*;

public class ProductAnalytics {
    private List<Shop> shops;
    private List<Product> products;
    public ProductAnalytics(List<Product> products, List<Shop> shops) {
        this.products = products;
        this.shops = shops;
    }

    /*2.13 Создать функцию public Set<Product> existInAll() - товары из products, которые имеются во
    всех магазинах;*/

        public Set<Product> existInAll(){
        Set<Product> setAllProductsInEachShop = new HashSet<>(this.products);
        for (Shop shop : shops) {
                setAllProductsInEachShop.retainAll(shop.getProducts());
            }
        return setAllProductsInEachShop;
        }

    /*2.14 Создать функцию public Set<Product> existAtListInOne() - товары из products, которые имеются хотя
     бы в одном магазине*/

        public Set<Product> existAtListInOne(){
            Set<Product> setMaybeInOneOrSomeShops = new HashSet<>();
            for (Shop shop : shops) {
                setMaybeInOneOrSomeShops.addAll(shop.getProducts());
            }
            return setMaybeInOneOrSomeShops;
        }

    /*2.15 Создать функцию public Set<Product> notExistInShops() - товары из products, которых нет ни в одном магазине*/

        Set<Product> notExistInShops(){
            Set<Product> setnotExistInShops = new HashSet<>(this.products);
            for (Shop shop : shops) {
                setnotExistInShops.removeAll(shop.getProducts());
            }
            return setnotExistInShops;
        }

    /*2.16 Создать функцию public Set<Product> existOnlyInOne() - товары из products, которые есть только в
     одном магазине*/

        Set<Product> existOnlyInOne(){
            Set<Product> setExistOnlyInOne = new HashSet<>();
            List<Product> list = new ArrayList<>();
            for (Shop shop : shops) {
                list.addAll(shop.getProducts());
            }
            int count;
            for (Product product : products) {
                count = 0;
                for (int i = 0; i < list.size(); i++) {
                    if (product.getCode().equals(list.get(i).getCode())) count++;
                }
                if (count == 1) setExistOnlyInOne.add(product);
            }
            return setExistOnlyInOne;
        }

    public static void main(String[] args) {
        //Проверка метода existInAll
        List<Product> list1 = new ArrayList();
        List<Product> list11 = new ArrayList();
        List<Product> list111 = new ArrayList();
        Product product1 = new Product("123");
        Product product2 = new Product("124");
        Product product3 = new Product("125");
        Product product4 = new Product("126");
        Product product5 = new Product("127");
        Product product6 = new Product("128");
        Product product7 = new Product("129");
        list1.add(product1);
        list1.add(product2);
        list1.add(product3);
        list1.add(product7);
        list11.add(product3);
        list11.add(product4);
        list11.add(product5);
        list111.add(product3);
        list111.add(product5);
        list111.add(product5);
        Shop shop1 = new Shop(list1);
        Shop shop11 = new Shop(list11);
        Shop shop111 = new Shop(list111);
        List<Shop> listShop1 = new ArrayList<>();
        listShop1.add(shop1);
        listShop1.add(shop11);
        listShop1.add(shop111);
        List<Product> listProduct1 = new ArrayList<>();
        listProduct1.add(product1);
        listProduct1.add(product2);
        listProduct1.add(product3);
        listProduct1.add(product4);
        listProduct1.add(product5);
        listProduct1.add(product6);
        listProduct1.add(product7);
        ProductAnalytics productAnalytics = new ProductAnalytics(listProduct1, listShop1);
        System.out.println(productAnalytics.existInAll());
        System.out.println(productAnalytics.existAtListInOne());
        System.out.println(productAnalytics.notExistInShops());
        System.out.println(productAnalytics.existOnlyInOne());


    }


}
