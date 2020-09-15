package ru.progwards.java1.lessons.io1;


import java.io.FileWriter;
import java.io.IOException;


public class ForWrite {
    protected void writerFile(String  outFileName, char a/*String str*/, String logName){
        ForWriteMistake writerMistake = new ForWriteMistake();
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(outFileName,true);
            try {
                fileWriter.write(a + "\n");
            }finally {
                fileWriter.close();
            }
        }catch (IOException e){
            writerMistake.writerMistakeFile(logName, e);
        }
    }
}
