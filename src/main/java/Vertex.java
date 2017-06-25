import java.util.ArrayList;


public class Vertex {

    private boolean isVisited;
    private int cost = Integer.MAX_VALUE;
    private ArrayList<Edge> edges = new ArrayList<Edge>();

    public void setVisited(boolean visited) {
        this.isVisited = visited;
    }

    public boolean isVisited(){
        return this.isVisited;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return this.cost;
    }

    public ArrayList<Edge> getEdges() {
        return this.edges;
    }
}
