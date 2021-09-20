package presentantionlayer;

import bll.DeliveryService;
import bll.MenuItem;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * clasa corespunzatoare interfetei administratorului
 */
public class Administrator extends JPanel {
    private int k = 0;
    private DeliveryService dvs = new DeliveryService();
    private JButton back;
    private JButton btnNewuProd;
    private JButton btnEdit;
    private JButton btnDelete;
    private JButton btnAdauga;

    private JTable table;
    private DefaultTableModel model;

    private JTextField txtNumeProd;
    private JTextField txtRaiting;
    private JTextField txtCalorii;
    private JTextField txtProteine;
    private JTextField txtPret;
    private JTextField txtGrarsimi;
    private JTextField txtSodiu;
    private JTextField txtNumeComposite;
    private JTextField txtProd1;
    private JTextField txtProd2;

    public Administrator() {
        this.setLayout(null);
        this.setBounds(0, 0, 860, 568);
        back = new JButton("Back");
        back.setBounds(5, 5, 65, 25);

        dProd();
        btnAdmn();
        lblAdm();
        txtAdm();
        actionBtn();
        compositeP();
        gebRP();

        this.add(back);
        this.revalidate();
        this.repaint();
    }

    public void gebRP() {
        JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[]{"Selectati", "Raport 1", "Raport 2", "Raport 3", "Raport 4"}));
        comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        comboBox.setForeground(Color.BLACK);
        comboBox.setBounds(338, 409, 155, 32);
        this.add(comboBox);

        JButton btnNewButton = new JButton("Generare");
        btnNewButton.setForeground(Color.BLACK);
        btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btnNewButton.setBounds(529, 409, 113, 32);
        this.add(btnNewButton);

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String st = (String) comboBox.getSelectedItem();
                if (st.equals("Raport 1")) {
                    new Rap1();
                } else if (st.equals("Raport 2")) {
                    new Rap2();
                } else if (st.equals("Raport 3")) {
                    new Rap3();
                } else if (st.equals("Raport 4")) {
                    new Rap4();
                }


            }
        });
    }

    public void addListener(ActionListener e) {
        back.addActionListener(e);
    }

    private void tabel(int nr) {
        model = new DefaultTableModel();
        Object[] column = {"Title", "Raiting", "Calories", "Protein", "Fat", "Sodium", "Price"};
        Object[] row = new Object[7];

        model.setColumnIdentifiers(column);
        table.setModel(model);

        ArrayList<MenuItem> list = null;
        DeliveryService prd = new DeliveryService();
        list = prd.getMenu();
        list.remove(list.size() - 1);

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

    private void btnAdmn() {
        btnNewuProd = new JButton("Add New");
        btnNewuProd.setFont(new Font("Times New Roman", Font.BOLD, 12));
        btnNewuProd.setForeground(Color.BLACK);
        btnNewuProd.setBounds(10, 201, 91, 21);
        this.add(btnNewuProd);

        btnEdit = new JButton("Edit");
        btnEdit.setForeground(Color.BLACK);
        btnEdit.setFont(new Font("Times New Roman", Font.BOLD, 12));
        btnEdit.setBounds(153, 201, 91, 21);
        this.add(btnEdit);

        btnDelete = new JButton("Delete");
        btnDelete.setForeground(Color.BLACK);
        btnDelete.setFont(new Font("Times New Roman", Font.BOLD, 12));
        btnDelete.setBounds(86, 232, 85, 21);
        this.add(btnDelete);

        btnAdauga = new JButton("Adauga");
        btnAdauga.setForeground(Color.BLACK);
        btnAdauga.setFont(new Font("Times New Roman", Font.BOLD, 12));
        btnAdauga.setBounds(75, 421, 85, 21);
        this.add(btnAdauga);
    }

    private void actionBtn() {
        btnNewuProd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuItem prd = new MenuItem(txtNumeProd.getText(), Float.valueOf(Float.parseFloat(txtRaiting.getText())),
                        Integer.valueOf(Integer.parseInt(txtCalorii.getText())), Integer.valueOf(Integer.parseInt(txtProteine.getText())),
                        Integer.valueOf(Integer.parseInt(txtGrarsimi.getText())), Integer.valueOf(Integer.parseInt(txtSodiu.getText())),
                        Float.valueOf(Float.parseFloat(txtPret.getText())));
                dvs.addProdus(prd);
                tabel(0);
            }
        });
        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dvs.updateProdus(txtNumeProd.getText(), Float.valueOf((Float.parseFloat(txtPret.getText()))));
                tabel(0);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dvs.deleteProdus(txtNumeProd.getText());
                tabel(0);
            }
        });
        btnAdauga.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dvs.createCompositeProduct(txtNumeComposite.getText(), txtProd1.getText(), txtProd2.getText());
//                System.out.println("1");
                tabel(0);
//                txtNumeComposite.setText("");
//                txtProd1.setText("");
//                txtProd2.setText("");
            }
        });
    }

    private void lblAdm() {
        JLabel lblNumeProd = new JLabel("Nume Produs");
        lblNumeProd.setHorizontalAlignment(SwingConstants.CENTER);
        lblNumeProd.setForeground(Color.BLACK);
        lblNumeProd.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblNumeProd.setBounds(10, 27, 234, 23);
        this.add(lblNumeProd);

        JLabel lblRaiting = new JLabel("Raiting");
        lblRaiting.setHorizontalAlignment(SwingConstants.CENTER);
        lblRaiting.setForeground(Color.BLACK);
        lblRaiting.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblRaiting.setBounds(10, 81, 70, 23);
        this.add(lblRaiting);

        JLabel lblCalorii = new JLabel("Calorii");
        lblCalorii.setHorizontalAlignment(SwingConstants.CENTER);
        lblCalorii.setForeground(Color.BLACK);
        lblCalorii.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblCalorii.setBounds(90, 81, 70, 23);
        this.add(lblCalorii);

        JLabel lblProteine = new JLabel("Proteine");
        lblProteine.setHorizontalAlignment(SwingConstants.CENTER);
        lblProteine.setForeground(Color.BLACK);
        lblProteine.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblProteine.setBounds(174, 81, 70, 23);
        this.add(lblProteine);

        JLabel lblSodiu = new JLabel("Sodiu");
        lblSodiu.setHorizontalAlignment(SwingConstants.CENTER);
        lblSodiu.setForeground(Color.BLACK);
        lblSodiu.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblSodiu.setBounds(90, 141, 70, 23);
        this.add(lblSodiu);

        JLabel lblPret = new JLabel("Pret");
        lblPret.setHorizontalAlignment(SwingConstants.CENTER);
        lblPret.setForeground(Color.BLACK);
        lblPret.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblPret.setBounds(174, 141, 70, 23);
        this.add(lblPret);

        JLabel lblGrasimi = new JLabel("Grasimi");
        lblGrasimi.setHorizontalAlignment(SwingConstants.CENTER);
        lblGrasimi.setForeground(Color.BLACK);
        lblGrasimi.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblGrasimi.setBounds(10, 141, 70, 23);
        this.add(lblGrasimi);
    }

    private void txtAdm() {
        txtNumeProd = new JTextField();
        txtNumeProd.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        txtNumeProd.setBounds(10, 54, 234, 23);
        this.add(txtNumeProd);
        txtNumeProd.setColumns(10);

        txtRaiting = new JTextField();
        txtRaiting.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        txtRaiting.setColumns(10);
        txtRaiting.setBounds(10, 108, 70, 23);
        this.add(txtRaiting);

        txtCalorii = new JTextField();
        txtCalorii.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        txtCalorii.setColumns(10);
        txtCalorii.setBounds(90, 108, 70, 23);
        this.add(txtCalorii);

        txtProteine = new JTextField();
        txtProteine.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        txtProteine.setColumns(10);
        txtProteine.setBounds(174, 108, 70, 23);
        this.add(txtProteine);

        txtPret = new JTextField();
        txtPret.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        txtPret.setColumns(10);
        txtPret.setBounds(174, 168, 70, 23);
        this.add(txtPret);

        txtGrarsimi = new JTextField();
        txtGrarsimi.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        txtGrarsimi.setColumns(10);
        txtGrarsimi.setBounds(10, 168, 70, 23);
        this.add(txtGrarsimi);

        txtSodiu = new JTextField();
        txtSodiu.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        txtSodiu.setColumns(10);
        txtSodiu.setBounds(90, 168, 70, 23);
        this.add(txtSodiu);
    }

    private void selectTable(ListSelectionEvent listSelectionEvent) {
        if (listSelectionEvent.getValueIsAdjusting())
            return;

        DefaultListSelectionModel t = (DefaultListSelectionModel) listSelectionEvent.getSource();
        int selectI = t.getAnchorSelectionIndex();
        if (selectI == -1)
            return;

        txtNumeProd.setText(table.getValueAt(selectI, 0).toString());
        txtRaiting.setText(table.getValueAt(selectI, 1).toString());
        txtCalorii.setText(table.getValueAt(selectI, 2).toString());
        txtProteine.setText(table.getValueAt(selectI, 3).toString());
        txtGrarsimi.setText(table.getValueAt(selectI, 4).toString());
        txtSodiu.setText(table.getValueAt(selectI, 5).toString());
        txtPret.setText(table.getValueAt(selectI, 6).toString());
        if (k % 2 == 0) {
            k++;
            txtProd1.setText(table.getValueAt(selectI, 0).toString());
        } else {
            k++;
            txtProd2.setText(table.getValueAt(selectI, 0).toString());
        }
    }

    private void compositeP() {
        txtNumeComposite = new JTextField();
        txtNumeComposite.setForeground(Color.BLACK);
        txtNumeComposite.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        txtNumeComposite.setColumns(10);
        txtNumeComposite.setBounds(10, 290, 234, 23);
        this.add(txtNumeComposite);

        JLabel lblNumeComposite = new JLabel("Nume Composite");
        lblNumeComposite.setHorizontalAlignment(SwingConstants.CENTER);
        lblNumeComposite.setForeground(Color.BLACK);
        lblNumeComposite.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblNumeComposite.setBounds(10, 263, 234, 23);
        this.add(lblNumeComposite);

        JLabel lblProdus = new JLabel("Produs1");
        lblProdus.setHorizontalAlignment(SwingConstants.CENTER);
        lblProdus.setForeground(Color.BLACK);
        lblProdus.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblProdus.setBounds(10, 312, 234, 23);
        this.add(lblProdus);

        txtProd1 = new JTextField();
        txtProd1.setForeground(Color.BLACK);
        txtProd1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        txtProd1.setColumns(10);
        txtProd1.setBounds(10, 339, 234, 23);
        this.add(txtProd1);

        JLabel lblProdus_1 = new JLabel("Produs2");
        lblProdus_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblProdus_1.setForeground(Color.BLACK);
        lblProdus_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblProdus_1.setBounds(10, 361, 234, 23);
        this.add(lblProdus_1);

        txtProd2 = new JTextField();
        txtProd2.setForeground(Color.BLACK);
        txtProd2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        txtProd2.setColumns(10);
        txtProd2.setBounds(10, 388, 234, 23);
        this.add(txtProd2);
    }

    private void dProd() {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(254, 26, 596, 365);
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
    }
}

