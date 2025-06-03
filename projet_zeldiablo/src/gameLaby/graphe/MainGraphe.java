package gameLaby.graphe;

import gameLaby.Labyrinthe;
import gameLaby.casesSpe.Porte;

import java.io.IOException;

public class MainGraphe {
    public static void main(String[] args) throws IOException {
        Labyrinthe lab = new Labyrinthe("labySimple/laby2.txt", null);

        lab.getMursLaby();

        Graphe g = new Graphe(lab.getMursLaby(), lab.getCase(), lab.getPortes());
        Position dep = new Position(lab.getPj().getX(), lab.getPj().getY());

        System.out.println(g.resourdre(dep, new Position(3, 2)));
    }
}
