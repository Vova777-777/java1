package ru.progwards.java1.lessons.bitsworld;

public class Binary {
   int num;

    public Binary(byte num){
this.num = num;
    }

    @Override
    public String toString(){
        String str = "";
        for (int i = 7; i >= 0; i --) {
            int numberFor = num >>> i;
            int bit = numberFor & 1;
            str = str + bit;
        }
        return str;
    }

    public static void main(String[] args) {
        Binary binary = new Binary((byte)3);
        System.out.println(binary.toString());
    }
}
