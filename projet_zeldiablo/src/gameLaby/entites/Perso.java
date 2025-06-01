package gameLaby.entites;
import gameLaby.objets.Epee;
import gameLaby.objets.Objet;

import java.util.ArrayList;

/**
 * gere un personnage situe en x,y
 */
public class Perso extends Entite {

    private ArrayList<Objet> inventaire = new ArrayList<>();
    private Objet itemSelecte;
    /**
     * constructeur
     *
     * @param dx position selon x
     * @param dy position selon y
     */
    public Perso(int dx, int dy) {
        super(dx, dy);
        inventaire.add(new Epee("epee",10,10,10));
        inventaire.add(new Epee("epee",10,10,10));
    }

    public void selectionnerObjet(int indice) {
        if (indice >= 1 && indice <= inventaire.size()) {
            itemSelecte = inventaire.get(indice-1);
        }
    }

    public Objet getItemSelecte() {
        return itemSelecte;
    }

    public ArrayList<Objet> getInventaire() {
        return inventaire;
    }
}
