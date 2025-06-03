package gameLaby.casesSpe;

import gameLaby.LabyJeu;
import gameLaby.entites.Entite;
import gameLaby.entites.Perso;

/**
 * Classe Sortie implements CaseDeclencheuse et définit la case qui permet de finir le niveau
 * @author GPOIRE-Developpement
 */
public class Sortie implements CaseDeclencheuse {

    private boolean monter;

    public Sortie(boolean monter) {
        this.monter = monter;
    }

    /**
     * Méthode activer définit les actions à faire lorsqu'un joueur entre sur la case de fin
     * @param ent
     */
    public void activer(Entite ent) {
        if (ent instanceof Perso) {
            if (monter) {
                try {
                    LabyJeu.niveauSuivant((Perso) ent);
                } catch (Exception e) {
                    LabyJeu.jeuFini();
                }
            } else {
                LabyJeu.niveauPrec((Perso) ent);
            }
        }
    }

    public String getType() {
        return "sortie";
    }

    public boolean getMonter() {
        return monter;
    }
}
