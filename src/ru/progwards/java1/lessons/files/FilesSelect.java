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
                    List<String> contentFile;
                    if (pathMatcher.matches(file)) contentFile = Files.readAllLines(file);
                    else return FileVisitResult.CONTINUE;
                    List<String> listNamesDirForCopy = getNameOfDirForCopyFile(contentFile, keys);
                    if (listNamesDirForCopy.isEmpty()) return FileVisitResult.CONTINUE;
                    createDirForCopyAndCopyFile(listNamesDirForCopy,outFolder, file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }
            });
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private List<String> getNameOfDirForCopyFile(List<String> contentFile, List<String> keys) {
        List<String> list = new ArrayList();
        for (int i = 0; i < contentFile.size(); i++){
            String str = contentFile.get(i);
            for (int j = 0; j < keys.size(); j++) {
                if (str.contains(keys.get(j))) list.add(keys.get(j));
            }
        }
        return list;
    }

    private void createDirForCopyAndCopyFile(List<String> namesDirForCopy,String outFolder, Path file) throws IOException {
        for (int i = 0; i < namesDirForCopy.size(); i++) {
            Path pathNewDir = Paths.get(outFolder + "/" + namesDirForCopy.get(i));
            if (!Files.exists(Paths.get(outFolder + "/" + namesDirForCopy.get(i)))) {
                Path dirForCopy = Files.createDirectory(pathNewDir);
                Files.copy(file, dirForCopy.resolve(file.getFileName()));
            } else{if (Files.notExists(pathNewDir.resolve(file.getFileName())))
                Files.copy(file, pathNewDir.resolve(file.getFileName()));}
        }
    }

    public static void main(String[] args) throws IOException {
       /* dir1/file1.txt(111), dir1/file2.txt(111), dir1/file3.txt(123), dir2/dir3/file1.txt(111),
       dir2/dir3/file3.txt(123), dir2/file1.txt(222), dir2/file2.txt(111), file2.txt(111)
        В каталоге outFolder обнаружена структура файлов:
        111/file1.txt, 111/file2.txt, 123/file3.txt
        Ожидалось:
        111/file1.txt, 111/file2.txt, 123/file3.txt, 222/file1.txt  */

        FilesSelect filesSelect = new FilesSelect();

        List<String> keys = new ArrayList<>();
        keys.add("111");
        keys.add("222");
        keys.add("333");
        keys.add("123");

        filesSelect.selectFiles("B:/1", "B:/2", keys);
    }
}
