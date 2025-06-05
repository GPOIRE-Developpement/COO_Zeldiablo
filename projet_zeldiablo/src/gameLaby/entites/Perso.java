package gameLaby.entites;
import gameLaby.LabyJeu;
import gameLaby.objets.Bouclier;
import gameLaby.objets.Epee;
import gameLaby.objets.Objet;
import gameLaby.objets.Potion;
import moteurJeu.MoteurJeu;

import java.util.ArrayList;
import java.util.List;

/**
 * gere un personnage situe en x,y
 */
public class Perso extends Entite {

    /**
     * Liste d'objet qui représente l'inventaire du joueur
     */
    private ArrayList<Objet> inventaire = new ArrayList<>();

    /**
     * Représente l'objet qui est séléctionné dans l'inventaire
     */
    private Objet itemSelecte;

    /**
     * Représente le bouclier du personnage
     */
    private Bouclier bouclier;

    private int vieDeBase;
    /**
     * constructeur
     *
     * @param dx position selon x
     * @param dy position selon y
     */
    public Perso(int dx, int dy) {
        super(dx, dy);
    }

    /**
     * Méthode permettant d'interagir avec les objets présent dans le labyrinthe ou dans l'inventaire
     * @param objet Liste des objets d'un labyrinthe
     */
    public void interagir(List<Objet> objet) {
        Objet objetAttraper = null;
        boolean objetPied = false;

        for (Objet obj : objet){
            if (this.getX() == obj.getX() && this.getY() == obj.getY()){
                if (inventaire.size() < 4){
                    objetAttraper = obj;
                }
                objetPied = true;
            }
        }

        if (this.itemSelecte instanceof Potion && !objetPied){
            if (!((Potion) this.itemSelecte).isUsed() ){
                int vie = this.getHP() + ((Potion) this.itemSelecte).getVie();
                if (vie > vieDeBase){
                    vie = vieDeBase;
                }
                this.setHp(vie);
                ((Potion) this.itemSelecte).utliser();
            }
        }

        if (objetAttraper!= null) {
            if (objetAttraper instanceof Bouclier) {
                this.bouclier = (Bouclier) objetAttraper;
                objet.remove(objetAttraper);
            } else {
                inventaire.add(objetAttraper);
                objet.remove(objetAttraper);
            }
        } else {
            if (this.itemSelecte != null && !objetPied) {
                inventaire.remove(itemSelecte);
                itemSelecte.setX(this.getX());
                itemSelecte.setY(this.getY());
                objet.add(itemSelecte);
                itemSelecte = null;
            }
        }

        if (inventaire.isEmpty()){
            itemSelecte = null;
        }

    }

    /**
     * Permet de séléctionner un objet présent dans l'inventaire
     * @param indice indice de l'objet à selectionner
     */
    public void selectionnerObjet(int indice) {
        if (indice >= 1 && indice <= inventaire.size()) {
            itemSelecte = inventaire.get(indice-1);
        }
    }

    /**
     * @return retourne l'objet qui est séléctionné dans l'inventaire
     */
    public Objet getItemSelecte() {
        return itemSelecte;
    }

    /**
     * @return retourne l'inventaire du personnage
     */
    public ArrayList<Objet> getInventaire() {
        return inventaire;
    }

    /**
     * Permet au personnage d'attaquer
     * @param entite l'entite à attaquer
     */
    public void attaquer(Entite entite) {
        if (itemSelecte != null && itemSelecte.getNom().equals("sword")) {
            entite.subirDegat(-(atk + ((Epee)itemSelecte).getDommage()));
        } else {
            entite.subirDegat(-atk);
        }
    }

    /**
     * Modifie le nombre d'Hp du perso
     * @param n Nouveaux Hp
     */
    @Override
    public void subirDegat(int n){
        if (this.bouclier != null){
            int degat = this.bouclier.getDef() + n;
            this.bouclier.setDef(degat);
            if (degat < 0) {
                super.subirDegat(degat);
            }
            if (this.bouclier.getDef() <= 0){
                this.bouclier = null;
            }
        } else {
            super.subirDegat(n);
            if(!super.estVivant()){
                MoteurJeu.GameOver();
            }
        }
        System.out.println("Vous avez " + hp + "Points de vie");
    }

    public void setHp(int hp) {
        this.hp = hp;
        vieDeBase = hp;
    }

    public Bouclier getBouclier() {
        return bouclier;
    }

    public String getName() {
        return "personnage";
    }
}
