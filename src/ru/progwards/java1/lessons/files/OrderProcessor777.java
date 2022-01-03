package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.*;




    public class OrderProcessor777 {
        String startPath;
        public OrderProcessor777(String startPath){
            this.startPath = startPath;
        }

        List <Order> listOrders = new ArrayList();
        int countNotRightFiles = 0;

    /*3.4 метод public int loadOrders(LocalDate start, LocalDate finish, String shopId) - загружает заказы за
    указанный диапазон дат, с start до finish, обе даты включительно. Если start == null, значит нет ограничения
    по дате слева, если finish == null, значит нет ограничения по дате справа, если shopId == null - грузим для
    всех магазинов При наличии хотя бы одной ошибке в формате файла, файл полностью игнорируется, т.е. Не поступает в
    обработку. Метод возвращает количество файлов с ошибками. При этом, если в классе содержалась информация, ее надо
    удалить*/

        public int loadOrders(LocalDate start, LocalDate finish, String shopId) {
            listOrders.clear();
            countNotRightFiles = 0;
            try {
                Files.walkFileTree(Paths.get(startPath), new SimpleFileVisitor<>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        if (Files.isHidden(file)) return FileVisitResult.CONTINUE;
                        if (!filterOrderByTime(start, finish, file)) return FileVisitResult.CONTINUE;
                        if (!checkRightFile(file)) return FileVisitResult.CONTINUE;
                        Order order = loadOrder(file, shopId);
                        if (order == null) return FileVisitResult.CONTINUE;

                        listOrders.add(order);
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                        return FileVisitResult.CONTINUE;
                    }
                });
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            return countNotRightFiles;
        }

        private boolean filterOrderByTime(LocalDate start, LocalDate finish, Path file) throws IOException {
            boolean result = true;
            FileTime fileTime = Files.getLastModifiedTime(file);
            LocalDate ld = LocalDate.ofInstant(fileTime.toInstant(), ZoneId.systemDefault());
            if (start == null && finish != null){
                if (!ld.isAfter(finish)) result = true;
                else result = false;
            }
            else if (finish == null && start != null){
                if (!ld.isBefore(start)) result = true;
                else result = false;
            }
            else if (start != null && finish != null){
                if (!ld.isAfter(finish) && !ld.isBefore(start)) result = true;
                else result = false;
            }
            else if (start == null && finish == null) result = true;
            else result = false;
            return result;
        }

        private Order loadOrder(Path file, String shopId) throws IOException {
            Order order = new Order();
            getParametersOfOrder(order, file);
            if (shopId == null) return order;
            else if (order.shopId.equals(shopId)) return order;
            else return null;
        }

        private boolean checkRightFile(Path path){
            String fileName = path.getFileName().toString();
            String[] array = fileName.split("-");
            if (array.length != 3) {countNotRightFiles++; return false;}
            if (array[0].length() != 3) {countNotRightFiles++; return false;}
            if (array[1].length() != 6) {countNotRightFiles++; return false;}
            String strForEq = array[2].substring(4, array[2].length());
            if (!strForEq.equals(".csv")) {countNotRightFiles++; return false;}
            else return true;
        }

        private void getParametersOfOrder(Order order, Path file) throws IOException {
            String nameOfOrder = file.getFileName().toString();
            String[] array = nameOfOrder.split("-");
            order.shopId = array[0];
            order.orderId = array[1];
            order.customerId = array[2].substring(0,4);
            FileTime fileTime = Files.getLastModifiedTime(file);
            order.datetime =  LocalDateTime.ofInstant(fileTime.toInstant(), ZoneId.systemDefault());
            getParametersOrderFromContentOfFile(order, file);

        }

        private void getParametersOrderFromContentOfFile(Order order, Path file) throws IOException {
            List<String> listStringsOfOrders = Files.readAllLines(file);
            Collections.sort(listStringsOfOrders);
            order.items = getListOrderItem(listStringsOfOrders, order);
        }

        private List<OrderItem> getListOrderItem(List <String> listStringsOfOrders, Order order){
            List<OrderItem> listOrderItem = new ArrayList<>();
            for (int i = 0; i < listStringsOfOrders.size(); i++) {
                OrderItem orderItem = new OrderItem();
                String[] array = listStringsOfOrders.get(i).split(", ");
                if (!checkRightContentOfFile(array)) {listOrderItem.clear(); return listOrderItem;}
                orderItem.googsName = array[0];
                orderItem.count = Integer.valueOf(array[1]);
                orderItem.price = Double.valueOf(array[2]);
                order.sum = order.sum + (orderItem.price * orderItem.count);
                listOrderItem.add(orderItem);
            }
            return listOrderItem;
        }

        private boolean checkRightContentOfFile(String[] array){
            if (array.length != 3) return false;
            if (!isDigit(array)) return false;
            else return true;
        }

        private boolean isDigit(String[] array){
            try {
                Integer.valueOf(array[1]);
                Double.valueOf(array[2]);
                return true;
            }catch (NumberFormatException e){
                return false;
            }
        }


    /*метод public List<Order> process(String shopId) - выдать список заказов в порядке обработки (отсортированные
    по дате-времени), для заданного магазина. Если shopId == null, то для всех*/

        public List<Order> process(String shopId){
            List<Order> result = new ArrayList<>();
            for (int i = 0; i < listOrders.size(); i++){
                if (listOrders.get(i).items.isEmpty()) continue;
                if (shopId == null && !listOrders.get(i).items.isEmpty()) result.add(listOrders.get(i));
                else
                if (shopId !=null && shopId.equals(listOrders.get(i).shopId)) result.add(listOrders.get(i));
            }
            Collections.sort(result,
                    new Comparator<Order>() {
                        @Override
                        public int compare(Order o1, Order o2) {
                            if (o1.datetime.isBefore(o2.datetime)) return -1;
                            if (o1.datetime.isAfter(o2.datetime)) return 1;
                            else return 0;
                        }
                    });
            return result;
        }

    /*3.6 метод public Map<String, Double> statisticsByShop() - выдать информацию по объему продаж по магазинам
    (отсортированную по ключам): String - shopId, double - сумма стоимости всех проданных товаров в этом магазине*/

        public Map<String, Double> statisticsByShop(){
            Map<String, Double> result = new TreeMap<>();
            try {
                for (int i = 0; i < listOrders.size(); i++) {
                    String key = listOrders.get(i).shopId;
                    Double value = listOrders.get(i).sum;
                    if (!result.containsKey(listOrders.get(i).shopId)) result.put(key, value);
                    else{ value = value + result.get(key); result.replace(key, value);}
                }
            }catch (Exception e){
                System.out.println();
            }
            return result;
        }

     /*3.7 метод public Map<String, Double> statisticsByGoods() - выдать информацию по объему продаж по товарам
    (отсортированную по ключам): String - goodsName, double - сумма стоимости всех проданных
    товаров этого наименования*/

        Map<String, Double> statisticsByGoods(){
            Map<String, Double> result = new TreeMap<>();
            try {
                for (int i = 0; i < listOrders.size(); i++) {
                    for (int j = 0; j < listOrders.get(i).items.size(); j++) {
                        String key = listOrders.get(i).items.get(j).googsName;
                        Double value = listOrders.get(i).items.get(j).price * listOrders.get(i).items.get(j).count;
                        if (!result.containsKey(key)) result.put(key, value);
                        else {value = result.get(key) + value; result.replace(key, value);}
                    }
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            return result;
        }

    /*3.8 метод public Map<LocalDate, Double> statisticsByDay() - выдать информацию по объему продаж по дням
    (отсортированную по ключам): LocalDate - конкретный день, double - сумма стоимости всех проданных товаров
    в этот день*/

        public Map<LocalDate, Double> statisticsByDay(){
            Map<LocalDate, Double> result = new TreeMap<>();
            try {
                for (int i = 0; i < listOrders.size(); i++) {
                    LocalDate ld = listOrders.get(i).datetime.toLocalDate();
                    if (result.containsKey(ld)) {
                        Double sumGoods = result.get(ld) + listOrders.get(i).sum;
                        result.replace(ld, sumGoods);
                    } else result.put(ld, listOrders.get(i).sum);
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            return result;
        }

        public static void main(String[] args) throws IOException {
            OrderProcessor777 orderProcessor = new OrderProcessor777("B:/1");

        /*    System.out.println(orderProcessor.loadOrders(LocalDate.of(2020, Month.JANUARY, 1), LocalDate.of(2020, Month.JANUARY, 10), null));
            System.out.println("Список со всеми заказами:");
            for (int i = 0; i < orderProcessor.listOrders.size(); i++) {
                System.out.println(orderProcessor.listOrders.get(i));
            }
            System.out.println();

            orderProcessor.loadOrders(null, null, null);
            System.out.println("Метод process:");
            for (int i = 0; i < orderProcessor.process("S02").size(); i++) {
                System.out.println(orderProcessor.process("S02").get(i));
            }
           */ System.out.println();

            orderProcessor.loadOrders(LocalDate.of(2020, Month.JANUARY, 1), LocalDate.of(2020, Month.JANUARY, 10), null);
            System.out.println("Метод statisticsByShop:");
            for (Map.Entry entry : orderProcessor.statisticsByShop().entrySet()) {
                System.out.println(entry);
            }
            System.out.println();

            orderProcessor.loadOrders(LocalDate.of(2020, Month.JANUARY, 11), null, null);
            System.out.println("Метод statisticsByGoods():");
            for (Map.Entry entry : orderProcessor.statisticsByGoods().entrySet()) {
                System.out.println(entry);
            }
            System.out.println();



            orderProcessor.loadOrders(null, LocalDate.of(2020, Month.JANUARY, 16), "S01");
            System.out.println("Метод statisticsByDay:");
            for (Map.Entry entry : orderProcessor.statisticsByDay().entrySet()) {
                System.out.println(entry);
            }
        }
    }
