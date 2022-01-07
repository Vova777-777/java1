package ru.progwards.java2.lessons.gc;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Heap {
    int maxHeapSize;
    byte[] bytes = new byte[maxHeapSize];
    List<Block> listFreeBlocks = new ArrayList<>();
    List<Block> listOccupiedBlocks = new ArrayList<>();


    Heap(int maxHeapSize){
        this.maxHeapSize = maxHeapSize;
        listFreeBlocks.add(new Block(0, maxHeapSize));
    }


    // Метод public int malloc(int size) - "размещает", т.е. помечает как занятый блок памяти с количеством ячеек
    // массива heap равным size. Соответственно это должен быть непрерывный блок (последовательность ячеек), которые на
    // момент выделения свободны. Возвращает "указатель" - индекс первой ячейки в массиве, размещенного блока.

    int malloc(int size){
        int result = -1;
        Iterator<Block> iterator = listFreeBlocks.iterator();
        while (iterator.hasNext()){
            Block freeBlock = iterator.next();
            if (size <= freeBlock.sizeOfBlock){
                Block blockWritten = new Block(freeBlock.indicator, size);
                listOccupiedBlocks.add(blockWritten);
                Block blockClean = new Block(blockWritten.finishIndicator + 1, freeBlock.sizeOfBlock -
                        size);
                listFreeBlocks.remove(freeBlock);
                listFreeBlocks.add(blockClean);
                result = blockWritten.indicator;
            }
        }
        if (result < 0) throw new OutOfMemoryError("нет свободного места");
        return result;
    }


    private class Block{
        int indicator;
        int sizeOfBlock;
        int finishIndicator;


        Block(int indicator, int sizeOfBlock){
            this.indicator = indicator;
            this.sizeOfBlock = sizeOfBlock;
            this.finishIndicator = indicator + sizeOfBlock - 1;
        }

        @Override
        public String toString() {
            return "Block{" +
                    "indicator=" + indicator +
                    ", sizeOfBlock=" + sizeOfBlock +
                    ", finishIndicator=" + finishIndicator +
                    '}';
        }
    }


    public static void main(String[] args) {
        Heap heap = new Heap(20);

        heap.malloc(5);
        System.out.println("Blocks of listFreeBlocks");
        for (Block block: heap.listFreeBlocks) {
            System.out.println(block.toString());
        }
        System.out.println("");

        System.out.println("Blocks of listOccupiedBlocks");
        for (Block block: heap.listOccupiedBlocks) {
            System.out.println(block.toString());
        }
        System.out.println("\n");

        heap.malloc(3);
        System.out.println("Blocks of listFreeBlocks");
        for (Block block: heap.listFreeBlocks) {
            System.out.println(block.toString());
        }
        System.out.println("");

        System.out.println("Blocks of listOccupiedBlocks");
        for (Block block: heap.listOccupiedBlocks) {
            System.out.println(block.toString());
        }


    }
}
