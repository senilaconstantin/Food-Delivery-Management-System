package bll;

import dataLayer.FileWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * In aceasta clasa se implementeaza metodele din IDeliveryServiceProcessing
 */
public class DeliveryService extends Observable implements IDeliveryServiceProcessing, Serializable {
    private HashMap<Order, ArrayList<MenuItem>> hmap;
    private ArrayList<MenuItem> menu;
    private FileWriter fw = new FileWriter();

    public DeliveryService() {
        this.hmap = new HashMap<Order, ArrayList<MenuItem>>();
        this.menu = new ArrayList<MenuItem>();
        citireCSV();
    }

    private void citireCSV() {
        Scanner fRead = null;
        try {
            fRead = new Scanner(new File("Produse.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (fRead.hasNextLine()) {
            String[] values = new String[7];
            values[0] = fRead.nextLine();
            values[1] = fRead.nextLine();
            values[2] = fRead.nextLine();
            values[3] = fRead.nextLine();
            values[4] = fRead.nextLine();
            values[5] = fRead.nextLine();
            values[6] = fRead.nextLine();

            MenuItem m = new MenuItem(values[0], Float.valueOf(Float.parseFloat(values[1])), Integer.valueOf(Integer.parseInt(values[2])), Integer.valueOf(Integer.parseInt(values[3])), Integer.valueOf(Integer.parseInt(values[4])), Integer.valueOf(Integer.parseInt(values[5])), Float.valueOf(Float.parseFloat(values[6])));
            menu.add(m);
        }
        fRead.close();
    }

    public ArrayList<MenuItem> getMenu() {
        return menu;
    }

    /**
     * cauta
     * @param nume
     * @return
     */
    public MenuItem search(String nume) {
        for (MenuItem m : menu) {
            if (nume.equals(m.getTitlu()))
                return m;
        }
        return null;
    }

    /**
     * adauga
     * @param produs
     */
    @Override
    public void addProdus(MenuItem produs) {
        menu.add(produs);
        fw.scriereF(menu, "Produse.txt");
    }

    /**
     * sterge
     * @param nume
     */
    @Override
    public void deleteProdus(String nume) {
        MenuItem q = null;
        for (MenuItem m : menu)
            if (nume.equals(m.getTitlu()))
                q = m;
        menu.remove(q);
        fw.scriereF(menu, "Produse.txt");
    }

    /**
     * face update la produs
     * @param nume
     * @param pret
     */
    @Override
    public void updateProdus(String nume, float pret) {
        MenuItem q = null;
        for (int i = 0; i < menu.size(); i++) {
            if (nume.equals(menu.get(i).getTitlu())) {
                q = menu.get(i);
                q.setPret(pret);
                menu.set(i, q);
            }
        }
        fw.scriereF(menu, "Produse.txt");
    }

    /**
     * creaza un produs composit
     * @param title
     * @param produs1
     * @param produs2
     */
    @Override
    public void createCompositeProduct(String title, String produs1, String produs2) {
        MenuItem m = search(produs1);
        MenuItem m1 = search(produs2);
        CompositeProduct cmp = null;
        if (m != null && m1 != null) {
            ArrayList<MenuItem> list = new ArrayList<>();
            list.add(m);
            list.add(m1);
            cmp = new CompositeProduct(title, list);
            MenuItem menn = new MenuItem(title, cmp.computeRaiting(), cmp.computeCalorii(), cmp.computeProteine(), cmp.computeGrasimi(), cmp.computeSodiu(), cmp.computePrice());
            menu.add(menn);
            fw.scriereF(menu, "Produse.txt");
        }
    }

    /**
     * genereaza raport 1
     * @param o1
     * @param o2
     * @return
     * @throws IOException
     */
    @Override
    public ArrayList<Order> generateTimeRaport1(int o1, int o2) throws IOException {
        Order o = new Order();
        ArrayList<Order> ord;
        ArrayList<Order> cmz = o.getOrder();
        ord = (ArrayList<Order>) cmz.stream().filter(cmd -> cmd.getOra() >= o1 && cmd.getOra() < o2).collect(Collectors.toList());

        return ord;
    }

    /**
     * genereaza raport 2
     * @param nr
     * @return
     * @throws IOException
     */
    @Override
    public ArrayList<MenuItem> generateRaport2(int nr) throws IOException {
        Order o = new Order();
        ArrayList<Order> cmz = o.getOrder();

        ArrayList<MenuItem> allProducts = new ArrayList<>();
        //cmz.stream().map(order -> order.getComanda()).forEach(product -> allProducts.addAll(product));
        cmz.stream().map(Order::getComanda).forEach(allProducts::addAll);


        Map<MenuItem, Long> map = allProducts.stream().collect(Collectors.groupingBy(p -> p,
                Collectors.counting()));

        ArrayList<MenuItem> produseFinale = (ArrayList<MenuItem>) map.keySet().stream().filter(menuitem -> map.get(menuitem) >= nr).collect(Collectors.toList());

        //ArrayList<Order> comenzi=cmz.forEach(comamda-> comamda.getComanda().forEach(produs->produs.getTitlu().equals()));

        return produseFinale;
    }

    /**
     * genereaza raport 3
     * @param nrComenzi
     * @param suma
     * @return
     * @throws IOException
     */
    @Override
    public ArrayList<String> generateRaport3Clienti(int nrComenzi, int suma) throws IOException {
        Order o = new Order();
        ArrayList<Order> cmz = o.getOrder();

        ArrayList<Order> comenziRamaseSuma= (ArrayList<Order>) cmz.stream().filter(cmd -> cmd.getPretTotal() >= suma).collect(Collectors.toList());

        Map<String, Long> map = comenziRamaseSuma.stream().collect(Collectors.groupingBy(p -> p.getNumeClient(),
                Collectors.counting()));


        ArrayList<String> allClienti = (ArrayList<String>) map.keySet().stream().filter(numeClient -> map.get(numeClient) >= nrComenzi).collect(Collectors.toList());


        return allClienti;
    }

    /**
     * genereaza raport 4
     * @param nrZi
     * @return
     * @throws IOException
     */
    @Override
    public ArrayList<Order> generareRaport4(int nrZi) throws IOException {
        Order o = new Order();
        ArrayList<Order> cmz = o.getOrder();

        ArrayList<Order> listOrder= (ArrayList<Order>) cmz.stream().filter(order->order.getZi()==nrZi).collect(Collectors.toList());

        return listOrder;
    }

    @Override
    public float computeOrderPret(ArrayList<MenuItem> comanda) {
        float pret = 0;
        for (MenuItem mn : comanda)
            pret += mn.getPret();
        return pret;
    }

    /**
     * cauta dupa nume
     * @param titlu
     * @return
     */
    @Override
    public ArrayList<MenuItem> searchTitlu(String titlu) {
        ArrayList<MenuItem> prd;
        prd = (ArrayList<MenuItem>) menu.stream().filter(p -> p.getTitlu().contains(titlu) == true).collect(Collectors.toList());
        // System.out.println(prd.size());
        return prd;
    }

    /**
     * cauta dupa titlu si calorii
     * @param titlu
     * @param calorii
     * @return
     */
    @Override
    public ArrayList<MenuItem> searchTitluCalorii(String titlu, int calorii) {
        ArrayList<MenuItem> prd;
        prd = (ArrayList<MenuItem>) menu.stream().filter(p -> p.getTitlu().contains(titlu) == true && p.getCalorii() == calorii).collect(Collectors.toList());
        return prd;
    }


}
