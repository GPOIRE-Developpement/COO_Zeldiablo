package gameLaby.entites;

abstract public class Entite {

	/**
	 * constantes actions possibles
	 */
	public static final String HAUT = "Haut";
	public static final String BAS = "Bas";
	public static final String GAUCHE = "Gauche";
	public static final String DROITE = "Droite";

	private String position = "Bas";

	protected int x,y,atk,hp;

	public Entite(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * permet de savoir si l'entité' est en x,y
	 *
	 * @param dx position testee
	 * @param dy position testee
	 * @return true si l'entité est bien en (dx,dy)
	 */
	public boolean etrePresent(int dx, int dy) {
		return (this.x == dx && this.y == dy);
	}


	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}

	public int getHP() {
		return hp;
	}

	public int getATK() {
		return atk;
	}

	public boolean estVivant(){
		return hp > 0;
	}

	public void setHP(int n){ this.hp = hp + n; }

	public void setAtk(int n){
		this.atk = atk + n;
	}

	public String getPosition() {
		return position;
	}

	/**
	 * retourne la case suivante selon une actions
	 *
	 * @param x      case depart
	 * @param y      case depart
	 * @param action action effectuee
	 * @return case suivante
	 */
	static int[] getSuivant(int x, int y, String action) {
		switch (action) {
			case HAUT:
				// on monte une ligne
				y--;
				break;
			case BAS:
				// on descend une ligne
				y++;
				break;
			case DROITE:
				// on augmente colonne
				x++;
				break;
			case GAUCHE:
				// on augmente colonne
				x--;
				break;
			default:
				throw new Error("action inconnue");
		}
		int[] res = {x, y};
		return res;
	}



	public int[] deplacer(String action) {
		// case courante
		int[] courante = {this.x, this.y};

		this.position = action;

		// calcule case suivante
        return getSuivant(courante[0], courante[1], action);
	}


	public abstract void attaquer(Entite entite);


	/**
	 * Méthode permettant de vérifier qu'une entité peut attaquer
	 * @param entite
	 * @return boolean
	 */
	public boolean peutAttaquer(Entite entite) {
		int posX = entite.getX();
		int posY = entite.getY();
		return (this.x == posX) && (this.y - 1 == posY) || (this.x == posX) && (this.y + 1 == posY) || (this.x - 1 == posX) && (this.y == posY) || (this.x + 1 == posX) && (this.y == posY);
	}
}
