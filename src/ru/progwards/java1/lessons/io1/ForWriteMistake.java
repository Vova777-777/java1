package ru.progwards.java1.lessons.io1;

import java.io.FileWriter;
import java.io.IOException;

public class ForWriteMistake {
    protected void writerMistakeFile(String  outFileName, Exception e){
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(outFileName,true);
            try {
                fileWriter.write(e.getMessage() + "\n");
            }finally {
                fileWriter.close();
            }
        }catch (IOException ex){
            e.getMessage();
        }
    }
}
