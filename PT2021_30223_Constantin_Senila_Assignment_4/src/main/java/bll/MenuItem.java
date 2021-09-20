package bll;

import java.io.Serial;
import java.io.Serializable;

/**
 * Aceasta clasa este mostenita de BaseProduct si de CompositeProduct
 */
public class MenuItem implements Serializable {
    @Serial
    private static final long serialVersion = 46346L;
    private String titlu;
    private float raiting;
    private int calorii;
    private int proteine;
    private int grasimi;
    private int sodiu;
    private float pret;

    public MenuItem(String titlu, float raiting, int calorii, int proteine, int grasimi, int sodiu, float pret) {
        this.titlu = titlu;
        this.raiting = raiting;
        this.calorii = calorii;
        this.proteine = proteine;
        this.grasimi = grasimi;
        this.sodiu = sodiu;
        this.pret = pret;
    }

    public MenuItem() {
    }

    public MenuItem(String product, int i) {
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public float getRaiting() {
        return raiting;
    }

    public void setRaiting(float raiting) {
        this.raiting = raiting;
    }

    public int getCalorii() {
        return calorii;
    }

    public void setCalorii(int calorii) {
        this.calorii = calorii;
    }

    public int getProteine() {
        return proteine;
    }

    public void setProteine(int proteine) {
        this.proteine = proteine;
    }

    public int getGrasimi() {
        return grasimi;
    }

    public void setGrasimi(int grasimi) {
        this.grasimi = grasimi;
    }

    public int getSodiu() {
        return sodiu;
    }

    public void setSodiu(int sodiu) {
        this.sodiu = sodiu;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }
    @Override
    public String toString() {
        return titlu +
                "\n" + raiting +
                "\n" + calorii +
                "\n" + proteine +
                "\n" + grasimi +
                "\n" + sodiu +
                "\n" + pret + "\n";
    }

}
