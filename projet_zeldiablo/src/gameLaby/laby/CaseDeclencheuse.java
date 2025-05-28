package gameLaby.laby;

/**
 * Interface CaseDeclencheuse permet de définir le modèle d'une CaseDeclencheuse
 * @author GPOIRE-Developpement
 */
public interface CaseDeclencheuse {

    public final char SORTIE = 'S';
    public final char PIEGE = 'T';//trap
    public final char INTERRUPTEUR = 'I';

    final boolean active = false;

    public String getType();
    /**
     * Méthode activer est lancée lorsque l'entité en paramètre est sur la case
     * @param ent
     */
    public void activer(Entite ent);
}
