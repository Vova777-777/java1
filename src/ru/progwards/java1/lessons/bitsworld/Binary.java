package ru.progwards.java1.lessons.bitsworld;

public class Binary {
   int num;

    public Binary(byte num){
this.num = num;
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder(" ");
        for (int i = 7; i >= 0; i --) {
            int numberFor = num >>> i;
            int bit = numberFor & 1;
            str.append(bit);
        }
        return num + ":" + str;
    }

    public static void main(String[] args) {
        Binary binary = new Binary((byte)-5);
        System.out.println(binary.toString());
    }
}
