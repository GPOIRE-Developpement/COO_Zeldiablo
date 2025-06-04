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
	}

	@Override
	public void attaquer(Entite entite) {
		entite.subirDegat(-1);
	}

	public String getName() {
		return "monstre";
	}


}
