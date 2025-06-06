package moteurJeu;

import gameLaby.LabyJeu;
import javafx.scene.input.KeyEvent;

public class Clavier {

    /**
     * controle appuyes
     */
    public boolean haut, bas, gauche, droite, interaction, attaquer;
    private int itemSelectionne = 0;

    /**
     * stocke les commandes
     *
     * @param event evenement clavier
     */
    public void appuyerTouche(KeyEvent event) {

        switch (event.getCode()) {

            // si touche bas
            case S :
            case DOWN :
                this.bas = true;
                break;

            // si touche haut
            case Z:
                case UP :
                this.haut = true;
                break;

            // si touche gauche
            case Q:
            case LEFT:
                this.gauche = true;
                break;

            // si touche droite
            case D:
                case RIGHT:
                this.droite = true;
                break;

            case DIGIT1:
                itemSelectionne= 1;
                System.out.println(event.getCode());
                break;

            case DIGIT2:
                itemSelectionne = 2;
                System.out.println(event.getCode());
                break;

            case DIGIT3:
                itemSelectionne = 3;
                System.out.println(event.getCode());
                break;

            case DIGIT4:
                itemSelectionne = 4;
                System.out.println(event.getCode());
                break;

            case E:
                LabyJeu.interagir();
                break;

            case SPACE:
                LabyJeu.pjAttaque();
                break;

            case A:
                LabyJeu.sortir();
        }
    }

    /**
     * stocke les commandes
     *
     * @param event evenement clavier
     */
    public void relacherTouche(KeyEvent event) {

        switch (event.getCode()) {

            // si touche bas
            case S:
                case DOWN :
                this.bas = false;
                break;

            // si touche haut
            case Z:
                case UP :
                this.haut = false;
                break;

            // si touche gauche
            case Q:
                case LEFT:
                this.gauche = false;
                break;

            // si touche droite
            case D:
                case RIGHT:
                this.droite = false;
                break;

        }
    }

    /**
     * Methode permettant de récupérer l'item sélectionné
     * @return l'entier correspondant à l'item selectionné
     */
    public int getItemSelectionne() {
        return itemSelectionne;
    }

    public void reset() {
        gauche = false;
        droite = false;
        attaquer = false;
        bas = false;
        haut = false;
        interaction = false;
    }
}
