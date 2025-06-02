package gameLaby.entites;


/**
 * gere un monstre situe en x,y
 */
public class Monstre extends Entite {


	/**
	 * constructeur
	 *
	 * @param posx position selon x
	 * @param posy position selon y
	 */
	public Monstre(int posx, int posy) {
		super(posx, posy);
		this.atk = 1;
		this.hp = 5;
	}

	@Override
	public void attaquer(Entite entite) {
		entite.setHP(-1);
	}


}
