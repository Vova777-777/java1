package ru.progwards.java1.lessons.io2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Translator {
    private  String[] inLang;
    private String[] outLang;

    Translator(String[] inLang, String[] outLang){
        this.inLang = inLang;
        this.outLang = outLang;
    }

    public String[] getInLang() {
        return inLang;
    }

    public String[] getOutLang() {
        return outLang;
    }

    public void setInLang(String[] inLang) {
        this.inLang = inLang;
    }

    public void setOutLang(String[] outLang) {
        this.outLang = outLang;
    }

    public  String translate(String sentence){
      String[] arrayNoPunct = sentence.replaceAll("\\p{Punct}","").split(" ");
      String[] arrayWithPunctuation = sentence.split(" ");


        StringBuilder stringBuilderResult = new StringBuilder();
        for (int i = 0; i < arrayNoPunct.length; i++){
            for (int j = 0; j < inLang.length; j++){
               if (arrayNoPunct[i].equalsIgnoreCase(inLang[j])){ stringBuilderResult.append(outLang[j]);}
               else continue;
               if (!(arrayNoPunct[i].equalsIgnoreCase(arrayWithPunctuation[i]))){
                   stringBuilderResult.append(arrayWithPunctuation[i].substring(arrayWithPunctuation[i].length()-1));}
               stringBuilderResult.append(" ");
            }
        }

        return getNeedRegister(arrayNoPunct, inLang, stringBuilderResult.toString().trim());
    }

    public String getNeedRegister(String[] up, String[] down, String str){
       String[] array = str.split(" ");
        StringBuilder stringBuilderResult = new StringBuilder();
        for (int i = 0; i < up.length; i++){
            stringBuilderResult.replace(0,stringBuilderResult.length(),"");
            stringBuilderResult.append(array[i]);
            for (int j = 0; j < down.length; j++) {
                if (up[i].equalsIgnoreCase(down[j]) && !(up[i].equals(down[j]))) {
                    String firstChar = array[i].substring(0, 1).toUpperCase();
                   stringBuilderResult.replace(0, 1, firstChar).toString();
                }
                else continue;


                 array[i] = stringBuilderResult.toString();

            }


        }
        stringBuilderResult.replace(0, stringBuilderResult.length(),"");
        for (int i = 0; i < array.length; i++){
            stringBuilderResult.append(array[i]);
            stringBuilderResult.append(" ");
        }

        return stringBuilderResult.toString().trim();

    }

    public static void main(String[] args) {
        String[] inLang = {"make", "love", "not", "war"};
        String[] outLang = {"твори", "любовь", "не", "войну"};

        System.out.println( new Translator(inLang, outLang).translate("Make love! Not war!"));


        Set<Character> PUNCT_SET = new HashSet<>(Arrays.asList(
                '!', '"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-',
                '.', '/', ':', ';', '<', '=', '>', '?', '@', '[', '\\', ']', '^',
                '_', '`', '{', '|', '}', '~'));

    }


}
