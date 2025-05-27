package gameLaby.laby;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * classe labyrinthe. represente un labyrinthe avec
 * <ul> des murs </ul>
 * <ul> un personnage (x,y) </ul>
 */
public class Labyrinthe {

    /**
     * Constantes char
     */
    public static final char MUR = 'X';
    public static final char PJ = 'P';
    public static final char VIDE = '.';
    public static final char MONSTER = 'M';

    /**
     * attribut du personnage
     */
    public Perso pj;

    /**
     * attribut de monstre
     */
    public ArrayList<Entite> monstres;

    /**
     * les murs du labyrinthe
     */
    public boolean[][] murs;

    /**
     * Les objets du labyrinthe
     */
    public ArrayList<Objet> objets;

    /**
     * les cases à action du labyrinthe
     * CaseDeclencheuse[x][y]
     */
    public CaseDeclencheuse[][] cases;

    public int nbMonstre = 3;



    /**
     * charge le labyrinthe
     *
     * @param nom nom du fichier de labyrinthe
     * @return labyrinthe cree
     * @throws IOException probleme a la lecture / ouverture
     */
    public Labyrinthe(String nom) throws IOException {
        // ouvrir fichier
        FileReader fichier = new FileReader(nom);
        BufferedReader bfRead = new BufferedReader(fichier);

        int nbLignes, nbColonnes;
        // lecture nblignes
        nbLignes = Integer.parseInt(bfRead.readLine());
        // lecture nbcolonnes
        nbColonnes = Integer.parseInt(bfRead.readLine());

        // creation labyrinthe vide
        this.murs = new boolean[nbColonnes][nbLignes];
        this.pj = null;
        this.monstres = new ArrayList<>();
        this.cases = new CaseDeclencheuse[nbColonnes][nbLignes];
        // lecture des cases
        String ligne = bfRead.readLine();

        // stocke les indices courants
        int numeroLigne = 0;

        // parcours le fichier
        while (ligne != null) {

            // parcours de la ligne
            for (int colonne = 0; colonne < ligne.length(); colonne++) {
                char c = ligne.charAt(colonne);
                switch (c) {
                    case MUR:
                        this.murs[colonne][numeroLigne] = true;
                        break;
                    case VIDE:
                        this.murs[colonne][numeroLigne] = false;
                        break;
	                case PJ:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        // ajoute PJ
                        this.pj = new Perso(colonne, numeroLigne);
                        break;
                    case CaseDeclencheuse.PIEGE :
                        CasePiege piege = new CasePiege();
                        cases [colonne][numeroLigne] = piege;
                        break;
                    case Objet.EPEE:
                        Epee epee = new Epee("Epee en bois", 3, colonne, numeroLigne);
                        objets.add(epee);
                        break;
                    case Objet.BOUCLIER:
                        Bouclier bouclier = new Bouclier("Bouclier en bois", 3, colonne, numeroLigne);
                        objets.add(bouclier);
                        break;
                    default:
                        throw new Error("caractere inconnu " + c);
                }
            }

            // lecture
            ligne = bfRead.readLine();
            numeroLigne++;
        }

        // ferme fichier
        bfRead.close();
        generationMonstre(nbColonnes, nbLignes);
    }


    /**
     * deplace le personnage en fonction de l'action.
     * gere la collision avec les murs
     *
     * @param action une des actions possibles
     */
    public void deplacerPerso(String action) {

        // calcule case suivante
        int[] suivante = pj.deplacer(action);

        boolean monstrePresent = false;
        for (Entite entite : monstres) {
            if (suivante[0] == entite.x && suivante[1] == entite.y) {
                monstrePresent = true;
            }
        }

        // si c'est pas un mur, on effectue le deplacement
        if (!this.murs[suivante[0]][suivante[1]] && !monstrePresent) {
            // on met a jour personnage
            this.pj.x = suivante[0];
            this.pj.y = suivante[1];
            estSurCase(pj);
        }
        for (Entite entite : monstres) {
            String[] direction = {Entite.GAUCHE, Entite.DROITE, Entite.HAUT, Entite.BAS};
            for (Entite entite2 : monstres) {
                entite2.deplacer(direction[(int)Math.floor(Math.random() * direction.length)]);
            }
        }
    }





    /**
     * jamais fini
     *
     * @return fin du jeu
     */
    public boolean etreFini() {
        return false;
    }

    // ##################################
    // GETTER
    // ##################################

    /**
     * return taille selon Y
     *
     * @return
     */
    public int getLengthY() {
        return murs[0].length;
    }

    /**
     * return taille selon X
     *
     * @return
     */
    public int getLength() {
        return murs.length;
    }

    /**
     * return mur en (i,j)
     * @param x
     * @param y
     * @return
     */
    public boolean getMur(int x, int y) {
        // utilise le tableau de boolean
        return this.murs[x][y];
    }

    /**
     * Vérifier si l'entité est sur une case à effet
     * @param ent
     */
    public void estSurCase(Entite ent){
        CaseDeclencheuse caseDeclencheuse = cases[ent.getX()][ent.getY()];
        if (caseDeclencheuse != null) {
            caseDeclencheuse.activer(ent);
        }
    }

    public ArrayList<Entite> getMonstres() {
        return monstres;
    }

    public CaseDeclencheuse[][] getCase() {
        return cases;
    }

    public void generationMonstre(int nbColonne, int nbLigne) {
        for (int i = 0; i < nbMonstre; i++) {
            int x = (int) Math.floor(Math.random()*nbColonne);
            int y = (int) Math.floor(Math.random()*nbLigne);
            while (this.murs[x][y] || pj.x == x && pj.y == y) {
                x = (int) Math.floor(Math.random()*nbColonne);
                y = (int) Math.floor(Math.random()*nbLigne);
            }
            Monstre monstre = new Monstre(x, y);
            monstres.add(monstre);
        }
    }
}
