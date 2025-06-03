import gameLaby.entites.Perso;
import gameLaby.objets.Epee;
import gameLaby.objets.Objet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de teste de la méthode Interagir de Perso
 */
public class TestPerso {

    private Perso perso;
    private Epee epee1, epee2, epee3, epee4, epee5;

    /**
     * Méthode d'initialisation pour chaque Test
     */
    @BeforeEach
    public void setUp() {
        perso = new Perso(2, 2);
        epee1 = new Epee("sword1",5, 2, 2);
        epee2 = new Epee("sword2",5, 2, 2);
        epee3 = new Epee("sword3",5, 2, 2);
        epee4 = new Epee("sword4",5,2, 2);
        epee5 = new Epee("sword5",5,2, 2);
    }

    /**
     * Test la méthode interagir lorsqu'elle est utilisée pour ramassé un objet
     */
    @Test
    public void testInteragirRamasseObjet() {
        List<Objet> objets = new ArrayList<>();
        objets.add(epee1);

        perso.interagir(objets);

        assertEquals(1, perso.getInventaire().size(), "L'objet aurait dû être ajouté à l'inventaire.");
        assertTrue(perso.getInventaire().contains(epee1), "L'objet ramassé devrait être dans l'inventaire.");
        assertFalse(objets.contains(epee1), "L'objet aurait dû être retiré du sol.");
    }

    /**
     * Test la méthode interagir lorsqu'elle est utilisée pour ramassé un objet mais que l'inventaire est plein
     */
    @Test
    public void testInteragirInventairePlein() {
        List<Objet> objets = new ArrayList<>();
        objets.add(epee5);

        perso.getInventaire().add(epee1);
        perso.getInventaire().add(epee2);
        perso.getInventaire().add(epee3);
        perso.getInventaire().add(epee4);

        perso.interagir(objets);

        assertEquals(4, perso.getInventaire().size(), "L'inventaire ne doit pas dépasser 4 objets.");
        assertTrue(objets.contains(epee5), "L'objet au sol ne doit pas être ramassé.");
    }

    /**
     * Test la méthode interagir lorsqu'elle est utilisée pour lacher un objet
     */
    @Test
    public void testInteragirDeposerObjet() {
        List<Objet> objets = new ArrayList<>();

        perso.getInventaire().add(epee1);
        perso.selectionnerObjet(1);

        perso.interagir(objets);

        assertTrue(objets.contains(epee1), "L'objet sélectionné aurait dû être déposé.");
        assertFalse(perso.getInventaire().contains(epee1), "L'objet sélectionné ne devrait plus être dans l'inventaire.");
        assertEquals(2, epee1.getX(), "L'objet doit être déposé à la position X du perso.");
        assertEquals(2, epee1.getY(), "L'objet doit être déposé à la position Y du perso.");
    }

    /**
     * Test la méthode interagir lorsqu'elle est utilisée pour lacher un objet mais qu'il existe déjà un objet sur la case
     */
    @Test
    public void testInteragirDeposerObjetSurObjet() {
        List<Objet> objets = new ArrayList<>();
        objets.add(epee1);

        perso.getInventaire().add(epee2);
        perso.selectionnerObjet(1);

        perso.interagir(objets);

        assertTrue(perso.getInventaire().contains(epee2), "L'objet ne devrait pas être déposé si un autre est déjà au sol.");
        assertFalse(objets.contains(epee2), "L'inventaire ne doit pas avoir déposé l'objet.");
    }

    /**
     * Test la déselection Automatique lorsque l'inventaire est vide
     */
    @Test
    public void testDeselectionAutomatiqueSiInventaireVide() {
        List<Objet> objets = new ArrayList<>();

        perso.getInventaire().add(epee1);
        perso.selectionnerObjet(1);

        perso.interagir(objets);

        assertNull(perso.getItemSelecte(), "L'objet sélectionné devrait être null si l'inventaire est vide.");
    }
}
