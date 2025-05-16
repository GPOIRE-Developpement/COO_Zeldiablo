package gameLaby.laby;

import gameArkanoid.Balle;
import gameArkanoid.Raquette;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import moteurJeu.DessinJeu;
import moteurJeu.Jeu;

public class LabyDessin implements DessinJeu {

    public void dessinerJeu(Jeu jeu, Canvas canvas) {

        int echelle = 50;

        LabyJeu labyJeu = (LabyJeu) jeu;

        final GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.setFill(Color.BLACK);
        Labyrinthe laby = labyJeu.getLabyrinthe();

        for (int x = 0; x < laby.getLength(); x++) {
            for (int y = 0; y < laby.getLengthY(); y++) {
                if (laby.getMur(x,y)) {
                    gc.fillRect(x*echelle, y*echelle, echelle, echelle);
                }
            }
        }

        gc.setFill(Color.RED);
        Perso perso = laby.pj;
        gc.fillOval(perso.x*echelle, perso.y*echelle, echelle, echelle);

    }

}
