package presentantionlayer;

import bll.DeliveryService;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
/**
 * Pentru interfata grafica raport 3
 */
public class Rap3 {

    private JFrame frame;
    private JTextArea textArea;
    private JTextField txtNrComenzi;
    private JTextField txtSuma;
    private JButton btnGenereaza;
    private DeliveryService dvs = new DeliveryService();

    public Rap3() {
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
                int a = Integer.parseInt(txtNrComenzi.getText());
                int b = Integer.parseInt(txtSuma.getText());
                ArrayList<String> clienti = null;
                try {
                    clienti = dvs.generateRaport3Clienti(a, b);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                String str=new String();
                for(String s: clienti){
                    str+=s+"\n";
                }
                textArea.setText("");
                textArea.append(str);
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

        txtNrComenzi = new JTextField();
        txtNrComenzi.setForeground(Color.BLACK);
        txtNrComenzi.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        txtNrComenzi.setColumns(10);
        txtNrComenzi.setBounds(10, 142, 148, 19);
        frame.getContentPane().add(txtNrComenzi);

        JLabel lblNumar = new JLabel("Numar Comenzi");
        lblNumar.setHorizontalAlignment(SwingConstants.CENTER);
        lblNumar.setForeground(Color.BLACK);
        lblNumar.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        lblNumar.setBounds(10, 119, 148, 27);
        frame.getContentPane().add(lblNumar);

        JLabel lblSuma = new JLabel("Suma ");
        lblSuma.setHorizontalAlignment(SwingConstants.CENTER);
        lblSuma.setForeground(Color.BLACK);
        lblSuma.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        lblSuma.setBounds(10, 171, 148, 27);
        frame.getContentPane().add(lblSuma);

        txtSuma = new JTextField();
        txtSuma.setForeground(Color.BLACK);
        txtSuma.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        txtSuma.setColumns(10);
        txtSuma.setBounds(10, 194, 148, 19);
        frame.getContentPane().add(txtSuma);
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Raport3");
        frame.setBounds(100, 100, 861, 524);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.getContentPane().setLayout(null);
    }

}

