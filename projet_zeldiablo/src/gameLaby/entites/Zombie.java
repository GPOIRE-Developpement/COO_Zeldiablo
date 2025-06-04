package gameLaby.entites;

import gameLaby.graphe.Graphe;

public class Zombie extends Entite {
    /**
     * constructeur
     *
     * @param posx position selon x
     * @param posy position selon y
     */
    public Zombie(int posx, int posy) {
        super(posx, posy);
    }

    @Override
    public void attaquer(Entite entite) {
        entite.subirDegat(-1);
    }

    public String getName() {
        return "zombie";
    }

    public int[] deplacer(String a, Perso perso, Graphe g) {
        int[] courante = {this.x, this.y};

        String action = Graphe.resoudre2(super.getX(), super.getY(), perso.getX(), perso.getY());

        super.setPosition(action);

        // calcule case suivante
        return Entite.getSuivant(courante[0], courante[1], action);
    }
}
