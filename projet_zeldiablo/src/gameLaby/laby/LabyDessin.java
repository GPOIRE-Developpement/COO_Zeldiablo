package gameLaby.laby;

import gameArkanoid.Balle;
import gameArkanoid.Raquette;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import moteurJeu.DessinJeu;
import moteurJeu.Jeu;

import java.io.File;
import java.util.ArrayList;

public class LabyDessin implements DessinJeu {

    static final int size = 50;

    static final String PATH = "texture/";
    static final String WALL = PATH + "wall/simple_wall.png";
    static final String TOWER = PATH + "wall/tower.png";
    static final String GROUND = PATH + "ground/simple_grass.png";

    static final String PERSO = PATH + "character/front.png";
    static final String ORC = PATH + "orc/front.png";


    public void dessinerJeu(Jeu jeu, Canvas canvas) {

        int echelle = size;

        LabyJeu labyJeu = (LabyJeu) jeu;

        final GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.BLACK);
        Labyrinthe laby = labyJeu.getLabyrinthe();

        File imgf_wall = new File(WALL);
        Image img_wall = new Image(imgf_wall.toURI().toString());

        File imgf_ground = new File(GROUND);
        Image img_ground = new Image(imgf_ground.toURI().toString());

        for (int x = 0; x < laby.getLength(); x++) {
            for (int y = 0; y < laby.getLengthY(); y++) {
                if (laby.getMur(x,y)) {
                    gc.drawImage(img_wall, x*size, y*size, size, size);
                } else {
                    gc.drawImage(img_ground, x*size, y*size, size, size);
                }
            }
        }

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
        gc.fillRect(x1+border, y1+border,6*marge+5*caseSize,border+caseSize);

        gc.setFill(Color.BLACK);
        //creation des carrés
        int x = (int) ((x1+marge+border));
        int y = (int) ((y1+marge+border));
        for (int i = 0; i < 5; i++) {
            gc.fillRect(x, y, caseSize,caseSize);
            x = x + caseSize+marge;
        }

        //on parcours les cases
        CaseDeclencheuse[][] cases = labyJeu.getLabyrinthe().getCase();
        for (int colonne=0; colonne<cases.length; colonne++) {
            for (int ligne=0; ligne<cases[colonne].length; ligne++) {
                if (cases[colonne][ligne] != null) {
                    gc.setFill(Color.ORANGE);
                    gc.fillRect(colonne*size+3, ligne*size+3, size-6, size-6);
                }
            }
        }

        //dessin du monstre
        File imgf_orc = new File(ORC);
        Image img_orc = new Image(imgf_orc.toURI().toString());

        ArrayList<Entite> monstres = labyJeu.getLabyrinthe().getMonstres();
        for (Entite entite : monstres) {
            gc.drawImage(img_orc, entite.getX()*size, entite.getY()*size, size, size);
        }

        //gestion joueur
        File imgf_pj = new File(PERSO);
        Image img_pj = new Image(imgf_pj.toURI().toString());
        int posX = labyJeu.getLabyrinthe().pj.getX();
        int posY = labyJeu.getLabyrinthe().pj.getY();
        gc.drawImage(img_pj, posX*size, posY*size, size, size);

    }

}
