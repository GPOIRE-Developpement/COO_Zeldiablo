package gameLaby.laby;

import moteurJeu.Clavier;
import moteurJeu.Jeu;

import java.io.IOException;

public class LabyJeu implements Jeu{

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public static final int INTERFACE_HEIGHT = 90;

    public final Labyrinthe labyrinthe;

    public LabyJeu() throws IOException {
        this.labyrinthe = new Labyrinthe("labySimple/laby2.txt");
    }

    public void update(double seconde, Clavier clavier) {
        if (clavier.gauche) {
            labyrinthe.deplacerPerso(Entite.GAUCHE);
        }

        if (clavier.droite) {
            labyrinthe.deplacerPerso(Entite.DROITE);
        }

        if (clavier.haut) {
            labyrinthe.deplacerPerso(Entite.HAUT);
        }

        if (clavier.bas) {
            labyrinthe.deplacerPerso(Entite.BAS);
        }

    }

    public void init() {
        //rien de ce que je pense
    }

    public boolean etreFini() {
        return labyrinthe.etreFini();
    }

    public Labyrinthe getLabyrinthe() {
        return labyrinthe;
    }

}
