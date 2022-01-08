package ru.progwards.java2.lessons.gc;



import java.util.*;

public class Heap {
    int maxHeapSize;
    byte[] bytes = new byte[maxHeapSize];
    List<Block> listFreeBlocks = new ArrayList<>();
    List<Block> listOccupiedBlocks = new ArrayList<>();
    static Queue<Block> queueDefrag = new ArrayDeque<>();


    Heap(int maxHeapSize){
        this.maxHeapSize = maxHeapSize;
        listFreeBlocks.add(new Block(0, maxHeapSize));
    }


    // Метод public int malloc(int size) - "размещает", т.е. помечает как занятый блок памяти с количеством ячеек
    // массива heap равным size. Соответственно это должен быть непрерывный блок (последовательность ячеек), которые на
    // момент выделения свободны. Возвращает "указатель" - индекс первой ячейки в массиве, размещенного блока.

    int malloc(int size) throws OutOfMemoryException {
        int result = -1;
        Queue queue = new ArrayDeque();
        Iterator<Block> iterator = listFreeBlocks.iterator();
        while (iterator.hasNext()){
            Block freeBlock = iterator.next();
            if (size <= freeBlock.sizeOfBlock){
                Block blockWritten = new Block(freeBlock.indicator, size);
                listOccupiedBlocks.add(blockWritten);
                Block blockClean = new Block(blockWritten.finishIndicator + 1, freeBlock.sizeOfBlock -
                        size);
                iterator.remove();
                queue.add(blockClean);
                result = blockWritten.indicator;
                break;
            }
        }
        listFreeBlocks.addAll(queue);
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
                iterator.remove();
                validPtr = true;
                break;
            }
        }
        if (validPtr == false) throw new InvalidPointerException("неверный указатель. Возникает при освобождении блока, "
                + "если переданный указатель не является началом блока");
    }

    public void defrag(){
        Iterator<Block> iterator = listFreeBlocks.iterator();
        if (listFreeBlocks.isEmpty()) return;
        Queue<Block> queueRemove = new ArrayDeque<>();
        Block blockResult = null;
        Block block = null;
        while (iterator.hasNext()){
            block = iterator.next();
            for (Block element: listFreeBlocks) {
                if (element == block) continue;
                if (block.indicator == (element.finishIndicator + 1)){
                    blockResult = new Block(element.indicator, (block.sizeOfBlock + element.sizeOfBlock));
                    queueRemove.add(block);
                    queueRemove.add(element);
                    break;
                }
            }
            if (blockResult !=null) break;
        }
        for (Block blo : queueRemove) {
            listFreeBlocks.remove(blo);
        }
        if (blockResult == null) return;
        listFreeBlocks.add(blockResult);
        defrag();
    }

    //. Метод public void compact() - компактизация кучи - перенос всех занятых блоков в начало хипа, с копированием
    // самих данных - элементов массива. Для более точной имитации производительности копировать просто в цикле по
    // одному элементу, не используя System.arraycopy. Обязательно запускаем compact из malloc если не нашли блок
    // подходящего размера

    public void compact(){
       int j = 0;
        listOccupiedBlocks.sort(Comparator.comparing(x -> x.indicator));
        for (Block block : listOccupiedBlocks) {
            for (int i = block.indicator; i <= block.finishIndicator; i ++){
                bytes[j] = bytes[i];
                bytes[i] = 0;
                j++;
            }
        }
    }

    public void getBytes(int ptr, byte[] bytes) {
        System.arraycopy(this.bytes, ptr, bytes, 0, this.bytes.length);
    }

    public void setBytes(int ptr, byte[] bytes) {
        System.arraycopy(bytes, 0, this.bytes, ptr, bytes.length);
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

        System.out.println("REVISE MALLOC\n");
        System.out.println("used method mooloc(5)");
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
        System.out.println("used method mooloc(3)");
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
        System.out.println("used method free(0)");
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

        System.out.println("REVISE Defrag");
        System.out.println("use methods free(5) and defrag. After that all blocks have to connect in one free block");
        heap.free(5);
        heap.defrag();
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

        System.out.println("REVISE COMPACT");
        heap.bytes = new byte[] {0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,2,2,2,0,0};
        heap.malloc(5);
        heap.malloc(5);
        heap.malloc(5);
        heap.malloc(3);
        heap.free(0);
        heap.free(10);
        System.out.println("Before");
        System.out.println("Blocks of listFreeBlocks");
        for (Block block: heap.listFreeBlocks) {
            System.out.println(block.toString());
        }
        System.out.println("");

        System.out.println("Blocks of listOccupiedBlocks");
        for (Block block: heap.listOccupiedBlocks) {
            System.out.println(block.toString());
        }
        heap.compact();
        System.out.println("After");
        System.out.println("Blocks of listFreeBlocks");
        for (Block block: heap.listFreeBlocks) {
            System.out.println(block.toString());
        }
        System.out.println("");

        System.out.println("Blocks of listOccupiedBlocks");
        for (Block block: heap.listOccupiedBlocks) {
            System.out.println(block.toString());
        }
        System.out.println(Arrays.toString(heap.bytes));



    }
}
