package gameLaby.laby.objets;

/**
 * Classe qui représente un objet bouclier
 */
public class Bouclier extends Objet {

    /**
     * Point de résistance du bouclier
     */
    private int def;

    /**
     * constructeur de bouclier
     * @param nom nom du bouclier
     * @param def résistance du bouclier
     * @param x position X
     * @param y position Y
     */
    public Bouclier(String nom, int def, int x, int y) {
        super(nom,x,y);
        this.def = def;
    }

    /**
     * @return le nom du bouclier
     */
    public String getNom(){
        return super.getNom();
    }

    /**
     * @return la résistance du bouclier
     */
    public int getDef(){
        return this.def;
    }
}
