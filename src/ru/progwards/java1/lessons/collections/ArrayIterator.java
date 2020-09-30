package ru.progwards.java1.lessons.collections;

import java.util.Iterator;

    public class ArrayIterator<T> implements Iterator<T> {

        private T[] array;

        ArrayIterator(T[] array) {
            this.array = array;
        }

        @Override
        public boolean hasNext() {
            if (this.equals(array[array.length-1]))// TODO Auto-generated method stub
            return false;
            else return true;

        }

        @Override
        public T next() {
            if (this.hasNext())// TODO Auto-generated method stub
            return this.next();
           else return null;
        }
    }

