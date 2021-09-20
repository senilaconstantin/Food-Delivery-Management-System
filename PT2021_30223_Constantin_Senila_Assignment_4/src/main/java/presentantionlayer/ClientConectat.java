package presentantionlayer;

import bll.*;
import bll.MenuItem;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * se poate efectua comanda de catre client
 */
public class ClientConectat extends JPanel {
    private JButton back;
    private PagPrincipala pG;
    private Clienti client;

    private JTable table;
    private JTextField txtxNume;
    private DefaultTableModel model;
    private DefaultTableModel modelC;
    private JTextField txtNumeProd;
    private JTable tableC;
    private JButton btnAdauga;
    private JButton btnComanda;
    private DeliveryService dvs = new DeliveryService();
    private ArrayList<MenuItem> comanda = new ArrayList<>();
    private JTextField txtNumeProdCautat;
    private JTextField txtNumarCalorii;
    private JButton btnCauta2;

    public ClientConectat(PagPrincipala pG, Clienti client) {
        this.pG = pG;
        this.client = client;

        this.setLayout(null);
        this.setBounds(0, 0, 860, 568);

        back = new JButton("Back");
        back.setBounds(5, 5, 65, 25);

        actionBack();
        dProd();
        comandaClient();
        actionCmd();
        jLblCMD();
        cautaCrt2();
        jBtn();

        this.add(back);
        this.revalidate();
        this.repaint();
    }

    private void cautaCrt2(){
        JLabel lblNumeProdCautat = new JLabel("Nume Produs");
        lblNumeProdCautat.setHorizontalAlignment(SwingConstants.CENTER);
        lblNumeProdCautat.setForeground(Color.BLACK);
        lblNumeProdCautat.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblNumeProdCautat.setBounds(294, 310, 234, 23);
        this.add(lblNumeProdCautat);

        txtNumeProdCautat = new JTextField();
        txtNumeProdCautat.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        txtNumeProdCautat.setColumns(10);
        txtNumeProdCautat.setBounds(294, 333, 234, 23);
        this.add(txtNumeProdCautat);

        txtNumarCalorii = new JTextField();
        txtNumarCalorii.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        txtNumarCalorii.setColumns(10);
        txtNumarCalorii.setBounds(348, 383, 125, 23);
        this.add(txtNumarCalorii);

        JLabel lblNrCalorii = new JLabel("Numar Calorii");
        lblNrCalorii.setHorizontalAlignment(SwingConstants.CENTER);
        lblNrCalorii.setForeground(Color.BLACK);
        lblNrCalorii.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblNrCalorii.setBounds(294, 360, 234, 23);
        this.add(lblNrCalorii);

        btnCauta2 = new JButton("Cauta");
        btnCauta2.setForeground(Color.BLACK);
        btnCauta2.setFont(new Font("Times New Roman", Font.ITALIC, 14));
        btnCauta2.setBounds(371, 416, 85, 21);
        this.add(btnCauta2);
    }

    private void jLblCMD() {
        JLabel lblProd = new JLabel("Produse");
        lblProd.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
        lblProd.setForeground(Color.BLACK);
        lblProd.setHorizontalAlignment(SwingConstants.CENTER);
        lblProd.setBounds(256, 0, 594, 23);
        this.add(lblProd);

        JLabel lblNumeProd = new JLabel("Nume Produs");
        lblNumeProd.setHorizontalAlignment(SwingConstants.CENTER);
        lblNumeProd.setForeground(Color.BLACK);
        lblNumeProd.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblNumeProd.setBounds(10, 27, 234, 23);
        this.add(lblNumeProd);
    }

    private void actionCmd() {
        btnAdauga.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                comanda.add(dvs.search(txtNumeProd.getText()));
                tableComanda();
                txtNumeProd.setText("");
            }
        });

        btnComanda.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Date date =new Date();

                Order cmd = new Order(client.getNume(), comanda, date, dvs.computeOrderPret(comanda));

                comanda = new ArrayList<>();

                ArrayList<Order> lst=null;
                try {
                    lst=cmd.getOrder();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                //System.out.println(lst);
                if(lst==null)
                    lst=new ArrayList<>();
                lst.add(cmd);
                try {
                    cmd.scriereOrder(lst);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                tableComanda();
                showAlert("Comanda efectuata. Va multumim!");
            }
        });
    }

    private void tableComanda() {
        modelC = new DefaultTableModel();
        Object[] column = {"Comanda"};
        Object[] row = new Object[2];

        modelC.setColumnIdentifiers(column);
        tableC.setModel(modelC);

        for (MenuItem mn : comanda) {
            row[0] = mn.getTitlu();
            modelC.addRow(row);
        }
        row[0] = "Pretul total: " + dvs.computeOrderPret(comanda) + " Ron";
        modelC.addRow(row);
    }

    private void comandaClient() {
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(10, 123, 234, 200);
        this.add(scrollPane_1);

        tableC = new JTable();
        tableC.setForeground(Color.BLACK);
        tableC.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        scrollPane_1.setViewportView(tableC);
        tableComanda();

        txtNumeProd = new JTextField();
        txtNumeProd.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        txtNumeProd.setBounds(10, 54, 234, 23);
        this.add(txtNumeProd);
        txtNumeProd.setColumns(10);

        btnAdauga = new JButton("Adauga");
        btnAdauga.setFont(new Font("Times New Roman", Font.ITALIC, 14));
        btnAdauga.setForeground(Color.BLACK);
        btnAdauga.setBounds(85, 87, 85, 21);
        this.add(btnAdauga);

        btnComanda = new JButton("Comanda");
        btnComanda.setForeground(Color.BLACK);
        btnComanda.setFont(new Font("Times New Roman", Font.ITALIC, 14));
        btnComanda.setBounds(73, 333, 97, 21);
        this.add(btnComanda);
    }

    private void actionBack() {
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Clyent c = null;
                try {
                    c = new Clyent(pG);
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
                pG.adaugaPanel(c);
            }
        });
    }

    private void tabel(int nr) {
        model = new DefaultTableModel();
        Object[] column = {"Title", "Raiting", "Calories", "Protein", "Fat", "Sodium", "Price"};
        Object[] row = new Object[7];

        model.setColumnIdentifiers(column);
        table.setModel(model);

        ArrayList<MenuItem> list = null;
        if (nr == 0) {
            DeliveryService prd = new DeliveryService();
            list = prd.getMenu();
            list.remove(list.size() - 1);
        } else if(nr==1) {
            list = dvs.searchTitlu(txtxNume.getText());
        }else if(nr==2){
            list=dvs.searchTitluCalorii(txtNumeProdCautat.getText(),Integer.valueOf(Integer.parseInt(txtNumarCalorii.getText())));
        }
        for (MenuItem mn : list) {
            row[0] = mn.getTitlu();
            row[1] = mn.getRaiting();
            row[2] = mn.getCalorii();
            row[3] = mn.getProteine();
            row[4] = mn.getGrasimi();
            row[5] = mn.getSodiu();
            row[6] = mn.getPret();
            model.addRow(row);
        }
    }

    private void jBtn() {
        JButton btnViewAll = new JButton("View all");
        btnViewAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tabel(0);
            }
        });
        btnViewAll.setFont(new Font("Times New Roman", Font.BOLD, 12));
        btnViewAll.setForeground(Color.BLACK);
        btnViewAll.setBounds(711, 317, 85, 21);
        this.add(btnViewAll);

        JButton btnViewNume = new JButton("View ");
        btnViewNume.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tabel(1);
            }
        });
        btnViewNume.setForeground(Color.BLACK);
        btnViewNume.setFont(new Font("Times New Roman", Font.BOLD, 12));
        btnViewNume.setBounds(711, 417, 85, 21);
        this.add(btnViewNume);

        btnCauta2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tabel(2);
            }
        });
    }

    private void dProd() {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(254, 26, 596, 263);
        this.add(scrollPane);

        table = new JTable();
        table.setForeground(Color.BLACK);
        table.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        scrollPane.setViewportView(table);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel selectTb = table.getSelectionModel();
        selectTb.addListSelectionListener(this::selectTable);

        tabel(0);

        JLabel lblProd = new JLabel("Produse");
        lblProd.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
        lblProd.setForeground(Color.BLACK);
        lblProd.setHorizontalAlignment(SwingConstants.CENTER);
        lblProd.setBounds(256, 0, 594, 23);
        this.add(lblProd);

        JLabel lblNume = new JLabel("Nume");
        lblNume.setHorizontalAlignment(SwingConstants.CENTER);
        lblNume.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        lblNume.setForeground(Color.BLACK);
        lblNume.setBounds(656, 363, 194, 21);
        this.add(lblNume);

        txtxNume = new JTextField();
        txtxNume.setBounds(656, 382, 192, 23);
        this.add(txtxNume);
        txtxNume.setColumns(10);
    }
    public static void showAlert(String msg) {
        JLabel allertMsg = new JLabel(msg);
        allertMsg.setFont(new Font("Tahoma", Font.PLAIN, 20));
        JOptionPane.showMessageDialog(null, allertMsg);
    }
    private void selectTable(ListSelectionEvent listSelectionEvent) {
        if (listSelectionEvent.getValueIsAdjusting())
            return;

        DefaultListSelectionModel t = (DefaultListSelectionModel) listSelectionEvent.getSource();
        int selectI = t.getAnchorSelectionIndex();
        if (selectI == -1)
            return;

        txtNumeProd.setText(table.getValueAt(selectI, 0).toString());
    }

}
