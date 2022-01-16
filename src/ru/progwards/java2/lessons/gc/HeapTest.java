

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
        while ((maxSize - allocated) > 1700000000) {//50000
            long lstart, lstop;
            int size = getRandomSize();
            allocated += size;
            count++;
            lstart = System.currentTimeMillis();

            heap.runDefragFromOtherMethod(300);//!!!!!!!!!!!!!!!!!!
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
//                blocks.remove(n);
                blocks.clear();//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            }
            n = Math.abs(ThreadLocalRandom.current().nextInt()%100000);
            if (n==0)
                System.out.println(maxSize-allocated);

//            for (Heap.Block block : heap.listOccupiedBlocks){//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//               blocks.add(new Block(block.ptrStart, block.sizeOfBlock));
//            }
        }
        long stop = System.currentTimeMillis();
        System.out.println("malloc time: "+allocTime+" free time: "+freeTime);
        System.out.println("total time: "+(stop-start)+" count: "+count);
    }
}


//defrag 300
//malloc time: 47 free time: 44583
//       total time: 44708 count: 510958
