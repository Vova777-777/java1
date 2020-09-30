package ru.progwards.java1.lessons.collections;


import java.util.Iterator;

    public class ArrayIterator<T> implements Iterator<T> {

        private T[] array;
        int count = -1;

        ArrayIterator(T[] array) {
            this.array = array;
        }

        @Override
        public boolean hasNext() {
            if (this.count == array.length - 1)// TODO Auto-generated method stub
            return false;
            else return true;
        }

        @Override
        public T next() {
            count++;
           // TODO Auto-generated method stub
            return this.array[count];
        }

        public static void main(String[] args) {
            Integer[] array = {42,12,33,22,11,444,66778};


            ArrayIterator iterator = new ArrayIterator(array);
            while (iterator.hasNext()){
               String a = iterator.next().toString();
                System.out.println(a);
            }

        }
    }

