package gameLaby.laby;

abstract public class Entite {

	int x,y,atk,hp;

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

	public int getHP() {
		return hp;
	}

	public int getATK() {
		return atk;
	}

	public boolean estVivant(){
		return hp > 0;
	}

	public void setHP(int n){
		hp -= n;
	}
}
