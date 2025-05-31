package gameLaby.laby.objets;


/**
 * Classe qui représente un objet Epée
 */
public class Epee extends Objet {

    /**
     * Degat de l'épée
     */
    private int dommage;

    /**
     * constructeur de Epee
     * @param nom nom de l'épée
     * @param dommage degat de l'épée
     * @param x position en X
     * @param y position en Y
     */
    public Epee(String nom, int dommage, int x, int y) {
        super(nom, x, y);
        this.dommage = dommage;
    }

    /**
     * @return le nom de l'épée
     */
    public String getNom(){
        return super.getNom();
    }

    /**
     * @return les dégats de l'épée
     */
    public int getDommage(){
        return this.dommage;
    }
}
