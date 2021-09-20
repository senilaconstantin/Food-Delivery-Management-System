package presentantionlayer;

import bll.DeliveryService;
import bll.Order;
import bll.MenuItem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
/**
 * Pentru interfata grafica raport2
 */
public class Rap2 {

    private JFrame frame;
    private JTextField txtNumarProd;
    private JTextArea textArea;
    private JButton btnGenereaza;
    private DeliveryService dvs = new DeliveryService();

    public Rap2() {
        initialize();
        initialize1();
        action();
    }

    private void action() {
        btnGenereaza.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int a = Integer.parseInt(txtNumarProd.getText());

                ArrayList<MenuItem> prod = null;
                try {
                    prod = dvs.generateRaport2(a);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                String s = new String();
                if(prod==null)
                    prod=new ArrayList<>();
                for (MenuItem o : prod) {
                    s += o.getTitlu()+"\n";
                }
                textArea.setText("");
                textArea.append(s);
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

        JLabel lblOra2 = new JLabel("Numar Produse");
        lblOra2.setHorizontalAlignment(SwingConstants.CENTER);
        lblOra2.setForeground(Color.BLACK);
        lblOra2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        lblOra2.setBounds(10, 200, 148, 27);
        frame.getContentPane().add(lblOra2);

        txtNumarProd = new JTextField();
        txtNumarProd.setForeground(Color.BLACK);
        txtNumarProd.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        txtNumarProd.setColumns(10);
        txtNumarProd.setBounds(10, 223, 148, 19);
        frame.getContentPane().add(txtNumarProd);

        btnGenereaza = new JButton("Genereaza");
        btnGenereaza.setForeground(Color.BLACK);
        btnGenereaza.setFont(new Font("Times New Roman", Font.BOLD, 12));
        btnGenereaza.setBounds(37, 272, 92, 27);
        frame.getContentPane().add(btnGenereaza);
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Raport2");
        frame.setBounds(100, 100, 861, 524);
        frame.getContentPane().setLayout(null);
//        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
    }

}

