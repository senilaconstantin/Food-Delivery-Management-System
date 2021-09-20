package bll;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static java.nio.file.StandardOpenOption.APPEND;

/**
 *
 * aceasta clasa contine comenzile
 */
public class Order implements Serializable {
    @Serial
    private static final long serialVersion = 4430233860839576402L;
    private String numeClient;
    private ArrayList<MenuItem> comanda;
    private Date orederDate;
    private float pretTotal;

    public Order() {
    }

    public Order(String numeClient, ArrayList<MenuItem> comanda, Date orederDate, float pretTotal) {
        this.numeClient = numeClient;
        this.comanda = comanda;
        this.orederDate = orederDate;
        this.pretTotal = pretTotal;
        writeFile(toString());
    }

    public int getOra() {
        return orederDate.getHours();
    }

    public String getNumeClient() {
        return numeClient;
    }

    public float getPretTotal() {
        return pretTotal;
    }

    public int getZi(){
        return orederDate.getDay();
    }


    public ArrayList<Order> getOrder() throws IOException {
        ArrayList<Order> ord = null;
        File file1 = new File("Comanda.ser");
        if (file1.exists()) {
            FileInputStream file = new FileInputStream(file1);
            ObjectInputStream in = new ObjectInputStream(file);
            try {
                ord = (ArrayList<Order>) in.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            in.close();
            file.close();
        }
        if (ord == null)
            ord = new ArrayList<>();
        return ord;
    }

    public void scriereOrder(ArrayList<Order> ord) throws IOException {
        FileOutputStream file = new FileOutputStream("Comanda.ser");
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(ord);
        out.close();
        file.close();
    }

    public ArrayList<MenuItem> getComanda() {
        return comanda;
    }

    private void writeFile(String str) {
        OutputStream os = null;
        try {
            os = Files.newOutputStream(Paths.get("Comenzi.txt"), APPEND);

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        PrintWriter pr = new PrintWriter(os);
        pr.println(str);


        pr.close();
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", new Locale("en", "EN"));
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy", new Locale("en", "EN"));
        String str = "Comanda facuta de: " +
                numeClient + "\nLa data de:\n" + sdf2.format(orederDate) + "\nLa ora:\n" + sdf.format(orederDate) + "\n ";
        for (MenuItem mn : comanda) {
            str += "-" + mn.getTitlu() + ", Pret: " + mn.getPret() + " Ron\n  ";
        }
        str += "    Pretul total este: " + pretTotal + " Ron\n\n";
        return str;
    }
}
