

public class Edge {

    private int fromVertex;
    private int toVertex;
    private int cost;

    public Edge(int fromVertex, int toVertex, int cost){
        this.fromVertex = fromVertex;
        this.toVertex = toVertex;
        this.cost = cost;
    }

    public int getFromVertex() {
        return fromVertex;
    }

    public void setFromVertex(int fromVertex) {
        this.fromVertex = fromVertex;
    }

    public int getToVertex() {
        return toVertex;
    }

    public void setToVertex(int toVertex) {
        this.toVertex = toVertex;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getContiguousVertex( int vertex){
       int contiguousVertex = (this.fromVertex == vertex) ? this.toVertex : fromVertex;
       return contiguousVertex;
    }
}
