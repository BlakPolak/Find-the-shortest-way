import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        ReadFile fileWithCities = new ReadFile();
        List<Edge> edges = new ArrayList<>();
        List<String> citiesConnectionAndCost = fileWithCities.getCitiesConnectionAndCostsRepresentedByInt();
        for (int i = 0; i < citiesConnectionAndCost.size(); i++){
            String parts[] = citiesConnectionAndCost.get(i).split("\\s");
            int fromVertex = Integer.parseInt(parts[0]);
            int toVertex = Integer.parseInt(parts[1]);
            int cost = Integer.parseInt(parts[2]);
            edges.add(new Edge(fromVertex,toVertex,cost));
        }
        Graph graph = new Graph(edges);
        graph.calculateLeastCost();
        graph.printTravelCostForChosenConnection(fileWithCities);
        graph.printTravelCostForOtherCities(fileWithCities);
    }
}
