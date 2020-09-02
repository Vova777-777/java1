package ru.progwards.java1.lessons.interfaces;

public interface CompareWeight {


    public enum CompareResult{
        LESS,
        EQUAL,
        GREATER
    }

    public CompareResult compareWeight(CompareWeight smthHasWeigt);

    public static void sort(CompareWeight[] a){
        Animal[] b;
        b = (Animal[]) a;

        for ( int i = 0; i < b.length - 1; i++){
            for (int j = i + 1; j < b.length; j++){
                Animal k;
                if (b[i].getWeight() > b[j].getWeight()) {k = b[i]; b[i] = b[j]; b[j] = k;}
            }
        }
    }
}
