import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Graph {

    private List<Vertex> vertices;
    private List<Edge> edges;
    private int numberOfVertices;
    private int numberOfEdges;

    public Graph(List<Edge> edges) {
        this.edges = edges;
        this.vertices = new ArrayList<Vertex>();
        this.numberOfVertices = calculateNumberOfVertices(edges);
        this.numberOfEdges = edges.size();
        this.addVerticesToList();
        this.addEdgesToVertices();
    }

    private int calculateNumberOfVertices(List<Edge> edges) {
        int verticesQuantity = 0;
        for (Edge edge : edges) {
            if (edge.getToVertex() > verticesQuantity) {
                verticesQuantity = edge.getToVertex();
            }
            if (edge.getFromVertex() > verticesQuantity) {
                verticesQuantity = edge.getFromVertex();
            }
        }
        verticesQuantity++;
        return verticesQuantity;
    }

    private void addVerticesToList() {
        for (int i = 0; i < this.numberOfVertices; i++) {
            this.vertices.add(new Vertex());
        }
    }

    private void addEdgesToVertices() {
        for (int i = 0; i < this.numberOfEdges; i++) {
            int indexOfVertexFrom = edges.get(i).getFromVertex();
            int indexOfVertexTo = edges.get(i).getToVertex();
            this.vertices.get(indexOfVertexFrom).getEdges().add(edges.get(i));
            this.vertices.get(indexOfVertexTo).getEdges().add(edges.get(i));
        }
    }

    public void calculateLeastCost() {
        int startCityIndex = 0;
        int startCosts = 0;
        this.vertices.get(startCityIndex).setCost(startCosts);
        int actualVertexIndex = 0;
        for (int i = 0; i < this.vertices.size(); i++) {
            ArrayList<Edge> vertexEdges = this.vertices.get(actualVertexIndex).getEdges();

            for (int j = 0; j < vertexEdges.size(); j++) {
                int contiguousVertexIndex = vertexEdges.get(j).getContiguousVertex(actualVertexIndex);

                if (!this.vertices.get(contiguousVertexIndex).isVisited()) {
                    int costForEdge = vertexEdges.get(j).getCost();
                    int alreadyCalculatedCost = this.vertices.get(actualVertexIndex).getCost();
                    int temporaryCost = alreadyCalculatedCost + costForEdge;
                    int costForContiguousVertex = this.vertices.get(contiguousVertexIndex).getCost();

                    if (temporaryCost < costForContiguousVertex) {
                        Vertex contiguousVertex = this.vertices.get(contiguousVertexIndex);
                        contiguousVertex.setCost(temporaryCost);
                    }
                }
            }
            Vertex actualVertex = this.vertices.get(actualVertexIndex);
            actualVertex.setVisited(true);
            actualVertexIndex = getVertexIndexWithLeastCost();
        }
    }

    private int getVertexIndexWithLeastCost() {
        int vertexIndex = 0;
        int cost = Integer.MAX_VALUE;

        for (int i = 0; i < this.vertices.size(); i++) {
            int actualCost = this.vertices.get(i).getCost();
            Vertex actualVertex = this.vertices.get(i);
            if (!actualVertex.isVisited() && actualCost < cost) {
                vertexIndex = i;
                cost = actualCost;
            }
        }
        return vertexIndex;
    }

    public void printTravelCostForChosenConnection(ReadFile fileWithCities) {
        int startCityIndex = 0;
        int destinationCityIndex = this.vertices.size()-1;
        String startCityName = "";
        String destinationCityName = "";
        Map<String, Integer> cities = fileWithCities.getCitiesHashMap();
        for (String city: cities.keySet()){
            if (startCityIndex == cities.get(city)){
                startCityName += city;
            } else if (destinationCityIndex == cities.get(city)){
                destinationCityName += city;
            }
        }
        System.out.println("The least cost for travel from " + startCityName + " to " + destinationCityName + " is " + vertices.get(destinationCityIndex).getCost() + " zł");

    }

    public void printTravelCostForOtherCities(ReadFile fileWithCities) {
        int startCityIndex = 0;
        String startCityName = "";
        String destinationCityName = "";
        Map<String, Integer> cities = fileWithCities.getCitiesHashMap();
        System.out.println("\nTo fly to other cities at the least cost using entered connections you have to spend accordingly:");
        for (int i = 1 ; i < this.vertices.size() - 1; i++){
            int destinationCityIndex = i;
            for (String city: cities.keySet()){
                if (startCityIndex == cities.get(city)){
                    startCityName = city;
                } else if (destinationCityIndex == cities.get(city)){
                    destinationCityName = city;
                }
            }
            System.out.println("From " + startCityName + " to " + destinationCityName + " -> " + vertices.get(destinationCityIndex).getCost() + " zł");
        }
    }
}