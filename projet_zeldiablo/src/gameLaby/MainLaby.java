package gameLaby;

import moteurJeu.MoteurJeu;

import java.io.IOException;

public class MainLaby {

    public static void main(String[] args) throws IOException {
        int width = 800;
        int height = 600;
        int pFPS = 50;

        LabyJeu labyJeu = new LabyJeu("labySimple/laby2.txt");
        LabyDessin labyDessin = new LabyDessin();

        MoteurJeu.setTaille(labyJeu.getLabyrinthe().getLength()*LabyDessin.size, labyJeu.getLabyrinthe().getLengthY()*LabyDessin.size + LabyJeu.INTERFACE_HEIGHT);
        MoteurJeu.setFPS(pFPS);

        MoteurJeu.launch(labyJeu,labyDessin);
    }

}
