package ru.progwards.java1.lessons.io2;


import java.io.IOException;
import java.io.RandomAccessFile;

public class Censor {

    public static class CensorException extends Exception{
        public String fileName = "";
        public CensorException(String fileName){
            super();
            this.fileName = fileName;
        }
        @Override
        public String getMessage() {
            return  fileName + ":" + super.getMessage();
        }
    }



    public static void censorFile(String inoutFileName, String[] obscene) throws CensorException {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(inoutFileName, "rw")){
          int b = randomAccessFile.read();
          String dateFromFile = "";
          while (b != -1){
              dateFromFile = dateFromFile + (char) b;
              b = randomAccessFile.read();
          }

          for ( int i = 0; i < obscene.length; i++){
               if (dateFromFile.contains(obscene[i])) {changeWardToStars(inoutFileName,dateFromFile,obscene[i]);}
            }

        } catch (Exception e) {
            CensorException censorExeption = new CensorException(inoutFileName);
            throw censorExeption;
        }
    }

    public static void changeWardToStars(String inoutFileName, String dateFromFile, String wardForChange){
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(inoutFileName, "rw")){
            String starsForChange = "";
            for (int i = 0; i < wardForChange.length(); i++){
                starsForChange = starsForChange + "*";
            }
            int a = 0;
            while (a != -1){
                a = dateFromFile.indexOf(wardForChange,a);
                randomAccessFile.seek(a);
                randomAccessFile.writeBytes(starsForChange);
                a++;
            }
        }catch (IOException e){
            e.getMessage();
        }
    }

    public static void main(String[] args){
        String str = "loves much his";
        try {
            censorFile(null, null);
        } catch (CensorException e) {
            System.out.println(e);
        }
    }

}
