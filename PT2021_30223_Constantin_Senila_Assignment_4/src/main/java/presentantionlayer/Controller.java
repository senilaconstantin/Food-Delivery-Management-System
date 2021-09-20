package presentantionlayer;

import java.awt.event.ActionListener;
/**
 * Pentru interfata grafica
 */
public class Controller {
    public Controller(PagPrincipala pg) {

        ActionANG a = new ActionANG(this, pg);
        pg.addListener(a);

        ActionADM adm = new ActionADM(this, pg);
        pg.addListenerAdmin(adm);

        ActionClient aclin = new ActionClient(this, pg);
        pg.addListenerClient(aclin);
    }


    public void backListenerEmployee(Employee angajat, ActionListener e) {
        angajat.addListener(e);
    }

    public void backListenerAdm(Administrator admin, ActionListener e) {
        admin.addListener(e);
    }
}
