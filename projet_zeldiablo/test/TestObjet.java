import gameLaby.objets.Bouclier;
import gameLaby.objets.Epee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour la classe objet et le
 */
public class TestObjet {

    /**
     * Méthode de test de la création d'une épée et du fonctionnement de ces méthodes
     */
    @Test
    public void testCreationEpee() {
        Epee epee = new Epee("Epee en Bois", 3, 3, 3);

        assertEquals("Epee en Bois", epee.getNom(), "Le nom de l'épée est incorrect.");
        assertEquals(3, epee.getDommage(), "Les dégâts de l'épée sont incorrects.");
        assertEquals(3, epee.getX(), "La position X de l'épée est incorrecte.");
        assertEquals(3, epee.getY(), "La position Y de l'épée est incorrecte.");
    }

    /**
     * Méthode de test de la création d'un bouclier et du fonctionnement de ces méthodes
     */
    @Test
    public void testCreationBouclier() {
        Bouclier bouclier = new Bouclier("Bouclier en Bois", 20, 3, 8);

        assertEquals("Bouclier en Bois", bouclier.getNom(), "Le nom du bouclier est incorrect.");
        assertEquals(20, bouclier.getDef(), "La résistance du bouclier est incorrecte.");
        assertEquals(3, bouclier.getX(), "La position X du bouclier est incorrecte.");
        assertEquals(8, bouclier.getY(), "La position Y du bouclier est incorrecte.");
    }
    
    /**
     * Méthode de test des constantes de objet
     */
    @Test
    public void testConstantesObjet() {
        assertEquals('E', Epee.EPEE, "Constante EPEE incorrecte.");
        assertEquals('B', Bouclier.BOUCLIER, "Constante BOUCLIER incorrecte.");
    }
}
