package ru.progwards.java1.lessons.io1;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CharFilter {
        public static void main(String[] args) throws IOException {
            String str = "не !";

            System.out.println(new CharFilter().listWithCharsFromFile("B://1//test1.txt").toString());
        }

    public static void filterFile(String inFileName, String outFileName, String filter){
        char[] array = filter.toCharArray();
        try {
            List list = new CharFilter().listWithCharsFromFile(inFileName);

            int lengthOfList = list.size();
            int k = -1;//индекс для списка чтобы не было ошибки при удалении ( вместо int i; )
            for (int i = 0; i < lengthOfList; i++){
             k++;
                for(int j = 0; j < array.length; j++){
                if (list.get(k).equals(array[j])) {list.remove(k); k--;}

                }
            }
            new CharFilter().writerFileMethod(outFileName, list);
        }catch (IOException e){
            e.getMessage();
        }

    }

    public ArrayList listWithCharsFromFile (String fileName) throws IOException {
        ArrayList list = new ArrayList();
        FileReader fileReader = new FileReader(fileName);
        while (fileReader.ready()){
            char a = (char) fileReader.read();
            list.add(a);
        }
        fileReader.close();
        return list;
    }

    public void writerFileMethod (String outFileName, List list) throws IOException {
            FileWriter writerFile = new FileWriter(outFileName);
            String str = "";
            for (int i = 0; i < list.size(); i++){
                str = str + list.get(i);
            }
            writerFile.write(str);
            writerFile.close();
    }



}
