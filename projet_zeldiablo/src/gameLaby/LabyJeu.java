package gameLaby;

import gameLaby.casesSpe.*;
import gameLaby.entites.*;
import gameLaby.objets.Objet;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import moteurJeu.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LabyJeu implements Jeu {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public static final double INTERFACE_HEIGHT = 2 * LabyDessin.size;

    public static Labyrinthe labyrinthe;

    private static int niveau;
    private static List<String> niveaux;
    private static List<Labyrinthe> labys;

    private boolean tutoAffiche = false;

    private static boolean lastLvl;

    public LabyJeu(String nomFichier) throws IOException {
        LabyJeu.labyrinthe = new Labyrinthe(nomFichier, null);
        LabyJeu.niveaux = null;
    }

    public void refresh() throws IOException {
        LabyJeu.niveau = 0;
        Labyrinthe lab = new Labyrinthe(niveaux.get(0), null);
        labyrinthe = lab;
        labys = new ArrayList<>();
        labys.add(lab);
        lastLvl = niveau == (niveaux.size() - 1);
    }

    public LabyJeu(List<String> niveaux) throws IOException {
        LabyJeu.niveau = 0;
        LabyJeu.niveaux = niveaux;
        Labyrinthe lab = new Labyrinthe(niveaux.get(0), null);
        labyrinthe = lab;
        labys = new ArrayList<>();
        labys.add(lab);
        lastLvl = niveau == (niveaux.size() - 1);
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

        for (Entite monstre : labyrinthe.getMonstres()) {
            if (monstre.isEstBlesse() && System.currentTimeMillis() - monstre.getTempsDernierHit() > 200) {
                monstre.setEstBlesse(false);
            }
        }

        if (labyrinthe.getPj().isEstBlesse() && System.currentTimeMillis() - labyrinthe.getPj().getTempsDernierHit() > 200) {
            labyrinthe.getPj().setEstBlesse(false);
        }

        labyrinthe.getPj().selectionnerObjet(clavier.getItemSelectionne());

        labyrinthe.deplacerMonstre();

    }

    public static void interagir() {
        labyrinthe.getPj().interagir(labyrinthe.getObjets());
    }

    public static void sortir() {
        CaseDeclencheuse caseDec = labyrinthe.getCase()[labyrinthe.getPj().getX()][labyrinthe.getPj().getY()];
        if (caseDec != null) {
            caseDec.activer(labyrinthe.getPj());
        }
    }

    public static void pjAttaque() {
        Objet itemEquip = labyrinthe.getPj().getItemSelecte();
        boolean isEpee;
        if (itemEquip == null) isEpee = false;
        else isEpee = itemEquip.getNom().equals("sword");
        for (Entite m : labyrinthe.getMonstres()) {
            if (labyrinthe.getPj().peutAttaquer(m, isEpee)) {
                labyrinthe.getPj().attaquer(m);
            }
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

    public static void niveauSuivant(Perso p) {
        lastLvl = niveau == (niveaux.size() - 1);
        if (niveau == niveaux.size()-2) {
            MoteurJeu.afficherIndication();
        }
        if (niveau < niveaux.size() - 1) {
            niveau++;
        }
        if (LabyJeu.niveau < LabyJeu.niveaux.size() && !lastLvl) {
            try {
                LabyJeu.labyrinthe = labys.get(niveau);
            } catch (Exception e) {
                try {
                    Labyrinthe labyrinthe1 = new Labyrinthe(LabyJeu.niveaux.get(LabyJeu.niveau), p);
                    LabyJeu.labyrinthe = labyrinthe1;
                    labys.add(labyrinthe1);
                } catch (IOException e1) {
                    System.out.println("fichier introuvable");
                }
            }
            if (p != null) {
                int[] coordSortie = labyrinthe.getSortie(false);
                System.out.println("x : " + coordSortie[0] + " y : " + coordSortie[1]);
                p.setX(coordSortie[0]);
                p.setY(coordSortie[1]);
                labyrinthe.setPJ(p);
            }
        } else {
            if (labyrinthe.getPj().getItemSelecte().getNom().equals("cle")) {
                MoteurJeu.jeuFini();
            }
        }
    }

    public static void niveauPrec(Perso pj) {
        LabyJeu.niveau--;
        try {
            LabyJeu.labyrinthe = labys.get(niveau);
        } catch (Exception e) {  // on ne devrait jamais arriver ici
            try {
                LabyJeu.labyrinthe = new Labyrinthe(LabyJeu.niveaux.get(LabyJeu.niveau), pj);
            } catch (IOException e2) {
                System.out.println("fichier introuvable");
            }
        }
        int[] coordSortie = labyrinthe.getSortie(true);
        pj.setX(coordSortie[0]);
        pj.setY(coordSortie[1]);
        labyrinthe.setPJ(pj);
    }

    public static int getNiveau() {
        return niveau;
    }

}
