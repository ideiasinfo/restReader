package com.app.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static com.app.utils.Constants.RESOURCES_PATH;

public class FileWriting {

    /**
     * createFolder, create the folder to create the files later
     *
     * @param folder: Destiny file
     */
    public static void createFolderInDisk(String folder){
        new File(RESOURCES_PATH+folder).mkdir();
    }

    /**
     * Writes on the file named as the first parameter the String which gets as second parameter.
     *
     * @param fileName: Destiny file
     * @param message: String to write on the destiny file
     */
    public static void writeUsingFileWriter(String fileName, String message) {
        FileWriter w = null;
        try {
            w = new FileWriter(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(w);
        PrintWriter wr = new PrintWriter(bw);

        try {
            wr.write(message);
            wr.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
