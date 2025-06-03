package gameLaby.objets;

/**
 * Classe qui représente un objet bouclier
 */
public class Bouclier extends Objet {

    /**
     * Point de résistance du bouclier
     */
    private int def;
    private int defDeBase;

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
        this.defDeBase = def;
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

    /**Méthode permettant d'obtenir la def du bouclier
     * @param def Nouvelle valeur de def
     */
    public void setDef(int def){ this.def = def; }

    public int getDefDeBase(){
        return this.defDeBase;
    }
}
