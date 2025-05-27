package gameLaby.laby;

/**
 * Classe CasePiege implements CaseDeclencheuse et définit les actions d'une case piégée
 * @author GPOIRE-Developpement
 */
public class CasePiege implements CaseDeclencheuse{
    /**
     * Méthode activer définit les actions à faire lorsqu'une entité est sur une case piégée
     * @param ent
     */
    public void activer(Entite ent){
        System.out.println("Vous êtes sur une case piégée !");
    }
}
