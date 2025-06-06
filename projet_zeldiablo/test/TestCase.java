import gameLaby.LabyJeu;
import gameLaby.MainLaby;
import gameLaby.casesSpe.CasePiege;
import gameLaby.casesSpe.Interrupteur;
import gameLaby.casesSpe.Porte;
import gameLaby.casesSpe.Sortie;
import gameLaby.entites.Perso;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestCase {

    @Test
    public void joueurSurCasePiegee(){
        Perso perso = new Perso(5, 9);
        perso.setHp(5);
        CasePiege casePiege = new CasePiege();

        assertEquals("Un problème est survenu lors de l'initialisation de la vie", 5, perso.getHP());

        casePiege.activer(perso);

        assertEquals("Un problème est survenu lorsque le joueur est passé sur la case", 4, perso.getHP());
    }

    @Test
    public void joueurSurInterrupteur(){
        Perso perso = new Perso(5, 9);
        Porte porte = new Porte(5, 9, true);
        Interrupteur interrupteur = new Interrupteur();
        interrupteur.setPorte(porte);

        assertEquals("La porte est fermée", false, porte.getOuverte());

        interrupteur.activer(perso);

        assertEquals("La porte est ouverte", true, porte.getOuverte());
    }

    @Test
    public void joueurSurSortie() throws Exception {
        Perso perso = new Perso(5, 9);
        Sortie sortie = new Sortie(true);

        List<String> niveaux = new ArrayList<>();
        niveaux.add("labySimple/niveau1.txt");
        niveaux.add("labySimple/niveau2.txt");

        LabyJeu labyJeu = new LabyJeu(niveaux);

        assertEquals("Un problème lors de l'initialisation du jeu", 0, labyJeu.getNiveau());

        sortie.activer(perso);

        assertEquals("Un problème lors de la sortie du personnage", 1, labyJeu.getNiveau());
    }
}