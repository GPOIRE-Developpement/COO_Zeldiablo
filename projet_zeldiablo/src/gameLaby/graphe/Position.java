package gameLaby.graphe;

public class Position implements Comparable<Position> {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Position){
            Position p = (Position)o;
            if(p.getX() == x && p.getY() == y){
                return true;
            }
        }
        return false;
    }

    @Override
    public int compareTo(Position other) {
        // Exemple de comparaison : d'abord sur x, puis sur y
        if (this.x != other.x) {
            return Integer.compare(this.x, other.x);
        } else {
            return Integer.compare(this.y, other.y);
        }
    }

    @Override
    public String toString(){
        return "[" + x + ", " + y + "]";
    }
}
