package presentantionlayer;

import bll.DeliveryService;
import bll.Order;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
/**
 * Pentru interfata grafica Raport4
 */
public class Rap4 {

    private JFrame frame;
    private JTextArea textArea;
    private JComboBox comboBox;
    private JButton btnGenereaza;
    private DeliveryService dvs = new DeliveryService();

    public Rap4() {
        initialize();
        initialize1();
        actionBtn();

    }

    private void actionBtn() {
        btnGenereaza = new JButton("Genereaza");
        btnGenereaza.setForeground(Color.BLACK);
        btnGenereaza.setFont(new Font("Times New Roman", Font.BOLD, 12));
        btnGenereaza.setBounds(34, 237, 92, 27);
        frame.getContentPane().add(btnGenereaza);

        btnGenereaza.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int nrZi = comboBox.getSelectedIndex();
                if (nrZi == 0)
                    nrZi = -1;
                else if (nrZi == 7)
                    nrZi = 0;
                if (nrZi != -1) {
                    ArrayList<Order> lstO = null;
                    try {
                        lstO = dvs.generareRaport4(nrZi);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    String str = new String();
                    for (Order ord : lstO) {
                        str += ord.toString();
                    }
                    textArea.setText("");
//                    System.out.println();
                    textArea.append(str);
                }
            }
        });
    }

    private void initialize1() {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(163, 10, 674, 467);
        frame.getContentPane().add(scrollPane);

        textArea = new JTextArea();
        textArea.setEditable(false);
        scrollPane.setViewportView(textArea);

        JLabel lblNumar = new JLabel("Numar Zi");
        lblNumar.setHorizontalAlignment(SwingConstants.CENTER);
        lblNumar.setForeground(Color.BLACK);
        lblNumar.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        lblNumar.setBounds(10, 183, 148, 27);
        frame.getContentPane().add(lblNumar);

        comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[]{"Alegeti zi", "Luni", "Marti", "Miercuri", "Joi", "Vineri", "Sambata", "Duminica"}));
        comboBox.setForeground(Color.BLACK);
        comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        comboBox.setBounds(10, 200, 143, 27);
        frame.getContentPane().add(comboBox);
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Raport 4");
        frame.setBounds(100, 100, 861, 524);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.getContentPane().setLayout(null);
    }

}

