package gameLaby.graphe;

import java.util.ArrayList;
import java.util.List;

public class Arcs {
    private List<Arc> arcs;

    public Arcs(){
        arcs = new ArrayList<Arc>();
    }

    public void ajouterArc(Arc arc){
        arcs.add(arc);
    }

    public List<Arc> getArcs(){
        return arcs;
    }

    @Override
    public String toString(){
        StringBuilder sB = new StringBuilder();
        for(Arc arc : arcs){
            sB.append(arc.toString());
            sB.append(" ");
        }
        return sB.toString();
    }
}
