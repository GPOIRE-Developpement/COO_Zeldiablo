package gameLaby.casesSpe;

public class Porte {

    private int x;
    private int y;

    private boolean ouverte;

    private boolean verticale;

    public boolean getVerti() {
        return verticale;
    }

    public void fermer() {
        ouverte = false;
    }

    public boolean getOuverte() {
        return ouverte;
    }

    public Porte(int x, int y, boolean verti) {
        this.x = x;
        this.y = y;
        ouverte = false;
    }

//    public void setX(int x) {
//        this.x = x;
//    }
//
//    public void setY(int y) {
//        this.y = y;
//    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void ouvrir() {
        this.ouverte = true;
    }
}
