package ru.progwards.java2.lessons.builders;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class ReaderMetaInf {


    public String readMetaInf() {
        String result = "";
        InputStream is = null;
        URL url = null;
        String strPathJar = this.findStrPathJar();
        try {
            url = new URL("jar:file:" + strPathJar + "!/META-INF/MANIFEST.MF");//"jar:file:/absolute/location/of/yourJar.jar!/1.txt"
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            is = url.openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            while (true) {
                if (!reader.ready()) break;
                result = result + reader.readLine() + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String findStrPathJar() {
        String result = "";
        File dir = new File(".\\target");
        File[] arrFiles = dir.listFiles();
        for (File file : arrFiles) {
            if (file.getName().contains(".jar")) {
                result = file.toString();
                break;
            }
        }
        return result;
    }

}
