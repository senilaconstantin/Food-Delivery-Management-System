package presentantionlayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Pentru interfata grafica
 */
public class ActionADM implements ActionListener {
    Controller contr;
    PagPrincipala pG;
    public ActionADM(Controller c,PagPrincipala pg) {
        contr = c;
        pG = pg;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Administrator panelAdmin = new Administrator();
        contr.backListenerAdm(panelAdmin, new ANGListener(contr,pG));
        pG.adaugaPanel(panelAdmin);
//        System.out.println("apasat");
        pG.revalidate();
        pG.repaint();
    }
}
