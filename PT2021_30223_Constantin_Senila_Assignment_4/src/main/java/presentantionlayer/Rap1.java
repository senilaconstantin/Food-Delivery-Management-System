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
 * Pentru interfata grafica RAp1
 */
public class Rap1 {

    private JFrame frame;
    private JTextField txtOra1;
    private JTextField txtOra2;
    private JTextArea textArea;
    private JButton btnGenereaza;
    private DeliveryService dvs=new DeliveryService();

    private Order o;

    public Rap1() {
        initialize();
        initialize1();
        actoinBtn();
        o=new Order();
    }

    private void actoinBtn() {
        btnGenereaza = new JButton("Genereaza");
        btnGenereaza.setForeground(Color.BLACK);
        btnGenereaza.setFont(new Font("Times New Roman", Font.BOLD, 12));
        btnGenereaza.setBounds(37, 272, 92, 27);
        frame.getContentPane().add(btnGenereaza);

        btnGenereaza.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int a=Integer.parseInt(txtOra1.getText());
                int b=Integer.parseInt(txtOra2.getText());
                ArrayList<Order> ord=null;
                try {
                   ord=dvs.generateTimeRaport1(a,b);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                String s=new String();
                for(Order o:ord){
                    s+=o.toString();
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

        JLabel lblOra1 = new JLabel("Ora 1");
        lblOra1.setHorizontalAlignment(SwingConstants.CENTER);
        lblOra1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        lblOra1.setForeground(Color.BLACK);
        lblOra1.setBounds(10, 150, 148, 27);
        frame.getContentPane().add(lblOra1);
        txtOra1 = new JTextField();
        txtOra1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        txtOra1.setForeground(Color.BLACK);
        txtOra1.setBounds(10, 173, 148, 19);
        frame.getContentPane().add(txtOra1);
        txtOra1.setColumns(10);

        JLabel lblOra2 = new JLabel("Ora 2");
        lblOra2.setHorizontalAlignment(SwingConstants.CENTER);
        lblOra2.setForeground(Color.BLACK);
        lblOra2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        lblOra2.setBounds(10, 200, 148, 27);
        frame.getContentPane().add(lblOra2);
        txtOra2 = new JTextField();
        txtOra2.setForeground(Color.BLACK);
        txtOra2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        txtOra2.setColumns(10);
        txtOra2.setBounds(10, 223, 148, 19);
        frame.getContentPane().add(txtOra2);
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Raport1");
        frame.setBounds(100, 100, 861, 524);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);

    }

}

