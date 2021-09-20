package start;

import bll.BaseProduct;
import bll.MenuItem;
import presentantionlayer.*;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

/**
 * clasa de inceput care porneste programul
 */
public class MainClass {

    private static boolean exista(ArrayList<MenuItem> list, String nume) {
        for (MenuItem m : list) {
            if (m.getTitlu().equals(nume))
                return true;
        }
        return false;
    }

    private static ArrayList<MenuItem> citireCSV() {
        ArrayList<MenuItem> list = new ArrayList<>();
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader("products.csv"));
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (exista(list, values[0]) == false) {
                    //System.out.println(values[0] + " " + values[1]);
                    MenuItem m = new MenuItem(values[0], Float.valueOf(Float.parseFloat(values[1])), Integer.valueOf(Integer.parseInt(values[2])), Integer.valueOf(Integer.parseInt(values[3])), Integer.valueOf(Integer.parseInt(values[4])), Integer.valueOf(Integer.parseInt(values[5])), Float.valueOf(Float.parseFloat(values[6])));
                    list.add(m);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    private static void scriereF(ArrayList<MenuItem> list) {
        FileWriter fw = null;
        try {
            fw = new FileWriter("Produse.txt");
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

    public static void main(String[] args) throws IOException {
        ArrayList<MenuItem> list = citireCSV();
        File fw = new File("Produse.txt");
        fw.createNewFile();
        scriereF(list);


//        FileOutputStream writer = new FileOutputStream("Comenzi.txt");
////        writer.write(("/**/").getBytes());
//        writer.close();
//
//        FileOutputStream file = new FileOutputStream ("Comanda.txt");
//        ObjectOutputStream out = new ObjectOutputStream(file);
//        out.close();
//        file.close();




        PagPrincipala pg = new PagPrincipala();
        Controller x = new Controller(pg);

        pg.setVisible(true);
    }
}
