package gameLaby.laby;

/**
 * Classe Sortie implements CaseDeclencheuse et définit la case qui permet de finir le niveau
 * @author GPOIRE-Developpement
 */
public class Sortie implements CaseDeclencheuse{
    /**
     * Méthode activer définit les actions à faire lorsqu'un joueur entre sur la case de fin
     * @param ent
     */
    public void activer(Entite ent){
        if(!(ent instanceof Perso)) return;
        System.out.println("Vous avez terminé");
    }

    public String getType() {
        return "sortie";
    }
}
