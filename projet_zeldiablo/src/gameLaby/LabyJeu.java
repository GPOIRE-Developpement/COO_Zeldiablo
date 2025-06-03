package gameLaby;

import gameLaby.casesSpe.CaseDeclencheuse;
import gameLaby.casesSpe.Sortie;
import gameLaby.entites.Entite;
import gameLaby.entites.Perso;
import javafx.application.Platform;
import moteurJeu.Clavier;
import moteurJeu.Jeu;
import moteurJeu.MoteurJeu;

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
        if (!tutoAffiche && niveau == 0) {
            tutoAffiche = true;
            Platform.runLater(() -> {
                MoteurJeu.AfficherTuto(niveau);
                clavier.reset(); // À faire si tu veux régler le bug de touche maintenue
            });
        }

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
        for (Entite m : labyrinthe.getMonstres()) {
            if (labyrinthe.getPj().peutAttaquer(m)) {
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
                    System.out.println("test");
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
            if (niveau < 6 && niveau > 0) {
                MoteurJeu.AfficherTuto(niveau);
            }
        } else {
            jeuFini();
        }
        System.out.println(niveau);
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

    public static void jeuFini() {
        System.out.println("Vous avez terminé la partie");
    }

}
