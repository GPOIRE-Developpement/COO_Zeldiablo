package gameLaby.casesSpe;

import gameLaby.entites.Entite;
import gameLaby.entites.Perso;

public class Interrupteur implements CaseDeclencheuse {

    Porte porte;

    private boolean isActive = false;

    public void activer(Entite ent) {
        isActive = true;
        if (ent instanceof Perso) {
            if (porte.getOuverte()) {
                porte.fermer();
            } else {
                porte.ouvrir();
            }
        }
        isActive = false;
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

    public boolean getActive() {
        return isActive;
    }

}
