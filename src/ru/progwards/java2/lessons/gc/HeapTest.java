

package ru.progwards.java2.lessons.gc;

//import ru.progwards.java2.lessons.gc_art.OutOfMemoryException;
import ru.progwards.java2.lessons.gc.OutOfMemoryException;

import java.util.ArrayDeque;
import java.util.concurrent.ThreadLocalRandom;

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



        long start = System.currentTimeMillis();
        // alloc and free 30% random
        while ((maxSize - allocated) > 50000) {
            long lstart, lstop;
            int size = getRandomSize();
            allocated += size;
            count++;
            lstart = System.currentTimeMillis();
            //if(forDefrag % 400 == 0) heap.defrag();//!!!!!!!!!!!!!!!!
            if(forDefrag % 50 == 0) heap.compact();//!!!!!!!!!!!!!!!!
            forDefrag++;
            int ptr = heap.malloc(size);
            lstop = System.currentTimeMillis();
            allocTime += lstop-lstart;
            blocks.offer(new Block(ptr, size));
            int n = Math.abs(ThreadLocalRandom.current().nextInt()%25);
            if (n == 0) {
                //n = Math.abs(ThreadLocalRandom.current().nextInt()%blocks.size());
                for (int i=0; i<5; i++) {
                    Block block = blocks.poll();
                    if (block == null)
                        break;
                    lstart = System.currentTimeMillis();
                    heap.free(block.ptrStart);
                    lstop = System.currentTimeMillis();
                    freeTime += lstop - lstart;
                    allocated -= block.sizeOfBlock;
                }
                //blocks.remove(n);
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
//only malloc and free without defrag and compact
//malloc time: 5525349 free time: 343329
//total time: 5870021 count: 4284937


//before malloc only defrag every 100 times
//malloc time: 2694 free time: 272897
//total time: 275813 count: 4279216

//before malloc only defrag every 200 times
//malloc time: 2470 free time: 265328
//total time: 268120 count: 4312444

//before malloc only defrag every 300 times
//malloc time: 2404 free time: 257529
//total time: 260251 count: 4287204

//before malloc only defrag every 400 times
//malloc time: 2194 free time: 278816
//total time: 281512 count: 4292447
//we can look that malloc time became less but free time increase and total time increase, so every 300 only defrag optimal
