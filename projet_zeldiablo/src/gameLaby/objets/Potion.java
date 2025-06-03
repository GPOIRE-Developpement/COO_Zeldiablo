package gameLaby.objets;

/**
 * Classe qui représente un objet Potion
 */
public class Potion extends Objet {

    /**
     * Vie rendu par la potion
     */
    private int vie;

    /**
     * Indique si la potion est pleine ou vide
     */
    private boolean used;

    /**
     * Constructeur de Potion
     * @param nom Nom de la potion
     * @param vie Vie rendu par la potion
     * @param posX Position X de la potion
     * @param posY position Y de la potion
     */
    public Potion(String nom, int vie, int posX, int posY) {
        super(nom, posX, posY);
        this.vie = vie;
        used = false;
    }

    /**
     * @return la vie rendu par la potion
     */
    public int getVie() {
        return vie;
    }

    /**
     * @return le nom de la potion
     */
    public String getNom() {
        if (used) {
            return "empty_bottle";
        } else {
            return "fill_bottle";
        }
    }

    /**
     * Permet d'indiquer que la potion est utilisée
     */
    public void utliser(){
        used = true;
    }

    /**
     * @return indique si la potion est utilisé ou non
     */
    public boolean isUsed() {
        return used;
    }


}
