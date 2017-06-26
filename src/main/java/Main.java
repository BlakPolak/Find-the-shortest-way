import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        ReadFileToHashMap hashMap = new ReadFileToHashMap();
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0,1,100));
        edges.add(new Edge(1,2,300));
        edges.add(new Edge(2,3,250));
        edges.add(new Edge(0,3,450));
        edges.add(new Edge(3,4,500));
        edges.add(new Edge(2,4,800));

        Graph graph = new Graph(edges);
        graph.calculateLeastCost();
        graph.print();
    }
}
