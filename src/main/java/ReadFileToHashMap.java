import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class ReadFileToHashMap {
    private Map<String, Integer> citiesHashMap;
    private List<String> citiesConnectionList = new ArrayList<>();
    List<String> citiesRepresentedByNumbers;

    public ReadFileToHashMap() throws IOException {
        this.citiesHashMap = new HashMap<>();
        this.citiesRepresentedByNumbers = new ArrayList<>();
        citiesToHashMap();
        fileLinesToList();
        this.citiesRepresentedByNumbers = replaceCitiesNameByNumbers();
    }

    private List<String> replaceCitiesNameByNumbers() {
        List<String> connections = new ArrayList<>();
        for (int connection = 0; connection < this.citiesConnectionList.size()-1; connection ++) {
            String[] parts = citiesConnectionList.get(connection).split("\\s");
            String fromCity = null;
            String toCity = null;
            if (this.citiesHashMap.containsKey(parts[0])) {
                fromCity = this.citiesHashMap.get(parts[0]).toString();
            }
            if (this.citiesHashMap.containsKey(parts[1])) {
                toCity = this.citiesHashMap.get(parts[1]).toString();
            }
            String cost = parts[2];
            connections.add(fromCity + " " + toCity + " " + cost);
        }
        System.out.println(connections);
        return connections;
    }

    public void fileLinesToList() throws FileNotFoundException {
        FileReader file = new FileReader("/home/ppolak/Desktop/Java/IdeaProjects/find-the-way-BlakPolak/src/main/java/citites.txt");  //address of the file
        Scanner sc = new Scanner(file);
        while( sc.hasNextLine() ){  //checking for the presence of next Line
            this.citiesConnectionList.add(sc.nextLine());  //reading and storing all citiesConnectionList
        }
        sc.close();  //close the scanner
    }

    private int countNumberOfLines() throws IOException {
        int numberOfLines = 0;
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader("/home/ppolak/Desktop/Java/IdeaProjects/find-the-way-BlakPolak/src/main/java/citites.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File error. Probably no such file in folder");
            e.getMessage();
        }
        while (in.readLine() != null){
            numberOfLines ++;
        }
        in.close();

        return  numberOfLines;
    }

    public void citiesToHashMap() throws IOException  {
        int numberOfLines = this.countNumberOfLines() -1;
        int vertexNumberForCity = 0;
        int actualLineNumber = 0;
        BufferedReader in = null;

        try {
            in = new BufferedReader(new FileReader("/home/ppolak/Desktop/Java/IdeaProjects/find-the-way-BlakPolak/src/main/java/citites.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File error. Probably no such file in folder");
            e.getMessage();
        }

        String line = "";
        while ((line = in.readLine()) != null) {
            int fromCity = 0 ;
            int toCity = 1 ;
            String parts[] = line.split("\\s");
            if (!citiesHashMap.containsKey(parts[fromCity])){
                this.citiesHashMap.put(parts[fromCity], vertexNumberForCity);
                vertexNumberForCity++;
            }
            if (!citiesHashMap.containsKey(parts[toCity])){
                this.citiesHashMap.put(parts[toCity], vertexNumberForCity);
                vertexNumberForCity++;
            }
            if (numberOfLines == actualLineNumber){
                int tempFromVertexNumber = this.citiesHashMap.get(parts[fromCity]);
                int tempToVertexNumber = this.citiesHashMap.get(parts[toCity]);
                int startCityIndex = 0;
                int destinationCityIndex = this.citiesHashMap.size() -1;
                for (String city : this.citiesHashMap.keySet()) {
                    if (this.citiesHashMap.get(city).equals(startCityIndex)) {
                        this.citiesHashMap.put(city, tempFromVertexNumber);
                    } else if (this.citiesHashMap.get(city).equals(destinationCityIndex)){
                        this.citiesHashMap.put(city, tempToVertexNumber);
                    }
                this.citiesHashMap.put(parts[fromCity], startCityIndex);
                this.citiesHashMap.put(parts[toCity], destinationCityIndex);
                }
            }
            actualLineNumber ++;
        }

        in.close();
        System.out.println(this.citiesHashMap.toString());
    }

}
