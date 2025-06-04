package gameLaby.objets;

/**
 * Classe abstraite qui représente les objets présents dans le jeu
 */
public abstract class Objet {

    /**
     * Position de l'objet
     */
    private int X;
    private int Y;

    /**
     * costantes char
     */
    public static final char EPEE = 'E';
    public static final char BOUCLIER = 'B';
    public static final char POTION = 'V';
    public static final char CLE = 'C';

    /**
     * nom de l'objet
     */
    private String nom;

    public Objet(String nom, int x, int y) {
        this.nom = nom;
        this.X = x;
        this.Y = y;
    }

    /**
     * @param x nouvelle position X
     */
    public void setX(int x) {
        this.X = x;
    }

    /**
     * @param y nouvelle position Y
     */
    public void setY(int y) {
        this.Y = y;
    }

    /**
     * @return position X de l'objet
     */
    public int getX(){
        return X;
    }

    /**
     * @return position Y de l'objet
     */
    public int getY(){
        return Y;
    }

    public String getNom(){
        return nom;
    }

}
