package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class FilesSelect {
    /*Реализовать метод с сигнатурой public void selectFiles(String inFolder, String outFolder, List<String> keys),
    который сортирует файлы по их содержимому. Нужно просмотреть содержимое всех файлов, с расширением txt,
    содержащихся в каталоге inFolder с подкаталогами, и если файл содержит ключевое слово из коллекции keys,
    то скопировать его в подпапку с соответствующим именем, этого элемента keys, все подпапки должны находиться
    в outFolder. */

    public void selectFiles(String inFolder, String outFolder, List<String> keys){
        try {
            PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/**.txt");
            Files.walkFileTree(Paths.get(inFolder), new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    List<String> contentFile = new ArrayList<>();

                    if (pathMatcher.matches(file)) contentFile = Files.readAllLines(file);
                    else return FileVisitResult.CONTINUE;
                    List<String> key = checkContentFileAndGetKey(contentFile, keys); //Создать отдельно метод на проверку и вызвать его

                    if (key.isEmpty()) return FileVisitResult.CONTINUE;
                    for (int i = 0; i < key.size(); i++) {
                        if (!Files.exists(Paths.get(outFolder + "/" + key.get(i)))) {
                            Path dirForCopy = Paths.get(outFolder + "/" + key.get(i));
                            dirForCopy = Files.createDirectory(Paths.get(outFolder + "/" + key.get(i)));
                            Files.copy(file, dirForCopy.resolve(file.getFileName()));
                        } else Files.copy(file, Paths.get(outFolder + "/" + key.get(i)).resolve(file.getFileName()));
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }
            });
        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }
    }

    private List<String> checkContentFileAndGetKey(List<String> contentFile, List<String> keys){
        List<String> list = new ArrayList();

        for (int i = 0; i < contentFile.size(); i++){
            String str = contentFile.get(i);
            for (int j = 0; j < keys.size(); j++) {
                if (str.contains(keys.get(j))) list.add(keys.get(j));
            }
        }
        return list;
    }

    public static void main(String[] args) throws IOException {
        FilesSelect filesSelect = new FilesSelect();

        List<String> keys = new ArrayList<>();
        keys.add("люблю");
        keys.add("папу");
        keys.add("пофиг");
        keys.add("на хер");

        filesSelect.selectFiles("B:/1", "B:/1/2", keys);
    }
}
