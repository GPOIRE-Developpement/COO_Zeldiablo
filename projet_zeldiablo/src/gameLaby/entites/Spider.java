package gameLaby.entites;

import gameLaby.graphe.*;

public class Spider extends Entite {
    /**
     * constructeur
     *
     * @param posx position selon x
     * @param posy position selon y
     */
    public Spider(int posx, int posy) {
        super(posx, posy);
        this.hp = 12;
    }

    @Override
    public void attaquer(Entite entite) {
        entite.subirDegat(-2);
    }

    @Override
    public int[] deplacer(String action, Perso perso, Graphe g) {
        Position pos = g.resourdre(new Position(super.getX(), super.getY()), new Position(perso.getX(), perso.getY()));

        if(this.getX() == pos.getX() && this.getY() == pos.getY()+1) {
            super.setPosition(Entite.HAUT);
        }else if(this.getX() == pos.getX() && this.getY() == pos.getY()-1) {
            super.setPosition(Entite.BAS);
        }else if(this.getX() == pos.getX()-1 && this.getY() == pos.getY()) {
            super.setPosition(Entite.DROITE);
        }else{
            super.setPosition(Entite.GAUCHE);
        }

        int[] res = {pos.getX(), pos.getY()};
        return res;
    }

    public String getName() {
        return "spider";
    }
}
