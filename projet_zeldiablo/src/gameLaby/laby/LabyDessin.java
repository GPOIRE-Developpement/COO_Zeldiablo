package gameLaby.laby;

import gameArkanoid.Balle;
import gameArkanoid.Raquette;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import moteurJeu.DessinJeu;
import moteurJeu.Jeu;

public class LabyDessin implements DessinJeu {

    static final int size = 50;

    public void dessinerJeu(Jeu jeu, Canvas canvas) {

        int echelle = size;

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

        //gestion de l'inventaire
        gc.setFill(Color.BLUE);
        gc.fillRect(0,labyJeu.getLabyrinthe().getLengthY()*LabyDessin.size,labyJeu.getLabyrinthe().getLength()*LabyDessin.size, LabyJeu.INTERFACE_HEIGHT);

        //position de la ligne de démarcation infosJoueur/inventaire
        double x1 = (double) (labyJeu.getLabyrinthe().getLength() /3)*size;
        double y1 = labyJeu.getLabyrinthe().getLengthY()*size;
        double y2 = (labyJeu.getLabyrinthe().getLengthY() + LabyJeu.INTERFACE_HEIGHT)*size;
        gc.strokeLine(x1,y1,x1,y2);

        //gestion inventaire
        int border = 5;
        int marge = 2;
        int caseSize = 60;
        gc.setFill(Color.RED);
        gc.fillRect(x1+border, y1+border,6*marge+5*caseSize,2*border+caseSize);

        gc.setFill(Color.BLACK);
        //creation des carrés
        int x = (int) ((x1+marge+border));
        int y = (int) ((y1+marge+border));
        for (int i = 0; i < 5; i++) {
            gc.fillRect(x, y, caseSize,caseSize);
            x = x + caseSize+marge;
        }
    }

}
