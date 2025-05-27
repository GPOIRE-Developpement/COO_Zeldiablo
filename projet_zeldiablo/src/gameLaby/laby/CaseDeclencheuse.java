package gameLaby.laby;

/**
 * Interface CaseDeclencheuse permet de définir le modèle d'une CaseDeclencheuse
 * @author GPOIRE-Developpement
 */
public interface CaseDeclencheuse {
    /**
     * Méthode activer est lancée lorsque l'entité en paramètre est sur la case
     * @param ent
     */
    public void activer(Entite ent);
}
