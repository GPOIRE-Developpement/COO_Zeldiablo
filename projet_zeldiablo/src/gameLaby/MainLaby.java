package gameLaby;

import moteurJeu.MoteurJeu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainLaby {

    public static void main(String[] args) throws IOException {
        int width = 800;
        int height = 600;
        int pFPS = 50;

        List<String> niveaux = new ArrayList<>();
        niveaux.add("labySimple/laby2.txt");
        niveaux.add("labySimple/laby3.txt");

        LabyJeu labyJeu = new LabyJeu(niveaux);
        LabyDessin labyDessin = new LabyDessin();

        MoteurJeu.setTaille(labyJeu.getLabyrinthe().getLength()*LabyDessin.size, labyJeu.getLabyrinthe().getLengthY()*LabyDessin.size + LabyJeu.INTERFACE_HEIGHT);
        MoteurJeu.setFPS(pFPS);

        MoteurJeu.launch(labyJeu,labyDessin);
    }
}
