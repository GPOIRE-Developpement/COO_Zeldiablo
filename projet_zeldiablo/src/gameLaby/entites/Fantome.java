package gameLaby.entites;

public class Fantome extends Entite {
	/**
	 * constructeur
	 *
	 * @param posx position selon x
	 * @param posy position selon y
	 */
	public Fantome(int posx, int posy) {
		super(posx, posy);
		this.atk = 1;
		this.hp = 5;
	}

	@Override
	public void attaquer(Entite entite) {
		entite.subirDegat(-1);
	}

}
