package gameLaby.laby;


/**
 * gere un personnage situe en x,y
 */
public class Perso extends Entite {

    /**
     * constructeur
     *
     * @param dx position selon x
     * @param dy position selon y
     */
    public Perso(int dx, int dy) {
        super(dx, dy);
    }

    public void setHp(int n){
        this.hp = n;
    }

    public void setAtk(int n){
        this.atk = n;
    }

}
