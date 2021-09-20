package bll;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Aceasta clasa contine o compozitie de produse
 *
 */
public class CompositeProduct extends MenuItem {

    //    private String titlu;
//    private float raiting;
//    private int calorii;
//    private int proteine;
//    private int grasimi;
//    private int sodiu;
//    private float pret;
    private String numeComposite;
    private ArrayList<MenuItem> list;

    public CompositeProduct() {
        super();
        this.list = new ArrayList<>();
//        citireCSV();
//        this.list.add(this);
    }

    public CompositeProduct(String title, ArrayList<MenuItem> list) {
        this.numeComposite = title;
        this.list = list;
    }

//    public CompositeProduct(String titlu, float raiting, int calorii, int proteine, int grasimi, int sodiu, float pret) {
//        super(titlu, raiting, calorii, proteine, grasimi, sodiu, pret);
//        this.list = new ArrayList<>();
//        //citireCSV();
////        this.list.add(this);
//    }

    //    private void citireCSV() {
//        Scanner fRead = null;
//        try {
//            fRead = new Scanner(new File("Produse.txt"));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        while (fRead.hasNextLine()) {
//
//            String[] values = new String[7];
//            values[0] = fRead.nextLine();
//            values[1] = fRead.nextLine();
//            values[2] = fRead.nextLine();
//            values[3] = fRead.nextLine();
//            values[4] = fRead.nextLine();
//            values[5] = fRead.nextLine();
//            values[6] = fRead.nextLine();
////            System.out.println(values[0]+" "+values[1]+" "+values[2]+" "+values[3]+" "+values[4]+" "+values[5]+" "+values[6]);
//            BaseProduct m = new BaseProduct(values[0], Float.valueOf(Float.parseFloat(values[1])), Integer.valueOf(Integer.parseInt(values[2])), Integer.valueOf(Integer.parseInt(values[3])), Integer.valueOf(Integer.parseInt(values[4])), Integer.valueOf(Integer.parseInt(values[5])), Float.valueOf(Float.parseFloat(values[6])));
//            list.add(m);
//        }
//        fRead.close();
//    }
    public float computeRaiting() {
        float t = 0;
        Iterator<MenuItem> menuItemIterator = list.iterator();
        while (menuItemIterator.hasNext()) {
            MenuItem menuItem = menuItemIterator.next();
            t += menuItem.getRaiting();
        }
        float medie = t / list.size();
        super.setRaiting(medie);
        return medie;
    }

    public int computeCalorii() {
        int t = 0;
        Iterator<MenuItem> menuItemIterator = list.iterator();
        while (menuItemIterator.hasNext()) {
            MenuItem menuItem = menuItemIterator.next();
            t += menuItem.getCalorii();
        }
        super.setCalorii(t);
        return t;
    }

    public int computeProteine() {
        int t = 0;
        Iterator<MenuItem> menuItemIterator = list.iterator();
        while (menuItemIterator.hasNext()) {
            MenuItem menuItem = menuItemIterator.next();
            t += menuItem.getProteine();
        }
        super.setProteine(t);
        return t;
    }

    public int computeGrasimi() {
        int t = 0;
        Iterator<MenuItem> menuItemIterator = list.iterator();
        while (menuItemIterator.hasNext()) {
            MenuItem menuItem = menuItemIterator.next();
            t += menuItem.getGrasimi();
        }
        super.setGrasimi(t);
        return t;
    }

    public int computeSodiu() {
        int t = 0;
        Iterator<MenuItem> menuItemIterator = list.iterator();
        while (menuItemIterator.hasNext()) {
            MenuItem menuItem = menuItemIterator.next();
            t += menuItem.getSodiu();
        }
        super.setSodiu(t);
        return t;
    }

    public float computePrice() {
        float t = 0;
        Iterator<MenuItem> menuItemIterator = list.iterator();
        while (menuItemIterator.hasNext()) {
            MenuItem menuItem = menuItemIterator.next();
            t += menuItem.getPret();
        }
        super.setPret(t);
        return t;
    }


}
