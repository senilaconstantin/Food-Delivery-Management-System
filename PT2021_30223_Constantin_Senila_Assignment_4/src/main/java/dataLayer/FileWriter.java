package dataLayer;

import bll.MenuItem;

import java.io.IOException;
import java.util.ArrayList;

public class FileWriter {
    public FileWriter(){}

    /**
     * aici scrie in fisier
     * @param list
     * @param fileName
     */
    public static void scriereF(ArrayList<MenuItem> list, String fileName) {
        java.io.FileWriter fw = null;
        try {
            fw = new java.io.FileWriter(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(MenuItem mn:list){
            try {
                fw.write(mn.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
