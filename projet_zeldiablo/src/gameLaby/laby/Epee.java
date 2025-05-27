package gameLaby.laby;


/**
 * Classe qui représente un objet Epée
 */
public class Epee extends Objet{

    /**
     * Nom de l'épée
     */
    String nom;

    /**
     * Degat de l'épée
     */
    int dommage;

    /**
     * constructeur de Epee
     * @param nom nom de l'épée
     * @param dommage degat de l'épée
     * @param x position en X
     * @param y position en Y
     */
    public Epee(String nom, int dommage, int x, int y) {
        this.nom = nom;
        this.dommage = dommage;
        this.X = x;
        this.Y = y;
    }

    /**
     * @return le nom de l'épée
     */
    public String getNom(){
        return this.nom;
    }

    /**
     * @return les dégats de l'épée
     */
    public int getDommage(){
        return this.dommage;
    }
}
