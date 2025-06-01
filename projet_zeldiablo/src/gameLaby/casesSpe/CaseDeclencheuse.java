package gameLaby.casesSpe;

import gameLaby.entites.Entite;

/**
 * Interface CaseDeclencheuse permet de définir le modèle d'une CaseDeclencheuse
 * @author GPOIRE-Developpement
 */
public interface CaseDeclencheuse {

    public static final char SORTIE = 'S';
    public static final char PIEGE = 'T';//trap
    public static final char INTERRUPTEUR = 'I';

    public String getType();
    /**
     * Méthode activer est lancée lorsque l'entité en paramètre est sur la case
     * @param ent entité présente sur la case declencheuse
     */
    public void activer(Entite ent);
}
