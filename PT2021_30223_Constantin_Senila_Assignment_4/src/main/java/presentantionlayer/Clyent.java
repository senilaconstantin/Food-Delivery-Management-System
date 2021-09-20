package presentantionlayer;

import bll.Clienti;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.*;

/**
 * <p>se poate face inregistrarea si logarea de catre client</p>
 */
public class Clyent extends JPanel {
    private JButton back;
    private JButton btnInregistrare;
    private JButton btnConectare;

    private JTextField txtNume;
    private JTextField txtEmail;
    private JTextField txtUsername;
    private JTextField txtUsernameC;
    private JPasswordField passwordI;
    private JPasswordField passwordC;

    private ArrayList<Clienti> clienti;
    private PagPrincipala pG;

    public Clyent(PagPrincipala pG) throws FileNotFoundException {
        this.pG=pG;
        //this.clienti = new ArrayList<Clienti>();
        this.setLayout(null);
        this.setBounds(0, 0, 860, 568);

        back = new JButton("Back");
        back.setBounds(5, 5, 65, 25);

        array();
        initialize();
        initializeB();
        initializeLbl();

        btnActionI();
        btnActionC();
        btnActionB();

        this.add(back);
        this.revalidate();
        this.repaint();
    }

    private void initializeB() {
        btnInregistrare = new JButton("Inregistrare");
        btnInregistrare.setForeground(Color.BLACK);
        btnInregistrare.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
        btnInregistrare.setBounds(78, 285, 116, 30);
        this.add(btnInregistrare);

        btnConectare = new JButton("Conectare");
        btnConectare.setForeground(Color.BLACK);
        btnConectare.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
        btnConectare.setBounds(602, 231, 116, 30);
        this.add(btnConectare);
    }

    private void initializeLbl() {
        JLabel lblNewLabel = new JLabel("Inregistrare");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblNewLabel.setBounds(10, 72, 283, 24);
        this.add(lblNewLabel);

        JLabel lblNume = new JLabel("Nume:");
        lblNume.setHorizontalAlignment(SwingConstants.LEFT);
        lblNume.setForeground(Color.BLACK);
        lblNume.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblNume.setBounds(0, 125, 59, 24);
        this.add(lblNume);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
        lblEmail.setForeground(Color.BLACK);
        lblEmail.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblEmail.setBounds(0, 159, 59, 24);
        this.add(lblEmail);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setHorizontalAlignment(SwingConstants.LEFT);
        lblUsername.setForeground(Color.BLACK);
        lblUsername.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblUsername.setBounds(0, 193, 81, 24);
        this.add(lblUsername);

        JLabel lblParola = new JLabel("Parola:");
        lblParola.setHorizontalAlignment(SwingConstants.LEFT);
        lblParola.setForeground(Color.BLACK);
        lblParola.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblParola.setBounds(0, 228, 59, 24);
        this.add(lblParola);

        JLabel lblConectare = new JLabel("Conectare");
        lblConectare.setHorizontalAlignment(SwingConstants.CENTER);
        lblConectare.setForeground(Color.BLACK);
        lblConectare.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblConectare.setBounds(511, 89, 283, 24);
        this.add(lblConectare);

        JLabel lblUsernameC = new JLabel("Username:");
        lblUsernameC.setHorizontalAlignment(SwingConstants.LEFT);
        lblUsernameC.setForeground(Color.BLACK);
        lblUsernameC.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblUsernameC.setBounds(524, 142, 81, 24);
        this.add(lblUsernameC);

        JLabel lblParolaC = new JLabel("Parola:");
        lblParolaC.setHorizontalAlignment(SwingConstants.LEFT);
        lblParolaC.setForeground(Color.BLACK);
        lblParolaC.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblParolaC.setBounds(524, 177, 59, 24);
        this.add(lblParolaC);
    }

    private void initialize() {
        txtNume = new JTextField("");
        txtNume.setForeground(Color.BLACK);
        txtNume.setBounds(49, 127, 221, 23);
        this.add(txtNume);
        txtNume.setColumns(10);

        txtEmail = new JTextField("");
        txtEmail.setForeground(Color.BLACK);
        txtEmail.setColumns(10);
        txtEmail.setBounds(49, 161, 221, 23);
        this.add(txtEmail);

        txtUsername = new JTextField("");
        txtUsername.setForeground(Color.BLACK);
        txtUsername.setColumns(10);
        txtUsername.setBounds(78, 195, 192, 23);
        this.add(txtUsername);

        passwordI = new JPasswordField("");
        passwordI.setEchoChar('*');
        passwordI.setBounds(58, 228, 212, 23);
        this.add(passwordI);

        txtUsernameC = new JTextField("");
        txtUsernameC.setForeground(Color.BLACK);
        txtUsernameC.setColumns(10);
        txtUsernameC.setBounds(602, 144, 192, 23);
        this.add(txtUsernameC);

        passwordC = new JPasswordField("");
        passwordC.setEchoChar('*');
        passwordC.setBounds(582, 176, 212, 23);
        this.add(passwordC);
    }

    public static void showAlert(String msg) {
        JLabel allertMsg = new JLabel(msg);
        allertMsg.setFont(new Font("Tahoma", Font.PLAIN, 20));
        JOptionPane.showMessageDialog(null, allertMsg);
    }

    private void btnActionI() {
        btnInregistrare.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtNume.getText().equals("") || txtEmail.getText().equals("") || txtUsername.getText().equals("") || passwordI.getText().equals("")) {
                    showAlert("Introduceti date in toate campurile!");
                }
                else{
                    Clienti c = new Clienti(txtNume.getText(), txtEmail.getText(), txtUsername.getText(), passwordI.getText());
                    clienti.add(c);
                    OutputStream os = null;
                    try {
                        os = Files.newOutputStream(Paths.get("Clienti.txt"),APPEND);

                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    PrintWriter pr = new PrintWriter(os);
                    pr.println(c.toString());
//                    System.out.println(clienti.get(clienti.size()-1).toString());
                    txtEmail.setText("");
                    txtNume.setText("");
                    txtUsername.setText("");
                    passwordI.setText("");
                    showAlert("S-a creat cu succes!");
                    pr.close();
                }

            }
        });
    }
    private void array() {
        Clienti c=new Clienti();
        clienti=c.getClienti();
    }
    private void btnActionB(){
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pG.setVisible(false);
                PagPrincipala pg=new PagPrincipala();
                Controller c=new Controller(pg);
                pg.setVisible(true);
            }
        });
    }
    private void btnActionC(){
        btnConectare.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Clienti cl = null;
                int ok=0;
                for(Clienti c: clienti){
                    if(c.getUsername().equals(txtUsernameC.getText())&&c.getPassword().equals(passwordC.getText()))
                    {
                        ok=1;
                        cl=c;
                        break;
                    }
                }
                if(ok==1){
                    ClientConectat client=new ClientConectat(pG, cl);
                    pG.adaugaPanel(client);
                    showAlert("S-a conectat cu succes!");
                }
                else{
                    showAlert("Username sau Parola gresite!");
                }
            }
        });
    }
}
