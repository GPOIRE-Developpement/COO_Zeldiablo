package gameLaby.laby;

/**
 * Classe qui représente un objet bouclier
 */
public class Bouclier extends Objet{

    /**
     * Nom du bouclier
     */
    String nom;

    /**
     * Point de résistance du bouclier
     */
    int def;

    /**
     * constructeur de bouclier
     * @param nom nom du bouclier
     * @param def résistance du bouclier
     * @param x position X
     * @param y position Y
     */
    public Bouclier(String nom, int def, int x, int y) {
        this.nom = nom;
        this.def = def;
        this.X = x;
        this.Y = y;
    }

    /**
     * @return le nom du bouclier
     */
    public String getNom(){
        return this.nom;
    }

    /**
     * @return la résistance du bouclier
     */
    public int getDef(){
        return this.def;
    }
}
