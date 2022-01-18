

package ru.progwards.java2.lessons.gc;

//import ru.progwards.java2.lessons.gc_art.OutOfMemoryException;
import ru.progwards.java2.lessons.gc.OutOfMemoryException;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class HeapTest {
    static final int maxSize = 1932735283;
    static final int maxSmall = 10;
    static final int maxMedium = 100;
    static final int maxBig = 1000;
    static final int maxHuge = 10000;
    static int allocated = 0;

//    static class Block {
//        public int ptr;
//        public int size;
//
//        public Block(int ptr, int size) {
//            this.ptr = ptr;
//            this.size = size;
//        }
//    }

    static class Block{
        int ptrStart;
        int sizeOfBlock;
        int ptrFinish;




        Block(int indicator, int sizeOfBlock){
            this.ptrStart = indicator;
            this.sizeOfBlock = sizeOfBlock;
            this.ptrFinish = indicator + sizeOfBlock - 1;
        }

//        public Block merge(Block otherBlock) {
//            if(this.indicator < otherBlock.indicator) {
//                new Block(this.indicator, this.sizeOfBlock + otherBlock.sizeOfBlock);
//            } else {
//                new Block(otherBlock.indicator, this.sizeOfBlock + otherBlock.sizeOfBlock);
//            }
//        }


        @Override
        public String toString() {
            return "Block{" +
                    "ptrStart=" + ptrStart +
                    ", sizeOfBlock=" + sizeOfBlock +
                    ", ptrFinish=" + ptrFinish +
                    '}';
        }
    }

    static int forDefrag;//!!!!!!!!!!!!!!!

    static int getRandomSize() {
        int n = Math.abs(ThreadLocalRandom.current().nextInt()%10);
        int size = Math.abs(ThreadLocalRandom.current().nextInt());
        if (n < 6)
            size %= maxSmall;
        else if (n < 8)
            size %= maxMedium;
        else if (n < 9)
            size %= maxBig;
        else
            size %= maxHuge;
        return size > (maxSize-allocated)-1 ? (maxSize-allocated)/2+1 : size+1;
    }

    public static void main(String[] args) throws InvalidPointerException, ru.progwards.java2.lessons.gc/*_art*/.InvalidPointerException, OutOfMemoryException {
        Heap heap = new Heap(maxSize);
        ArrayDeque<Block> blocks = new ArrayDeque<>();
        int count = 0;
        int allocTime = 0;
        int freeTime = 0;
        Random random = new Random();



        long start = System.currentTimeMillis();
        // alloc and free 30% random
        while ((maxSize - allocated) > 1600000000) {//50000
            long lstart, lstop;
            int size = getRandomSize();
            allocated += size;
            count++;
            lstart = System.currentTimeMillis();

           ;//!!!!!!!!!!!!!!!!!!
            heap.runCompactFromOtherMethod(200000);
            heap.runDefragFromOtherMethod(200000);
            int ptr = heap.malloc(size);
            lstop = System.currentTimeMillis();
            allocTime += lstop-lstart;
            int n = Math.abs(ThreadLocalRandom.current().nextInt()%25);
            if (n == 0) {
                //n = Math.abs(ThreadLocalRandom.current().nextInt()%blocks.size());
                int countRandom;//создаем сдучайные номера ячеек для удаления из heap
                if (heap.listOccupiedBlocks.isEmpty()) countRandom = 0;
                else if (heap.listOccupiedBlocks.size() < 5) countRandom = heap.listOccupiedBlocks.size() - 1;
                else countRandom = 5;
                Set<Integer> set = new HashSet<>();
                while(set.size() != countRandom) {
                    int maxRandom = heap.listOccupiedBlocks.size() - 1;
                    set.add(random.nextInt(maxRandom));
                }
                List<Integer> listNumberCell = new ArrayList<>(set);
                Collections.sort(listNumberCell);
                int number = 0;
                while (!listNumberCell.isEmpty()) {
                    lstart = System.currentTimeMillis();
                    Heap.Block block = heap.listOccupiedBlocks.get(listNumberCell.get(listNumberCell.size()-1));
                    listNumberCell.remove(listNumberCell.size()-1);
                    heap.free(block.ptrStart);
                    lstop = System.currentTimeMillis();
                    freeTime += lstop - lstart;
                    allocated -= block.sizeOfBlock;
                }
            }
            n = Math.abs(ThreadLocalRandom.current().nextInt()%100000);
            if (n==0)
                System.out.println(maxSize-allocated);
        }
        long stop = System.currentTimeMillis();
        System.out.println("malloc time: "+allocTime+" free time: "+freeTime);
        System.out.println("total time: "+(stop-start)+" count: "+count);
    }
}

//    malloc time: 2392 free time: 44919
//        total time: 47326 count: 740058


//defrag 500
//malloc time: 2418 free time: 49500
//        total time: 52043 count: 740421


//defrag 10000
//malloc time: 2324 free time: 45406
//        total time: 47841 count: 740225

//defrag 50000
//malloc time: 1561 free time: 47340
//        total time: 48996 count: 737343

//defrag 100000
//malloc time: 2341 free time: 45449
//        total time: 47916 count: 737020


//defrag 200000
//    malloc time: 1915 free time: 43887
//        total time: 45927 count: 734396

//defrag 300000
//malloc time: 1299 free time: 45450
//        total time: 46783 count: 742143

//defrag 400000
//malloc time: 2666 free time: 45588
//        total time: 48272 count: 742575


//compact 100000
//malloc time: 2137 free time: 49653
//        total time: 51903 count: 732177


//compact 200000
//malloc time: 1476 free time: 51580
//        total time: 53167 count: 738267


//compact 200000
//    malloc time: 1497 free time: 67146
//        total time: 68692 count: 735089

// defrag and compact every 200 000
//    malloc time: 1526 free time: 51970
//        total time: 53573 count: 735193