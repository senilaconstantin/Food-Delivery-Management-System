package presentantionlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

/**
 * angajatul care rimeste cate o notificare pentru fiecare comanda
 */
public class Employee extends JPanel implements Observer {
    private JButton back;
    private JTextArea textAreaComenzi;
    public Employee() throws IOException {
        this.setLayout(null);
        this.setBounds(0, 0, 860, 568);

        back = new JButton("Back");
        back.setBounds(5, 5, 65, 25);

        txtArea();

        this.add(back);
        this.revalidate();
        this.repaint();
    }

    public void addListener(ActionListener e) {
        back.addActionListener(e);
    }

    private String comenzi() throws IOException {
        String str = null;
        File file = new File("Comenzi.txt");
        FileReader rd = null;
        try {
            rd = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            rd.read(chars);
            str = new String(chars);
            rd.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(rd != null){
                rd.close();
            }
        }
        //System.out.println(str);
        return str;
    }

    private void txtArea() throws IOException {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(76, 45, 756, 405);
        this.add(scrollPane);

        textAreaComenzi = new JTextArea();
        textAreaComenzi.setEditable(false);
        textAreaComenzi.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        textAreaComenzi.setForeground(Color.BLACK);
        scrollPane.setViewportView(textAreaComenzi);

        textAreaComenzi.append(comenzi());

        JLabel lblComenzi = new JLabel("Comenzi");
        lblComenzi.setHorizontalAlignment(SwingConstants.CENTER);
        lblComenzi.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
        lblComenzi.setForeground(Color.BLACK);
        lblComenzi.setBounds(74, 10, 754, 29);
        this.add(lblComenzi);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
