package com.app.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static com.app.utils.Constants.RESOURCES_PATH;

public class FileWriting {

    public static void createFolder(String folder){
        File file = new File(RESOURCES_PATH+folder);
        file.mkdir();
    }

    /**
     * Use FileWriter when number of write operations are less
     * @param data
     */
    public static void writeUsingFileWriter(String fileName, String data) {
        File file = new File(fileName);
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            fr.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
