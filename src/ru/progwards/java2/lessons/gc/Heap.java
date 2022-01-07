package ru.progwards.java2.lessons.gc;



import java.util.ArrayList;
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

    int malloc(int size) throws OutOfMemoryException {
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
        if (result < 0) throw new OutOfMemoryException("нет свободного блока подходящего размера");
        return result;
    }

    //Метод public void free(int ptr) - "удаляет", т.е. помечает как свободный блок памяти по "указателю". Проверять
    // валидность указателя - т.е. то, что он соответствует началу ранее выделенного блока, а не его середине,
    // или вообще, уже свободному.

    void free(int ptr) throws InvalidPointerException {
        boolean validPtr = false;
        Iterator<Block> iterator = listOccupiedBlocks.iterator();
        while (iterator.hasNext()){
            Block block = iterator.next();
            if (ptr == block.indicator){
                listFreeBlocks.add(block);
                listOccupiedBlocks.remove(block);
                validPtr = true;
            }
        }
        if (validPtr == false) throw new InvalidPointerException("неверный указатель. Возникает при освобождении блока, "
                + "если переданный указатель не является началом блока");
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


    public static void main(String[] args) throws OutOfMemoryException, InvalidPointerException {
        Heap heap = new Heap(20);

        System.out.println("REVISE MALLOC\n\n");

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
        System.out.println("\n\n");

        System.out.println("REVISE FREE\n\n");
        //heap.free(10);
        heap.free(0);
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



    }
}
