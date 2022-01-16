package ru.progwards.java2.lessons.gc;



import java.util.*;

public class Heap {
    int maxHeapSize;
    byte[] bytes;
    static int countSkipCompact = 0;
    static int countSkipDefrag = 0;

    List<Block> listFreeBlocks = new ArrayList<>();
    List<Block> listOccupiedBlocks = new ArrayList<>();

    Heap(int maxHeapSize){
        bytes = new byte[maxHeapSize];
        this.maxHeapSize = maxHeapSize;
        listFreeBlocks.add(new Block(0, maxHeapSize));

    }

    public class Block{
        int ptrStart;
        int sizeOfBlock;
        int ptrFinish;

        Block(int indicator, int sizeOfBlock){
            this.ptrStart = indicator;
            this.sizeOfBlock = sizeOfBlock;
            this.ptrFinish = indicator + sizeOfBlock - 1;
        }

        @Override
        public String toString() {
            return "Block{" +
                    "indicator=" + ptrStart +
                    ", sizeOfBlock=" + sizeOfBlock +
                    ", finishIndicator=" + ptrFinish +
                    '}';
        }
    }


    // Метод public int malloc(int size) - "размещает", т.е. помечает как занятый блок памяти с количеством ячеек
    // массива heap равным size. Соответственно это должен быть непрерывный блок (последовательность ячеек), которые на
    // момент выделения свободны. Возвращает "указатель" - индекс первой ячейки в массиве, размещенного блока.

    int malloc(int size) throws OutOfMemoryException {
        int result = -1;
        ListIterator<Block> iterator = listFreeBlocks.listIterator();
        while (iterator.hasNext()){
            Block cleanBlock = iterator.next();
            if (size <= cleanBlock.sizeOfBlock){
                Block blockWritten = new Block(cleanBlock.ptrStart, size);
                listOccupiedBlocks.add(blockWritten);
                if (size != cleanBlock.sizeOfBlock){
                Block creatingBlockClean = new Block(blockWritten.ptrFinish + 1, cleanBlock.sizeOfBlock -
                        size);
                iterator.remove();

                  listFreeBlocks.add(creatingBlockClean);
                }
                else {
                    iterator.remove();
                }
                result = blockWritten.ptrStart;
                break;
            }
        }
        if (result < 0) throw new OutOfMemoryException("нет свободного блока подходящего размера");
        return result;
    }



    //Метод public void free(int ptr) - "удаляет", т.е. помечает как свободный блок памяти по "указателю". Проверять
    // валидность указателя - т.е. то, что он соответствует началу ранее выделенного блока, а не его середине,
    // или вообще, уже свободному.

    void free(int ptr) throws InvalidPointerException {
//        runCompactFromOtherMethod(1);
        boolean validPtr = false;
        Iterator<Block> iterator = listOccupiedBlocks.iterator();
        while (iterator.hasNext()){
            Block block = iterator.next();
            if (ptr == block.ptrStart){
                listFreeBlocks.add(block);
                iterator.remove();
                validPtr = true;
                break;
            }
        }
        if (!validPtr) throw new InvalidPointerException("неверный указатель. Возникает при освобождении блока, "
                + "если переданный указатель не является началом блока");
    }

     public void defrag(){
        listFreeBlocks.sort(Comparator.comparing(x -> x.ptrStart));
        List<Block> listForRemove = new ArrayList<>();
        ListIterator<Block> iterator = listFreeBlocks.listIterator();
        if (listFreeBlocks.size() < 2) return;// there are one big block or no free space
        Block block = iterator.next();
        while (iterator.hasNext()){
            //Block blockNext = iterator.previous();

            Block blockNext = iterator.next();
            Block blockPre = listFreeBlocks.get(listFreeBlocks.indexOf(blockNext) - 1);
            if (blockNext.ptrStart == blockPre.ptrFinish + 1){
                iterator.add(new Block(blockPre.ptrStart, blockNext.sizeOfBlock + blockPre.sizeOfBlock));
                listForRemove.add(blockPre);
                listForRemove.add(blockNext);
            }
        }
        listFreeBlocks.removeAll(listForRemove);
    }

    void runDefragFromOtherMethod (int frequencyRunning){
        countSkipDefrag++;
        if (countSkipDefrag % frequencyRunning != 0) return;
        defrag();
        countSkipDefrag =0;
    }

    // Метод public void compact() - компактизация кучи - перенос всех занятых блоков в начало хипа, с копированием
    // самих данных - элементов массива. Для более точной имитации производительности копировать просто в цикле по
    // одному элементу, не используя System.arraycopy. Обязательно запускаем compact из malloc если не нашли блок
    // подходящего размера

    public void compact(){
        int sizeOfAlOccupied = 0;
       int j = 0;
        listOccupiedBlocks.sort(Comparator.comparing(x -> x.ptrStart));
        for (Block block : listOccupiedBlocks) {
            for (int i = block.ptrStart; i <= block.ptrFinish; i ++){
               if (i == j) {j = j + block.sizeOfBlock; break;}
               if (i == block.ptrStart){block.ptrStart = j; }
                bytes[j] = bytes[i];
                bytes[i] = 0;
                if (i == block.ptrFinish){block.ptrFinish = block.ptrStart + block.sizeOfBlock - 1;}
                j++;
            }
            sizeOfAlOccupied += block.sizeOfBlock;
        }
        if (listOccupiedBlocks.size() > 0) {
            listFreeBlocks.clear();
            Block blockLastOfListOccupied = listOccupiedBlocks.get(listOccupiedBlocks.size() - 1);
            listFreeBlocks.add(new Block(blockLastOfListOccupied.ptrFinish + 1,
                    maxHeapSize - sizeOfAlOccupied));
        }
    }

     void runCompactFromOtherMethod (int frequencyRunning){
        countSkipCompact++;
        if (countSkipCompact % frequencyRunning != 0) return;
        compact();
        countSkipCompact =0;
    }

    public void getBytes(int ptr, byte[] bytes) {
        System.arraycopy(this.bytes, ptr, bytes, 0, this.bytes.length);
    }

    public void setBytes(int ptr, byte[] bytes) {
        System.arraycopy(bytes, 0, this.bytes, ptr, bytes.length);
    }






    public static void main(String[] args) throws OutOfMemoryException, InvalidPointerException {
        Heap heap = new Heap(20);
        System.out.println(heap.bytes[4]);
        System.out.println(heap.bytes.length);
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
