package gameLaby.graphe;

import gameLaby.casesSpe.CaseDeclencheuse;
import gameLaby.casesSpe.Porte;

import java.util.ArrayList;
import java.util.List;

public class Graphe {
    private List<Position> noeuds;
    private List<Arcs> adjacents;

    private List<Position> posPortes;
    private List<Porte> portes;
    private List<Arcs> passePortes;

    public Graphe(boolean[][] m, CaseDeclencheuse[][] c, ArrayList<Porte> p){
        noeuds = new ArrayList<>();
        adjacents = new ArrayList<>();

        posPortes = new ArrayList<>();
        portes = new ArrayList<>();
        passePortes = new ArrayList<>();

        List<Position> listeC = new ArrayList<>();
        List<Position> listeP = new ArrayList<>();

        for(int i = 0; i < c.length; i++){
            for(int j = 0; j < c[i].length; j++){
                if(c[i][j] == null) continue;
                if(c[i][j].getType() != "piege") continue;

                listeC.add(new Position(i,j));
            }
        }

        for(int i = 0; i < p.size(); i++){
            Porte porte = p.get(i);

            listeP.add(new Position(porte.getX(), porte.getY()));
        }

        for(int i = 0; i < m.length; i++){
            for(int j = 0; j < m[i].length; j++){
                if(m[i][j]) continue;

                Position p1 = new Position(i, j);
                if(i+1 < m.length){
                    Position p2 = new Position(i+1, j);

                    creerArc(p1, p2, listeC, listeP, p);
                }

                if(j+1 < m[i].length){
                    Position p2 = new Position(i, j+1);

                    creerArc(p1, p2, listeC, listeP, p);
                }
            }
        }
    }

    private void creerArc(Position p1, Position p2, List<Position> listeC, List<Position> listeP, ArrayList<Porte> p){
        int cout = 1;
        if(listeC.contains(p2)) cout = 5;

        Arc arc1 = ajouterArc(p1, p2, 1);
        Arc arc2 = ajouterArc(p2, p1, 1);

        if(listeP.contains(p1) ||listeP.contains(p2)){
            Position p3;
            if (listeP.contains(p1)) {
                p3 = new Position(p1.getX(), p1.getY());
            } else {
                p3 = new Position(p2.getX(), p2.getY());
            }

            for (int i = 0; i < p.size(); i++) {
                Porte porte = p.get(i);
                if (p3.getX() == porte.getX() && p3.getY() == porte.getY()) {
                    int j = creerPorte(porte);

                    passePortes.get(j).ajouterArc(arc1);
                    passePortes.get(j).ajouterArc(arc2);
                }
            }
        }
    }

    private boolean noeudExiste(Position pos){
        return noeuds.contains(pos);
    }

    private boolean porteExiste(Position pos){
        return posPortes.contains(pos);
    }

    private int creerPorte(Porte p){
        Position pos = new Position(p.getX(), p.getY());
        if(!porteExiste(pos)){
            posPortes.add(pos);
            portes.add(p);
            passePortes.add(new Arcs());
        }

        return posPortes.indexOf(pos);
    }

    private void creerNoeud(Position pos){
        noeuds.add(pos);
        adjacents.add(new Arcs());
    }

    private int getIndice(Position pos){
        return noeuds.indexOf(pos);
    }

    public Arc ajouterArc(Position depart, Position arrivee, int cout){
        if(!noeudExiste(depart))
            creerNoeud(depart);
        if(!noeudExiste(arrivee))
            creerNoeud(arrivee);

        Arc arc = new Arc(arrivee, cout);
        adjacents.get(getIndice(depart)).ajouterArc(arc);

        return arc;
    }

    public Position resourdre(Position depart, Position arrivee){
        Valeurs v = new Valeurs();

        // Parcourir l'ensemble des portes pour définir leurs status
        for(int i = 0; i < portes.size(); i++){
            List<Arc> arcs = passePortes.get(i).getArcs();
            int cout = 0;
            if(portes.get(i).getOuverte()){
                cout = 1;
            }
            for(int j = 0; j < arcs.size(); j++){
                arcs.get(j).setCout(cout);
            }
        }

        // Calculer ensuite le trajet le plus court entre depart et arrivée

        for(int i = 0; i < this.noeuds.size(); i++){
            Position noeud = this.noeuds.get(i);
            v.setParent(noeud, depart);
            if(noeud.equals(depart)){
                v.setValeur(noeud, 0);
            }else{
                v.setValeur(noeud, Double.MAX_VALUE);
            }
        }

        boolean termine = false;
        while(!termine){
            termine = true;
            for (int i = 0; i < this.noeuds.size(); i++) {
                Position noeud = this.noeuds.get(i);
                List<Arc> suivant = this.suivants(this.noeuds.get(i));
                for (int j = 0; j < suivant.size(); j++) {
                    Arc adj = suivant.get(j);
                    int cout = adj.getCout();
                    if(cout == 0){
                        cout = Integer.MAX_VALUE;
                    }
                    double chemin = v.getValeur(noeud) + cout;
                    if (chemin < v.getValeur(adj.getDestination())) {
                        v.setValeur(adj.getDestination(), chemin);
                        v.setParent(adj.getDestination(), noeud);
                        termine = false;
                    }
                }
            }
        }

        List<Position> chemin = v.calculerChemin(arrivee);

        return chemin.get(1);
    }

    public List<Arc> suivants(Position p){
        int i = getIndice(p);
        return adjacents.get(i).getArcs();
    }

    @Override
    public String toString(){
        StringBuilder sB = new StringBuilder();

        for(int i = 0; i < noeuds.size(); i++){
            if(i!=0) sB.append("\n");
            sB.append(noeuds.get(i));
            sB.append(" -> ");
            sB.append(adjacents.get(i));
        }

        return sB.toString();
    }
}
