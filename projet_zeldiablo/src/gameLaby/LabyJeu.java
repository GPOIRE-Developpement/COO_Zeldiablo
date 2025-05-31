package gameLaby;

import gameLaby.entites.Entite;
import moteurJeu.Clavier;
import moteurJeu.Jeu;

import java.io.IOException;

public class LabyJeu implements Jeu{

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public static final double INTERFACE_HEIGHT = 2* LabyDessin.size;

    public final Labyrinthe labyrinthe;

    public LabyJeu(String nomFichier) throws IOException {
        this.labyrinthe = new Labyrinthe(nomFichier);
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

        labyrinthe.getPj().selectionnerObjet(clavier.getItemSelectionne());

        labyrinthe.deplacerMonstre();

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
