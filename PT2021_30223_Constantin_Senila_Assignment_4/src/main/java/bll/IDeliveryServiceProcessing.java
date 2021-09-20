package bll;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * aceasta interfata grafica este implementata de catre DeliveryService
 */
public interface IDeliveryServiceProcessing {
    //admin
    public void addProdus(MenuItem produs);
    public void deleteProdus(String nume);
    public void updateProdus(String nume, float pret);
    public void createCompositeProduct(String title,String produs1,String produs2);
    ArrayList<Order> generateTimeRaport1(int o1, int o2) throws IOException;
    ArrayList<MenuItem> generateRaport2(int nr) throws IOException;
    ArrayList<String> generateRaport3Clienti(int nrComenzi, int suma) throws IOException;
    ArrayList<Order> generareRaport4(int nrZi) throws IOException;

    //client
    public float computeOrderPret(ArrayList<MenuItem> comanda);
    public ArrayList<MenuItem> searchTitlu(String titlu);
    public ArrayList<MenuItem> searchTitluCalorii(String titlu, int calorii);


}
