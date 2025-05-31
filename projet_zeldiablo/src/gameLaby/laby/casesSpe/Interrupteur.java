package gameLaby.laby.casesSpe;

import gameLaby.laby.entites.Entite;
import gameLaby.laby.entites.Perso;

public class Interrupteur implements CaseDeclencheuse {

    Porte porte;

    public void activer(Entite ent) {
        if (ent instanceof Perso) {
            if (porte.getOuverte()) {
                porte.fermer();
            } else {
                porte.ouvrir();
            }
        }
    }



    public Interrupteur() {
        this.porte = null;
    }

    public void setPorte(Porte p) {
        this.porte = p;
    }

    public String getType() {
        return "interrupteur";
    }

}
