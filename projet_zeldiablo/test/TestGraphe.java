import gameLaby.Labyrinthe;
import gameLaby.graphe.*;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestGraphe {
    @Test
    public void testGraphe() throws IOException {

        Labyrinthe lab = new Labyrinthe("labySimple/laby2.txt", null);
        Graphe g = new Graphe(lab.getMursLaby(), lab.getCase(), lab.getPortes());

        Position pos = g.resourdre(new Position(3, 4), new Position(17, 12));
        assertEquals("Problème avec la génération intéligente de parcours en X", 2, pos.getX());
        assertEquals("Problème avec la génération intéligente de parcours en Y", 4, pos.getY());
    }
}
