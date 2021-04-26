package com.mycompany.app.utils;

import java.io.FileWriter;
import java.io.IOException;

public class IOHandler {

    public static void writeToFile(String path, String content) {
        FileWriter fw;

        try {
            fw = new FileWriter(path, true);
            fw.append(content + "\n");
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
