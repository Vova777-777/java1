package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileTime;
import java.time.*;
import java.util.Date;

public class Vspomogatelniy {


    private static void createDirectory(String strPath) throws IOException {
        if (!Files.exists(Paths.get(strPath)))
            Files.createDirectory(Paths.get(strPath));
    }

    private static void createFile(String strPath) throws IOException {
        if (!Files.exists(Paths.get(strPath)))
            Files.createFile(Paths.get(strPath));
    }

    private static void writeFile(String strPath, String strConnect) throws IOException {
       Files.write(Paths.get(strPath), strConnect.getBytes(), StandardOpenOption.APPEND);
    }

    private static void setLastMod(String strPath, Date date) throws IOException {
        Files.setLastModifiedTime(Paths.get(strPath), FileTime.fromMillis(date.getTime()));
    }

    private static void createForReviseOrderProcessor() throws IOException {
        createDirectory("B:/1/1");
        createDirectory("B:/1/2");
        createDirectory("B:/1/3");

        createFile("B:/1/3/S02-P01X05-0002.csv");
        writeFile("B:/1/3/S02-P01X05-0002.csv","Пазл “Замок в лесу”, 1, 700");
        LocalDateTime ld = LocalDateTime.of(2020,1,16,17,16,16);
        FileTime fileTime = FileTime.from(ld.toInstant(ZoneOffset.ofHours(3)));
        Files.setLastModifiedTime(Paths.get("B:/1/3/S02-P01X05-0002.csv"), fileTime);

        createFile("B:/1/3/S01-P01X02-0002.csv");
        writeFile("B:/1/3/S01-P01X02-0002.csv","Пазл “Замок в лесу”, 1, 700");
        LocalDateTime ld1 = LocalDateTime.of(2020,1,14,15,14,14);
        FileTime fileTime1  = FileTime.from(ld1.toInstant(ZoneOffset.ofHours(3)));
        Files.setLastModifiedTime(Paths.get("B:/1/3/S01-P01X02-0002.csv"), fileTime1);

        createFile("B:/1/3/S02-P01X04-0002.csv");
        writeFile("B:/1/3/S02-P01X04-0002.csv","Error: credit card can not be validated");
        LocalDateTime ld2 = LocalDateTime.of(2020,1,16,17,16,16);
        FileTime fileTime2  = FileTime.from(ld2.toInstant(ZoneOffset.ofHours(3)));
        Files.setLastModifiedTime(Paths.get("B:/1/3/S02-P01X04-0002.csv"), fileTime2);

        createFile("B:/1/1/S01-P01X01-0001.csv");
        writeFile("B:/1/1/S01-P01X01-0001.csv","Игрушка мягкая “Мишка”, 1, 1500\n");
        writeFile("B:/1/1/S01-P01X01-0001.csv","Пазл “Замок в лесу”, 2, 700\n");
        writeFile("B:/1/1/S01-P01X01-0001.csv","Книжка “Сказки Пушкина”, 1, 300");
        LocalDateTime ld3 = LocalDateTime.of(2020,1,1,13,0,0);
        FileTime fileTime3  = FileTime.from(ld3.toInstant(ZoneOffset.ofHours(3)));
        Files.setLastModifiedTime(Paths.get("B:/1/1/S01-P01X01-0001.csv"), fileTime3);

        createFile("B:/1/1/S02-P01X01-0001.csv");
        writeFile("B:/1/1/S02-P01X01-0001.csv","Игрушка мягкая “Мишка”, 1, 1500\n");
        writeFile("B:/1/1/S02-P01X01-0001.csv","Книжка “Сказки Пушкина”, 2, 300");
        LocalDateTime ld4 = LocalDateTime.of(2020,1,1,16,0,0);
        FileTime fileTime4  = FileTime.from(ld4.toInstant(ZoneOffset.ofHours(3)));
        Files.setLastModifiedTime(Paths.get("B:/1/1/S02-P01X01-0001.csv"), fileTime4);

        createFile("B:/1/2/S02-P01X03-000.csv");
        writeFile("B:/1/2/S02-P01X03-000.csv","Книжка “Сказки Пушкина”, 1, 300");
        LocalDateTime ld5 = LocalDateTime.of(2020,1,10,16,15,15);
        FileTime fileTime5  = FileTime.from(ld5.toInstant(ZoneOffset.ofHours(3)));
        Files.setLastModifiedTime(Paths.get("B:/1/2/S02-P01X03-000.csv"), fileTime5);

        createFile("B:/1/2/S02-P01X02-0003.csv");
        writeFile("B:/1/2/S02-P01X02-0003.csv","Игрушка мягкая “Мишка”, 1, 1500");
        LocalDateTime ld6 = LocalDateTime.of(2020,1,5,13,12,12);
        FileTime fileTime6  = FileTime.from(ld6.toInstant(ZoneOffset.ofHours(3)));
        Files.setLastModifiedTime(Paths.get("B:/1/2/S02-P01X02-0003.csv"), fileTime6);

        createFile("B:/1/2/S02-P01X03-0003.csv");
        writeFile("B:/1/2/S02-P01X03-0003.csv","Книжка “Сказки Пушкина”, 1, 300");
        LocalDateTime ld7 = LocalDateTime.of(2020,1,10,16,15,15);
        FileTime fileTime7  = FileTime.from(ld7.toInstant(ZoneOffset.ofHours(3)));
        Files.setLastModifiedTime(Paths.get("B:/1/2/S02-P01X03-0003.csv"), fileTime7);
    }

    private void createForReviseFindDuplicates() throws IOException {
        createFile("B:/1/file1");
        writeFile("B:/1/file1", "Идентичный 1");
        createFile("B:/1/file2");
        writeFile("B:/1/file1", "Идентичный 2");
        createDirectory("B:/1/dir1");
        createDirectory("B:/1/dir1");
    }

    private static void createForCheckFilesSelect() throws IOException {
        createDirectory("B:/1/dir1");
        createDirectory("B:/1/dir2");
        createDirectory("B:/1/dir3");
        createFile("B:/1/dir1/file1.txt");
        createFile("B:/1/dir1/file11.txt");
        createFile("B:/1/dir2/file2.txt");
        createFile("B:/1/dir2/file22.txt");
        createFile("B:/1/dir3/file3.txt");
        createFile("B:/1/dir3/file33.bin");
    }

    public static void main(String[] args) throws IOException {
        createForCheckFilesSelect();
    }
}
