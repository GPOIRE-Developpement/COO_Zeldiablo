package gameLaby.laby;

import moteurJeu.Clavier;
import moteurJeu.Jeu;

import java.io.IOException;

public class LabyJeu implements Jeu{

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public final Labyrinthe labyrinthe;

    public LabyJeu() throws IOException {
        this.labyrinthe = new Labyrinthe("labySimple/laby2.txt");
    }

    public void update(double seconde, Clavier clavier) {
        if (clavier.gauche) {
            labyrinthe.deplacerPerso(Labyrinthe.GAUCHE);
        }

        if (clavier.droite) {
            labyrinthe.deplacerPerso(Labyrinthe.DROITE);
        }

        if (clavier.haut) {
            labyrinthe.deplacerPerso(Labyrinthe.HAUT);
        }

        if (clavier.bas) {
            labyrinthe.deplacerPerso(Labyrinthe.BAS);
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
