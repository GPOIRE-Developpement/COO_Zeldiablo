package gameLaby;

import gameLaby.entites.Entite;
import moteurJeu.Clavier;
import moteurJeu.Jeu;

import java.io.IOException;
import java.util.List;

public class LabyJeu implements Jeu{

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public static final double INTERFACE_HEIGHT = 2* LabyDessin.size;

    public static Labyrinthe labyrinthe;

    private static int niveau;
    private static List<String> niveaux;

    public LabyJeu(String nomFichier) throws IOException {
        LabyJeu.labyrinthe = new Labyrinthe(nomFichier);
        LabyJeu.niveaux = null;
    }

    public LabyJeu(List<String> niveaux) throws IOException {
        LabyJeu.niveau = 0;
        LabyJeu.niveaux = niveaux;

        LabyJeu.niveauSuivant();
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

        if (clavier.interaction){
            labyrinthe.getPj().interagir(labyrinthe.getObjets());
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

    public static void niveauSuivant() throws IOException{
        if(LabyJeu.niveaux != null && LabyJeu.niveau < LabyJeu.niveaux.size()){
            LabyJeu.labyrinthe = new Labyrinthe(LabyJeu.niveaux.get(LabyJeu.niveau));
        }else{
            jeuFini();
        }
        LabyJeu.niveau++;
    }

    public static void jeuFini(){
        System.out.println("Vous avez terminé la partie");
    }
}
