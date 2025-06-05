package gameLaby.casesSpe;

import gameLaby.entites.Entite;

/**
 * Classe CasePiege implements CaseDeclencheuse et définit les actions d'une case piégée
 * @author GPOIRE-Developpement
 */
public class CasePiege implements CaseDeclencheuse {

    private boolean active = false;

    /**
     * Méthode activer définit les actions à faire lorsqu'une entité est sur une case piégée
     * @param ent
     */
    public void activer(Entite ent){
        if (ent.estVivant()) {
            ent.subirDegat(-1);
        }
        active = true;
    }

    public String getType() {
        return "piege";
    }

    public boolean estActive() {
        return active;
    }

}
