package presentantionlayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
/**
 * Pentru interfata grafica
 */
public class ActionANG implements ActionListener {
    Controller contr;
    PagPrincipala pG;

    public ActionANG(Controller c, PagPrincipala pg) {
        contr = c;
        pG = pg;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Employee panelAngajat = null;
        try {
            panelAngajat = new Employee();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        contr.backListenerEmployee(panelAngajat, new ANGListener(contr,pG));
        pG.adaugaPanel(panelAngajat);
        //System.out.println("apasat");
        pG.revalidate();
        pG.repaint();
    }
}
