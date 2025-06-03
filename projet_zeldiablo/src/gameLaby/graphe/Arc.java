package gameLaby.graphe;

public class Arc {
    private Position destination;
    private int cout;

    public Arc(Position destination, int cout){
        this.destination = destination;
        this.cout = cout;
    }

    public Position getDestination() {
        return destination;
    }

    public int getCout() {
        return cout;
    }

    public void setCout(int cout) {
        this.cout = cout;
    }

    @Override
    public String toString(){
        StringBuilder sB = new StringBuilder();

        sB.append(destination);
        sB.append("(");
        sB.append(cout);
        sB.append(")");

        return sB.toString();
    }
}
