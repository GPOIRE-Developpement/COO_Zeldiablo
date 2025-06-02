package gameLaby.objets;

public class Potion extends Objet {

    private int vie;
    private boolean used;

    public Potion(String nom, int vie, int posX, int posY) {
        super(nom, posX, posY);
        this.vie = vie;
        used = false;
    }

    public int getVie() {
        return vie;
    }

    public String getNom() {
        if (used) {
            return "empty_bottle";
        } else {
            return "fill_bottle";
        }
    }

    public boolean isUsed() {
        return used;
    }


}
