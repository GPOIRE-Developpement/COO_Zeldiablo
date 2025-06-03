package gameLaby;

import moteurJeu.MoteurJeu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainLaby {

    public static void main(String[] args) throws IOException {
        int width = 800;
        int height = 600;
        int pFPS = 11;

        List<String> niveaux = getNiveaux();

        LabyJeu labyJeu = new LabyJeu(niveaux);
        LabyDessin labyDessin = new LabyDessin();

        MoteurJeu.setTaille(labyJeu.getLabyrinthe().getLength()*LabyDessin.size, labyJeu.getLabyrinthe().getLengthY()*LabyDessin.size + LabyJeu.INTERFACE_HEIGHT);
        MoteurJeu.setFPS(pFPS);

        MoteurJeu.launch(labyJeu,labyDessin);
        MoteurJeu.AfficherTuto(0);
    }

    private static List<String> getNiveaux() {
        List<String> niveaux = new ArrayList<>();
        niveaux.add("labySimple/niveau1.txt");
        niveaux.add("labySimple/niveau2.txt");
        niveaux.add("labySimple/niveau3.txt");
        niveaux.add("labySimple/niveau4.txt");
        niveaux.add("labySimple/niveau5.txt");
        niveaux.add("labySimple/niveau6.txt");
        niveaux.add("labySimple/niveau7.txt");
        niveaux.add("labySimple/niveau8.txt");
        niveaux.add("labySimple/niveau9.txt");
        niveaux.add("labySimple/niveau10.txt");
        niveaux.add("labySimple/niveau11.txt");
        niveaux.add("labySimple/niveau12.txt");
        niveaux.add("labySimple/niveau13.txt");
        return niveaux;
    }
}
