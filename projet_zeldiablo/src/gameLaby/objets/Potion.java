package gameLaby.objets;

public class Potion extends Objet {

    private int vie;

    public Potion(String nom, int vie, int posX, int posY) {
        super(nom, posX, posY);
        this.vie = vie;
    }

    public int getVie() {
        return vie;
    }
}
