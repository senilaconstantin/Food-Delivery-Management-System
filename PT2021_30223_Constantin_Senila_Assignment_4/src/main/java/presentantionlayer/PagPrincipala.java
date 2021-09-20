package presentantionlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PagPrincipala extends JFrame {
    private JPanel panelPrincipal = new JPanel();
    private JButton btnAngajat;
    private JButton btnAdmin;
    private JButton btnClient;
    private JPanel content;
    /**
     * Pentru interfata grafica pagina principala
     */
    public PagPrincipala() {
        this.setBounds(300, 100, 860, 568);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        initialize2();
    }

    public void adaugaPanel(JPanel panelNou) {
        panelPrincipal.removeAll();
        panelPrincipal.add(panelNou);
        panelPrincipal.revalidate();
        panelPrincipal.repaint();
    }

    private void initialize2() {
        content = new JPanel();
        content.setBackground(Color.cyan);
        this.setContentPane(content);

        JLabel lblTitlu = new JLabel("Food Delivery Management System\r\n");
        lblTitlu.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitlu.setForeground(Color.BLACK);
        lblTitlu.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 44));
        lblTitlu.setBounds(0, 0, 860, 70);
        content.add(lblTitlu);

        content.setLayout(null);

        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);
       // panelPrincipal.setBackground(Color.BLUE);
        panelPrincipal.setBounds(0, 70, 860, 568);

        content.add(panelPrincipal);

        btnAdmin = new JButton("Admin");
        btnAdmin.setForeground(Color.BLACK);
        btnAdmin.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
        btnAdmin.setBounds(323, 123, 196, 48);
        panelPrincipal.add(btnAdmin);

        btnClient = new JButton("Client");
        btnClient.setForeground(Color.BLACK);
        btnClient.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
        btnClient.setBounds(323, 207, 196, 48);
        panelPrincipal.add(btnClient);

        btnAngajat = new JButton("Angajat");
        btnAngajat.setForeground(Color.BLACK);
        btnAngajat.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
        btnAngajat.setBounds(323, 286, 196, 48);
        panelPrincipal.add(btnAngajat);

        content.revalidate();
        content.repaint();
    }

    public void addListener(ActionListener e) {
       btnAngajat.addActionListener(e);
    }
    public void addListenerAdmin(ActionListener e) {
        btnAdmin.addActionListener(e);
    }
    public void addListenerClient(ActionListener e) {
        btnClient.addActionListener(e);
    }

}
