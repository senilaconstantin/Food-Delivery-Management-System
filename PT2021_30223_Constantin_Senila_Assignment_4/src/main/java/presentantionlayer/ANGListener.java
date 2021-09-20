package presentantionlayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Pentru interfata grafica
 */
public class ANGListener implements ActionListener {
    Controller contr;
    PagPrincipala pG;
    public ANGListener(Controller c,PagPrincipala pg) {
        contr = c;
        pG = pg;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        pG.setVisible(false);
        PagPrincipala pg = new PagPrincipala();
        Controller x = new Controller(pg);
        pg.setVisible(true);

        //System.out.println("apasat din angajat");
    }
}
