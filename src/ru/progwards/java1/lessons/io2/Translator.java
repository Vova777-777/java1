package ru.progwards.java1.lessons.io2;
/*Сергей, хотелось бы узнать насколько понятен данный класс для другого, его читаемость, человеком, кто его не писал.
 Метод прошел проверку роботом, поэтому вроде как функционал полностью на 100% удовлетворителен. Лично я своими
 проверками обнаружил (точнее изначально понимал) что он не будет возвращать например если предложение
 заканчивается на "!!!", вернёт только один. Может этого и достаточно. Не знаю как было заложено в задании и пра
  вильно ли писать с точки зрения грамматики три знака. В любом случае меня очень интересует читаемость. Я этот класс
  позже буду переделывать, мне он кажется несколько уродским (Роберт Мартин не одобрил бы)))), может я слишком
  самокритичен. Интересно ваше мнение. */
import java.util.*;

public class Translator {
    private  String[] inLang;
    private String[] outLang;

    Translator(String[] inLang, String[] outLang){
        this.inLang = inLang;
        this.outLang = outLang;
    }

    public  String translate(String sentence){
      String[] arrayNoPunct = sentence.replaceAll("\\p{Punct}","").split(" ");
      String[] arrayWithPunctuation = sentence.split(" ");
      StringBuilder stringBuilderResult = new StringBuilder();
        for (int i = 0; i < arrayNoPunct.length; i++){
            for (int j = 0; j < inLang.length; j++){
               if (arrayNoPunct[i].equalsIgnoreCase(inLang[j])){ stringBuilderResult.append(outLang[j]);}
               else { continue;}
                if (!(arrayNoPunct[i].equalsIgnoreCase(arrayWithPunctuation[i]))){
                   stringBuilderResult.append(arrayWithPunctuation[i].substring(arrayWithPunctuation[i].length()-1));}
               stringBuilderResult.append(" ");
            }
        }

        String result = getNeedRegister(arrayNoPunct, inLang, stringBuilderResult.toString().trim());
        return reviseIndividualPunctuationMark(arrayNoPunct, arrayWithPunctuation, result);
    }

    public String getNeedRegister(String[] up, String[] down, String str){
       String[] array = str.split(" ");
        StringBuilder stringBuilderResult = new StringBuilder();
        for (int i = 0; i < array.length/*up.length*/; i++){
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
//следующий метод для проверки на знаки препинания, стоящие отдельно от слов. Например " - "
    public String reviseIndividualPunctuationMark(String[] arrayNoPunct, String[] arrayWithPunctuation, String result){
        String str = "";
        ArrayList listResult = new ArrayList();
          Collections.addAll(listResult,result.split(" "));
        StringBuilder stringBuilder = new StringBuilder();

        for(int i = 0; i < arrayNoPunct.length; i++){
            if(str.equals (arrayNoPunct[i])) {listResult.add(i,arrayWithPunctuation[i]);  }
        }
        for (int i = 0; i < listResult.size(); i++){
            stringBuilder.append(listResult.get(i));
            stringBuilder.append(" ");
        }
        return stringBuilder.toString().trim();
    }

    public static void main(String[] args) {
        String[] inLang = {"make", "love", "not", "war"};
        String[] outLang = {"твори", "любовь", "не", "войну"};

        System.out.println( new Translator(inLang, outLang).translate("Not war - love - make!"));


        Set<Character> PUNCT_SET = new HashSet<>(Arrays.asList(
                '!', '"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-',
                '.', '/', ':', ';', '<', '=', '>', '?', '@', '[', '\\', ']', '^',
                '_', '`', '{', '|', '}', '~'));

    }
}
