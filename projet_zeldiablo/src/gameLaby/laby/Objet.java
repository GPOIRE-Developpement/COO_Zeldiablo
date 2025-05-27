package gameLaby.laby;

/**
 * Classe abstraite qui représente les objets présents dans le jeu
 */
public abstract class Objet {

    /**
     * Position de l'objet
     */
    int X;
    int Y;

    /**
     * costantes char
     */
    public static final char EPEE = 'E';
    public static final char BOUCLIER = 'B';

    boolean possede = false;

    /**
     * nom de l'objet
     */
    String nom;

    /**
     * @param x nouvelle position X
     */
    public void setX(int x){
        this.X = x;
    }

    /**
     * @param y nouvelle position Y
     */
    public void setY(int y){
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
}
