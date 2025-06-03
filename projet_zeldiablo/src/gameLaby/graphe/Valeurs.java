package gameLaby.graphe;

import java.util.*;

public class Valeurs {

    Map<Position, Double> valeur;
    Map<Position, Position> parent;

    public Valeurs() {
        this.valeur = new TreeMap<>();
        this.parent = new TreeMap<>();
    }

    public void setValeur(Position pos, double valeur) {
        this.valeur.put(pos, valeur);
    }

    public void setParent(Position pos, Position posParent) {
        this.parent.put(pos, posParent);
    }

    public Position getParent(Position pos) {
        return this.parent.get(pos);
    }

    public double getValeur(Position pos) {
        return this.valeur.get(pos);
    }

    @Override
    public String toString() {
        String res = "";
        for (Position s : this.valeur.keySet()) {
            Double valeurNoeud = valeur.get(s);
            Position noeudParent = parent.get(s);
            res += s + " -> V:" + valeurNoeud + " p:" + noeudParent + "\n";

        }
        return res;
    }

    public List<Position> calculerChemin(Position posDest){
        boolean depart = false;
        List<Position> chemin = new ArrayList<>();
        chemin.add(posDest);
        Position last = this.getParent(posDest);
        while(!depart){
            if(last.equals(this.getParent(last))){
                chemin.add(last);
                depart = true;
            }else{
                chemin.add(last);
                last = this.getParent(last);
            }
        }

        Collections.reverse(chemin);

        return chemin;
    }
}