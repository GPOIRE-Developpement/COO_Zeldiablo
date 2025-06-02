package gameLaby;

import gameLaby.casesSpe.*;
import gameLaby.entites.Entite;
import gameLaby.entites.Fantome;
import gameLaby.entites.Fantome;
import gameLaby.entites.Monstre;
import gameLaby.entites.Perso;
import gameLaby.objets.Bouclier;
import gameLaby.objets.Epee;
import gameLaby.objets.Objet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	public static final char PORTE = 'D';
	public static final char SORTIE = 'S';

	/**
	 * attribut du personnage
	 */
	private Perso pj;

	/**
	 * attribut de monstre
	 */
	private ArrayList<Entite> monstres;

	/**
	 * les murs du labyrinthe
	 */
	private boolean[][] murs;

	/**
	 * Les objets du labyrinthe
	 */
	private ArrayList<Objet> objets;

	/**
	 * les cases à action du labyrinthe
	 * CaseDeclencheuse[x][y]
	 */
	private CaseDeclencheuse[][] cases;

	/**
	 * nombre de monstres voulus
	 */
	private int nbMonstre = 4;

    private int nbFantome = 2;


	private ArrayList<Porte> portes;

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
		this.objets = new ArrayList<>();
		this.portes = new ArrayList<>();

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
						pj.setHP(10);
						pj.setAtk(1);
						break;
					case CaseDeclencheuse.PIEGE:
						this.murs[colonne][numeroLigne] = false;
						CasePiege piege = new CasePiege();
						cases[colonne][numeroLigne] = piege;
						break;
					case Objet.EPEE:
						this.murs[colonne][numeroLigne] = false;
						Epee epee = new Epee("sword", 3, colonne, numeroLigne);
						objets.add(epee);
						break;
					case Objet.BOUCLIER:
						this.murs[colonne][numeroLigne] = false;
						Bouclier bouclier = new Bouclier("shield", 3, colonne, numeroLigne);
						objets.add(bouclier);
						break;
					case CaseDeclencheuse.INTERRUPTEUR:
						this.murs[colonne][numeroLigne] = false;
						cases[colonne][numeroLigne] = new Interrupteur();
						break;
					case PORTE:
						this.murs[colonne][numeroLigne] = false;
						if (this.murs[colonne - 1][numeroLigne]) {
							portes.add(new Porte(colonne, numeroLigne, false));
						} else {
							portes.add(new Porte(colonne, numeroLigne, true));
						}
						break;
					case SORTIE:
						this.murs[colonne][numeroLigne] = false;
						cases[colonne][numeroLigne] = new Sortie();
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
		associerPorteInt(nbColonnes, nbLignes);
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

		boolean deplacementPossible = isEmpty(suivante[0], suivante[1]);

		// si c'est pas un mur, on effectue le deplacement
		if (deplacementPossible) {
			// on met a jour personnage
			this.pj.setX(suivante[0]);
			this.pj.setY(suivante[1]);
			estSurCase(pj);
		}
	}

	//booleen permettant de déplacer les monstre 1 unpdate sur 2 (on gérera avec un timer sans doute après)
	private boolean deplacement = true;

	/**
	 * Méthode permettant de déplacer toutes les entités de la liste monstres
	 */
	public void deplacerMonstre() {
		if (deplacement) {
			boolean deplacementPossible;
			String[] direction = {Entite.GAUCHE, Entite.DROITE, Entite.HAUT, Entite.BAS};
			List<Entite> morts = new ArrayList<>();
			for (Entite entite : monstres) {
				if(!entite.estVivant()){
					morts.add(entite);
					continue;
				}
				int[] position = entite.deplacer(direction[(int) Math.floor(Math.random() * direction.length)]);
				deplacementPossible = isEmpty(position[0],position[1]);
				if (deplacementPossible) {
					// on met a jour le monstre
					entite.setX(position[0]);
					entite.setY(position[1]);
					estSurCase(entite);
				}
				if(entite.peutAttaquer(pj)) {
					entite.attaquer(pj);
				}

			}
			deplacement = false;
			for(Entite ent : morts){
				monstres.remove(ent);
			}
		} else {
			deplacement = true;
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
	 * @return la taille du laby selon Y (le nombre de ligne)
	 */
	public int getLengthY() {
		return murs[0].length;
	}

	/**
	 * return taille selon X
	 *
	 * @return la taille du laby selon X (le nombre de colonne)
	 */
	public int getLength() {
		return murs.length;
	}

	/**
	 * return mur en (i,j)
	 *
	 * @param x indice de la colonne
	 * @param y indice de la ligne
	 * @return boolean, vrai si c'est un mur, faux sinon
	 */
	public boolean getMur(int x, int y) {
		// utilise le tableau de boolean
		return this.murs[x][y];
	}

	/**
	 * retourne le perso du laby
	 *
	 * @return un Perso
	 */
	public Perso getPj() {
		return pj;
	}

	/**
	 * retourne la liste des objets du laby
	 *
	 * @return une liste d'Objets
	 */
	public ArrayList<Objet> getObjets() {
		return objets;
	}

	/**
	 * Vérifier si l'entité est sur une case à effet
	 *
	 * @param ent entité sur la case
	 */
	public void estSurCase(Entite ent) {
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

	/**
	 * Methode permettant de gérer la génération aléatoire des monstres
	 *
	 * @param nbColonne nombre de colonnes du laby
	 * @param nbLigne   nombre de lignes du laby
	 */
	public void generationMonstre(int nbColonne, int nbLigne) {
		for (int i = 0; i < nbMonstre; i++) {
			int x = (int) Math.floor(Math.random() * nbColonne);
			int y = (int) Math.floor(Math.random() * nbLigne);
			while (this.murs[x][y] || pj.getX() == x && pj.getY() == y) {
				x = (int) Math.floor(Math.random() * nbColonne);
				y = (int) Math.floor(Math.random() * nbLigne);
			}
			Monstre monstre = new Monstre(x, y);
			monstres.add(monstre);
		}
	}

	/**
	 * Methode permettant d'associer des interrupteurs avec des portes
	 *
	 * @param nbColonnes nombre de colonnes du laby (parcours de cases)
	 * @param nbLignes   nombre de lignes du laby (parcours de cases)
	 */
	public void associerPorteInt(int nbColonnes, int nbLignes) {
		int nbPortes = 0;
		for (int i = 0; i < nbColonnes; i++) {
			for (int j = 0; j < nbLignes; j++) {
				if (cases[i][j] != null) {
					if (cases[i][j] instanceof Interrupteur) {
						((Interrupteur) cases[i][j]).setPorte(portes.get(nbPortes));
						nbPortes++;
					}
				}
			}
		}
		if (nbPortes != portes.size()) {
			throw new Error("Nombre de portes différent du nombre d'interrupteur");
		}
	}

	/**
	 * retourne la liste des portes du labyrinthe
	 *
	 * @return la liste des portes
	 */
	public ArrayList<Porte> getPortes() {
		return portes;
	}

	/**
	 * Methode permettant de vérifier si il y a quelque chose à la case en position x,y
	 *
	 * @param x position en x
	 * @param y position en y
	 * @return boolean, true si c'est vide, false sinon
	 */
	public boolean isEmpty(int x, int y) {
		if ((this.pj.getX() == x && this.pj.getY() == y) || getMur(x, y)) {
			return false;
		}
		for (Entite mstr : monstres) {
			if (mstr.getX() == x && mstr.getY() == y) {
				return false;
			}
		}
        for (Porte porte : portes) {
            if ((porte.getX() == x && porte.getY() == y) && !porte.getOuverte()) {
                return false;
            }
        }
		return true;
	}

	public boolean isEmptyFant(int x, int y) {
		if ((this.pj.getX() == x && this.pj.getY() == y)) {
			return false;
		}
		for (Entite mstr : monstres) {
			if (mstr.getX() == x && mstr.getY() == y) {
				return false;
			}
		}
		if (x < 0 || y < 0 || x > murs.length - 1 || y > murs[0].length - 1) {
			return false;
		}
		return true;
	}

	/**
	 * Methode permettant de gérer la génération aléatoire des fantômes
	 *
	 * @param nbColonne nombre de colonnes du laby
	 * @param nbLigne   nombre de lignes du laby
	 */
	public void generationFantome(int nbColonne, int nbLigne) {
		for (int i = 0; i < nbFantome; i++) {
			int x = (int) Math.floor(Math.random() * nbColonne);
			int y = (int) Math.floor(Math.random() * nbLigne);
			while (this.murs[x][y] || pj.getX() == x && pj.getY() == y) {
				x = (int) Math.floor(Math.random() * nbColonne);
				y = (int) Math.floor(Math.random() * nbLigne);
			}
			Fantome fantome = new Fantome(x, y);
			monstres.add(fantome);
		}
	}
}
