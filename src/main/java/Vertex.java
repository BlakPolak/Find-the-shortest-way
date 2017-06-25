import java.util.ArrayList;


public class Vertex {

    private boolean isVisited;
    private int distance = Integer.MAX_VALUE;
    private ArrayList<Edge> edges = new ArrayList<Edge>();


    public void setVisited(boolean visited) {
        this.isVisited = visited;
    }

    public boolean isVisited(){
        return this.isVisited;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getDistance() {
        return this.distance;
    }

    public void setEdges(ArrayList<Edge> edges) {
        this.edges = edges;
    }

    public ArrayList<Edge> getEdges() {
        return this.edges;
    }
}
