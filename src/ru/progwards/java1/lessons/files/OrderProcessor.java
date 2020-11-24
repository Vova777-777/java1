package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class OrderProcessor {
    String startPath;
    public OrderProcessor(String startPath){
        this.startPath = startPath;
    }

    List <String> listNamesAllFiles = new ArrayList();
    List <Order> listOrders = new ArrayList();
    int countNotRightFiles = 0;

    private void getNamesAllFiles(List <String> list) throws IOException {
        Path path = Paths.get(startPath);
        Files.walkFileTree(path, new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult visitFile(Path path1, BasicFileAttributes attrs) throws IOException {
                list.add(path1.getFileName().toString());
                return FileVisitResult.CONTINUE;
            }
            @Override
            public FileVisitResult visitFileFailed(Path path, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });
    }

    private void getAllOrders() throws IOException {
        getNamesAllFiles(listNamesAllFiles);
        for (int i = 0; i < listNamesAllFiles.size(); i++) {
            Order order = new Order();
            String nameOfFile = listNamesAllFiles.get(i);
            String[] array = listNamesAllFiles.get(i).split("-");
            if (array.length != 3) continue;
            if (array[0].length() != 3) break;
            if (array[1].length() != 6) break;
            String strForEq = array[2].substring(4,8);
            if (!strForEq.equals(".csv")) break;
            array[2] = array[2].substring(0,4);

            if (array[2].length() != 4) break;
            else {
            order.shopId = array[0];
            order.orderId = array[1];
            order.customerId = array[2];}
            order = getParametersOrderFromContentOfFile(order, nameOfFile);
            listOrders.add(order);

        }
    }

    private Order getParametersOrderFromContentOfFile(Order order, String nameOfFile) throws IOException {
        Path path = Paths.get(startPath);
        Files.walkFileTree(path, new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult visitFile(Path path1, BasicFileAttributes attrs) throws IOException {
                if (path1.getFileName().toString().equals(nameOfFile)){
                FileTime fileTime = Files.getLastModifiedTime(path1);
                order.datetime =  LocalDateTime.ofInstant(fileTime.toInstant(), ZoneId.systemDefault());
                List<String> listStringsOfOrders = Files.readAllLines(path1);
                Collections.sort(listStringsOfOrders);
                order.items = getListOrderItem(listStringsOfOrders, order);}
                return FileVisitResult.CONTINUE;
            }
            @Override
            public FileVisitResult visitFileFailed(Path path, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });
        return order;
    }

    private List<OrderItem> getListOrderItem(List <String> list, Order order){
        List<OrderItem> listOrderItem = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            OrderItem orderItem = new OrderItem();
            String[] array = list.get(i).split(", ");
            orderItem.googsName = array[0];
            orderItem.count = Integer.valueOf(array[1]);
            orderItem.price = Double.valueOf(array[2]);
            order.sum = order.sum + orderItem.price;
            listOrderItem.add(orderItem);
        }
        return listOrderItem;
    }

    private boolean checkRightFile(Path path){
      String fileName = path.getFileName().toString();
        String[] array = fileName.split("-");
        if (array.length != 3) return false;
        if (array[0].length() != 3) return false;
        if (array[1].length() != 6) return false;
        String strForEq = array[2].substring(4,8);
        if (!strForEq.equals(".csv")) return false;
        else return true;
    }

    /*метод public List<Order> process(String shopId) - выдать список заказов в порядке обработки (отсортированные
    по дате-времени), для заданного магазина. Если shopId == null, то для всех*/

    public List<Order> process(String shopId){
        List<Order> result = new ArrayList<>();
        try{
        if (shopId.equals(null));
        for (int i = 0; i < listOrders.size(); i++){
            if (shopId.equals(listOrders.get(i).shopId)) result.add(listOrders.get(i));
            }
        }
          catch (NullPointerException e) {
              result.addAll(listOrders);
          }
        finally {
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
    }
    /*3.6 метод public Map<String, Double> statisticsByShop() - выдать информацию по объему продаж по магазинам
    (отсортированную по ключам): String - shopId, double - сумма стоимости всех проданных товаров в этом магазине*/

    public Map<String, Double> statisticsByShop(){
        Map<String, Double> result = new TreeMap<>();
        for (int i = 0; i < listOrders.size(); i++){
            result.put(listOrders.get(i).shopId, listOrders.get(i).sum);
        }
        return result;
    }

    /*3.7 метод public Map<String, Double> statisticsByGoods() - выдать информацию по объему продаж по товарам
    (отсортированную по ключам): String - goodsName, double - сумма стоимости всех проданных
    товаров этого наименования*/

    Map<String, Double> statisticsByGoods(){
        Map<String, Double> result = new TreeMap<>();
        for (int i = 0; i < listOrders.size(); i++){
            for (int j = 0; j < listOrders.get(i).items.size(); j++){
                if (result.containsKey(listOrders.get(i).items.get(j).googsName)){
                    Double price = result.get(listOrders.get(i).items.get(j).googsName) + listOrders.get(i).items.get(j).price;
                    result.replace(listOrders.get(i).items.get(j).googsName, result.get(listOrders.get(i).items.get(j).googsName), price);
                }else {
                    result.put(listOrders.get(i).items.get(j).googsName, listOrders.get(i).items.get(j).price);
                }
            }

        }
        return result;
    }

    /*3.8 метод public Map<LocalDate, Double> statisticsByDay() - выдать информацию по объему продаж по дням
    (отсортированную по ключам): LocalDate - конкретный день, double - сумма стоимости всех проданных товаров
    в этот день*/

    public Map<LocalDate, Double> statisticsByDay(){
        Map<LocalDate, Double> result = new TreeMap<>();
        for (int i = 0; i < listOrders.size(); i++){
            LocalDate ld = listOrders.get(i).datetime.toLocalDate();
            if (result.containsKey(ld)){
                Double sumGoods = result.get(ld) + listOrders.get(i).sum;
                result.replace(ld, sumGoods);
            }
            else result.put(ld, listOrders.get(i).sum);
        }
        return result;
    }

    /*3.4 метод public int loadOrders(LocalDate start, LocalDate finish, String shopId) - загружает заказы за
    указанный диапазон дат, с start до finish, обе даты включительно. Если start == null, значит нет ограничения
    по дате слева, если finish == null, значит нет ограничения по дате справа, если shopId == null - грузим для
    всех магазинов При наличии хотя бы одной ошибке в формате файла, файл полностью игнорируется, т.е. Не поступает в
    обработку. Метод возвращает количество файлов с ошибками. При этом, если в классе содержалась информация, ее надо
    удалить*/

    public int loadOrders(LocalDate start, LocalDate finish, String shopId) throws IOException {
        int countNotRightFile = 0;
        OrderProcessor oP = new OrderProcessor(startPath);
        Files.walkFileTree(Paths.get(startPath), new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println(file);
                LocalDate ldLastModFile =
                        LocalDate.ofInstant(Files.getLastModifiedTime(file).toInstant(), ZoneId.systemDefault());
                if (shopId == null) {
                    if(!checkRightFile(file)) oP.countNotRightFiles++;
                }
                else
                if (start == null){
                    if (finish.isAfter(ldLastModFile) || finish.equals(ldLastModFile)){
                        if(!checkRightFile(file)) oP.countNotRightFiles++;
                    }
                }
                else
                if (finish == null){
                    if (start.isBefore(ldLastModFile) || start.equals(ldLastModFile)){
                        if(!checkRightFile(file)) oP.countNotRightFiles++;
                    }
                }
                else{
                    if ((finish.isAfter(ldLastModFile) || finish.equals(ldLastModFile)) &&
                            (start.isBefore(ldLastModFile) || start.equals(ldLastModFile))){
                        if(!checkRightFile(file)) oP.countNotRightFiles++;
                    }
                }

                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });
        return oP.countNotRightFiles;
    }

    private void loadOllOrders(){

    }

    public static void main(String[] args) throws IOException {
        OrderProcessor orderProcessor = new OrderProcessor("B:/1");
        orderProcessor.getAllOrders();
        
        System.out.println(orderProcessor.listOrders);
        System.out.println();
        
        System.out.println(orderProcessor.process("aaa"));
        System.out.println();

        for (Map.Entry entry : orderProcessor.statisticsByShop().entrySet()) {
            System.out.println(entry);
        }
        System.out.println();

        for (Map.Entry entry : orderProcessor.statisticsByGoods().entrySet()) {
            System.out.println(entry);
        }
        System.out.println();

        for (Map.Entry entry : orderProcessor.statisticsByDay().entrySet()) {
            System.out.println(entry);
        }
        System.out.println();

        System.out.println(orderProcessor.loadOrders(LocalDate.of(1980,1,1), LocalDate.of(2020,12,31), null));
    }
}
