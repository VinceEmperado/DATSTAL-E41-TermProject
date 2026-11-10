import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Reads the map file, builds the graph, prints the graph
 */

public class Campus {

    public static void load(String filename, Graph graph) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split("\\s+");
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Line " + lineNumber + " error: Places must be formatted as Place1 Place2");
                }
                if (parts[0].equalsIgnoreCase(parts[1])) {
                    throw new IllegalArgumentException("Road can not connect to itself. Error at line " + lineNumber + ".");
                }
                if (!graph.addRoad(parts[0], parts[1])) {
                    throw new IllegalArgumentException("Duplicate road at line " + lineNumber + ".");
                }
            }

        }

    }

    public static void main(String[] args) throws IOException {
        String filename = args.length > 0 ? args[0] : "src/map.txt";
        Graph graph = new Graph();

        try {
            load(filename, graph);
        } catch (FileNotFoundException e) {
            System.out.println("Error: file " + filename + " not found.");
            return;
        } catch (IllegalArgumentException e) {
            System.out.println("Error in map file: " + e.getMessage());
            return;
        } catch (IOException e) {
            System.out.println("Error in reading file: " + e.getMessage());
            return;
        }

        graph.printGraph();
    }


}
