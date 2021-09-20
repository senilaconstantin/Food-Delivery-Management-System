package bll;

/**
 * Aceasta clasa contine produsele de baza
 */
public class BaseProduct extends MenuItem {
    public BaseProduct(String titlu, float raiting, int calorii, int proteine, int grasimi, int sodiu, float pret) {
        super(titlu, raiting, calorii, proteine, grasimi, sodiu, pret);
    }

    public BaseProduct() {
        super();
    }

    @Override
    public String getTitlu() {
        return getTitlu();
    }

    @Override
    public void setTitlu(String titlu) {
        super.setTitlu(titlu);
    }

    @Override
    public float getRaiting() {
        return getRaiting();
    }

    @Override
    public void setRaiting(float raiting) {
        super.setRaiting(raiting);
    }

    @Override
    public int getCalorii() {
        return getCalorii();
    }

    @Override
    public void setCalorii(int calorii) {
        super.setCalorii(calorii);
    }

    @Override
    public int getProteine() {
        return getProteine();
    }

    @Override
    public void setProteine(int proteine) {
        super.setProteine(proteine);
    }

    @Override
    public int getGrasimi() {
        return getGrasimi();
    }

    @Override
    public void setGrasimi(int grasimi) {
        super.setGrasimi(grasimi);
    }

    @Override
    public int getSodiu() {
        return getSodiu();
    }

    @Override
    public void setSodiu(int sodiu) {
       super.setSodiu(sodiu);
    }

    @Override
    public float getPret() {
        return getPret();
    }

    @Override
    public void setPret(float pret) {
        super.setPret(pret);
    }
    public float computePrice() {
        return getPret();
    }

    @Override
    public String toString() {
        return getTitlu() +
                "\n" + getRaiting() +
                "\n" + getCalorii() +
                "\n" + getProteine() +
                "\n" + getGrasimi() +
                "\n" + getSodiu() +
                "\n" + getPret() + "\n";
    }
}
