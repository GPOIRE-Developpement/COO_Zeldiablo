package gameLaby;

import gameLaby.casesSpe.CaseDeclencheuse;
import gameLaby.casesSpe.Porte;
import gameLaby.entites.Entite;
import gameLaby.entites.Perso;
import gameLaby.objets.Objet;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import moteurJeu.DessinJeu;
import moteurJeu.Jeu;

import java.io.File;
import java.util.ArrayList;

public class LabyDessin implements DessinJeu {

    static final double size = 44;

    static final String PATH = "texture/";
    static final String WALL = PATH + "wall/";
    static final String TOWER = PATH + "wall/tower.png";
    static final String GROUND = PATH + "ground/simple_grass.png";
    static final String DOOR = PATH + "door/";

    static final String SKIN = PATH + "character/plague/";
    static final String MONSTER = PATH + "monster/";
    static final String BACKGROUND = PATH + "background/";
    static final String ITEM = PATH + "item/";
    static final String PERSO = PATH + "perso/";

    public void dessinerJeu(Jeu jeu, Canvas canvas) {

        double echelle = size;

        LabyJeu labyJeu = (LabyJeu) jeu;

        final GraphicsContext gc = canvas.getGraphicsContext2D();

        Labyrinthe laby = labyJeu.getLabyrinthe();

        final int pvInit = 10;

        gc.setFill(Color.BLACK);

        File imgf_wall = new File(WALL + "wall.png");
        Image img_wall = new Image(imgf_wall.toURI().toString());

        File imgf_ground = new File(GROUND);
        Image img_ground = new Image(imgf_ground.toURI().toString());

        //gestion des murs et du sol
        for (int x = 0; x < laby.getLength(); x++) {
            for (int y = 0; y < laby.getLengthY(); y++) {
                if (laby.getMur(x, y)) {
                    gc.drawImage(img_wall, x * size, y * size, size, size);
                } else {
                    gc.drawImage(img_ground, x * size, y * size, size, size);
                }
            }
        }

        //##############
        //INVENTAIRE
        //##############

        //background de l'inventaire
        gc.setFill(Color.DARKGREY);
        gc.fillRect(0,laby.getLengthY()*size,laby.getLength()*size,LabyJeu.INTERFACE_HEIGHT);

        //position de la ligne de démarcation infosJoueur/inventaire
        double x1 = (double) (laby.getLength() / 3) * size;
        double y1 = laby.getLengthY() * size;
        double y2 = (laby.getLengthY() + LabyJeu.INTERFACE_HEIGHT) * size;
        gc.strokeLine(x1, y1, x1, y2);

        //gestion de l'inventaire
        File imgf_inv = new File(BACKGROUND + "inventory_stacks.png");
        Image img_inv = new Image(imgf_inv.toURI().toString());
        gc.drawImage(img_inv, x1+10, y1+8, img_inv.getWidth()*2.25, img_inv.getHeight()*2.25);

        //gestion de l'affichage de l'item selectionné
        //16*2.25
        File imgf_selector= new File(BACKGROUND + "selector.png");
        Image img_selector = new Image(imgf_selector.toURI().toString());
        int indiceSelect = laby.getPj().getInventaire().indexOf(laby.getPj().getItemSelecte());
        if (indiceSelect >= 0 && indiceSelect < 5) {
            gc.drawImage(img_selector, x1 + 10 + (33 + 17 * (indiceSelect))*2.25, y1 + 8 + 4*2.25, 16 * 2.25, 16 * 2.25);
        }

        //gestion de l'affichage des HP
        gc.setFill(Color.rgb(246,56,65));
        int pv = laby.getPj().getHP();
        int nbPixelPvUnitaire = 105/10; //ici 105 c'est la largeur de la barre de pv;
        gc.fillRect(38*1.25, y1+15+10*1.25, nbPixelPvUnitaire*pv*1.25, 13*1.25);

        //gestion de la barre de vie vide du perso
        File imgf_life = new File(PERSO + "empty_lifebar.png");
        Image img_life = new Image(imgf_life.toURI().toString());
        gc.drawImage(img_life,20, y1+15, img_life.getWidth()*1.25, img_life.getHeight()*1.25);

        //##############
        //CASES SPECIALES
        //##############

        //gestion des cases declencheuses
        CaseDeclencheuse[][] cases = laby.getCase();
        for (int colonne = 0; colonne < cases.length; colonne++) {
            for (int ligne = 0; ligne < cases[colonne].length; ligne++) {
                if (cases[colonne][ligne] != null) {
                    switch (cases[colonne][ligne].getType()) {
                        case "piege":
                            gc.setFill(Color.ORANGE);
                            gc.fillRect(colonne * size + 3, ligne * size + 3, size - 6, size - 6);
                            break;
                        case "interrupteur":
                            gc.setFill(Color.RED);
                            gc.fillRect(colonne * size + 3, ligne * size + 3, size - 6, size - 6);
                            break;
                        default:
                            System.out.println("erreur je connais pas cette case");
                    }
                }
            }
        }

        //gestion des portes
        ArrayList<Porte> portes = laby.getPortes();
        for (Porte porte : portes) {
            if (porte.getVerti()) {
                System.out.println("blabla");
            } else {
                String etat = porte.getOuverte() ? "open" : "closed";
                String path = DOOR+"front_door_" + etat + ".png";
                File imgf_door = new File(path);
                Image img_door = new Image(imgf_door.toURI().toString());
                gc.drawImage(img_door,porte.getX()*size, porte.getY()*size, size, size);
            }
        }

        //##############
        //OBJETS
        //##############

        //gestion de l'affichege des objets
        ArrayList<Objet> objets = laby.getObjets();
        for (Objet objet : objets) {
            String path = ITEM + objet.getNom() + ".png";
            //creation du fichier à partir du path
            File imgf_item = new File(path);
            Image img_item = new Image(imgf_item.toURI().toString());
            gc.drawImage(img_item, objet.getX() * size, objet.getY() * size, size, size);
        }

        //##############
        //ENTITES
        //##############

        //dessin des monstre
        ArrayList<Entite> monstres = laby.getMonstres();
        for (Entite entite : monstres) {
            String path_monster = MONSTER + entite.getPosition() + ".png";
            File imgf_monster = new File(path_monster);
            Image img_monster = new Image(imgf_monster.toURI().toString());
            gc.drawImage(img_monster, entite.getX() * size, entite.getY() * size, size, size);
        }

        //gestion joueur
        Perso pj = laby.getPj();

        String path = SKIN + pj.getPosition() + ".png";
        File imgf_pj = new File(path);
        Image img_pj = new Image(imgf_pj.toURI().toString());
        int posX = pj.getX();
        int posY = pj.getY();
        gc.drawImage(img_pj, posX * size, posY * size, size, size);

        //##############
        //MURS UPPER
        //##############

        //mur de base si pas etendu
        File imgf_side_wall = new File(WALL+"wall_side.png");
        Image img_side_wall = new Image(imgf_side_wall.toURI().toString());

        File imgf_side_wall_extend = new File(WALL+"wall_side_extend.png");
        Image img_side_wall_extend = new Image(imgf_side_wall_extend.toURI().toString());
        //gestion de la "3D" avec la face haute des murs
        for (int n = 0; n < laby.getLength(); n++) {
            for (int p = 0; p < laby.getLengthY(); p++) {
                if (laby.getMur(n, p)) {
                    if (p > 0 && !laby.getMur(n, p - 1)) {
                        gc.drawImage(img_side_wall,n * size , (p * size) - size / 3, size, size / 3);
                    } else {
                        gc.drawImage(img_side_wall_extend,n * size, (p * size) - size, size, size);
                    }
                }
            }
        }
    }
}
