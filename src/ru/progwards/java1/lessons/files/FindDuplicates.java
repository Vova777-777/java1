package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class FindDuplicates {
    /*В заданном каталоге и его подкаталогах найти файлы, точно совпадающие по названию (и расширению), дате-времени
последнего изменения, размеру и по содержимому. Сигнатура метода
public List<List<String>> findDuplicates(String startPath), результат - список, содержащий списки строк с именами
и полными путями совпадающих файлов.
 */
    public List<List<String>> findDuplicates(String startPath) throws IOException {
        List  listAllFiles = new ArrayList<>();
        List<List<String>> result = new ArrayList<>();
        Path path = Paths.get(startPath);
        Files.walkFileTree(path, new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                    System.out.println(path);
                listAllFiles.add(path);
                return FileVisitResult.CONTINUE;
            }
            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });

        Set<Path> set = new HashSet();
        for (int i = 0; i < listAllFiles.size() - 1; i++){
            Path path1 = (Path) listAllFiles.get(i);
            for (int j = i + 1; j < listAllFiles.size(); j++) {
                Path path2 = (Path) listAllFiles.get(j);
                if (!checkNameOfFile(path1, path2)) continue;
                else if (!checkLastModifiedTime(path1, path2)) continue;
                else if (!checkSizeOfFile(path1, path2)) continue;
                else if (!checkContentOfFile(path1, path2)) continue;
                else {set.add(path1); set.add(path2);}
            }
                if (set.isEmpty()) continue;
                result.add(new ArrayList(set));
                set.clear();
        }
        return result;
    }

    private boolean checkNameOfFile(Path path1, Path path2) throws Exception{
        if (path1.getFileName().equals(path2.getFileName())) return true;
        else return false;
    }

    private boolean checkLastModifiedTime(Path path1, Path path2) throws IOException {
        if (Files.getLastModifiedTime(path1).equals(Files.getLastModifiedTime(path2))) return true;
        else return false;
    }

    private boolean checkSizeOfFile(Path path1, Path path2) throws IOException {
      if (Files.size(path1) == Files.size(path2)) return true;
      else return false;
    }

    private boolean checkContentOfFile(Path path1, Path path2){
        byte[] array1 = null;
        byte[] array2 = null;
        try {
            array1 = Files.readAllBytes(path1);
            array2 = Files.readAllBytes(path2);

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Arrays.equals(array1, array2)) return true;
        else return false;

    }

    public static void main(String[] args) throws IOException {
        System.out.println(new FindDuplicates().findDuplicates("B:/1"));
    }

}
