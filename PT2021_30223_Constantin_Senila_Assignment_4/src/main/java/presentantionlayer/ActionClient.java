package presentantionlayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
/**
 * Pentru interfata grafica
 */
public class ActionClient implements ActionListener {
    Controller contr;
    PagPrincipala pG;
    public ActionClient(Controller c,PagPrincipala pg){
        contr = c;
        pG = pg;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Clyent panelClyent = null;
        try {
            panelClyent = new Clyent(pG);
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        pG.adaugaPanel(panelClyent);
//        System.out.println("apasat");
        pG.revalidate();
        pG.repaint();
    }
}
