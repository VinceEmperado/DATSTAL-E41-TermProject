import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Graph.java
 * Undirected graph of the campus places stored as an adjacency list
 */
public class Graph {
    private PlaceList places = new PlaceList(10);
    private int[][] adjacencyMatrix = new int[10][10];
    private int[] degree = new int[10];
    private int roads = 0;

    // Adds a place if it is not part of the graph yet. Returns the vertex number.
    public int addPlace(String name) {
        int vertex = places.indexOf(name);
        if (vertex != -1) {
            return vertex;
        }

        vertex = places.size();
        places.add(name);

        if (vertex == adjacencyMatrix.length) {
            int[][] grow = new int[adjacencyMatrix.length * 2][];
            int[] newDegree = new int[degree.length * 2];
            for (int i = 0; i < vertex; i++) {
                grow[i] = adjacencyMatrix[i];
                newDegree[i] = degree[i];
            }
            adjacencyMatrix = grow;
            degree = newDegree;
        }

        adjacencyMatrix[vertex] = new int[2];
        degree[vertex] = 0;
        return vertex;
    }

    public int indexOf(String name) {
        return places.indexOf(name);
    }

    public String getName(int vertex) {
        return places.get(vertex);
    }

    public boolean hasPlace(String name) {
        return places.indexOf(name) != -1;
    }

    public boolean hasRoad(int a, int b) {
        if (a < 0 || a >= places.size() || b < 0 || b >= places.size()) {
            return false;
        }
        for (int i = 0; i < degree[a]; i++) {
            if (adjacencyMatrix[a][i] == b) {
                return true;
            }
        }
        return false;
    }

    public boolean addRoad(String a, String b) {
        int u = addPlace(a);
        int v = addPlace(b);
        if (u == v || hasRoad(u, v)) {
            return false;
        }
        addNeighbor(u, v);
        addNeighbor(v, u);
        roads++;
        return true;
    }

    private void addNeighbor(int u, int v) {
        if (degree[u] == adjacencyMatrix[u].length) {
            int[] bigger = new int[adjacencyMatrix[u].length * 2];
            for (int i = 0; i < degree[u]; i++) {
                bigger[i] = adjacencyMatrix[u][i];
            }
            adjacencyMatrix[u] = bigger;
        }
        adjacencyMatrix[u][degree[u]] = v;
        degree[u]++;
    }

    public int placeCount() {
        return places.size();
    }

    public int roadCount() {
        return roads;
    }

    public int getDegree(int v) {
        if (v < 0 || v == places.size()) {
            return 0;
        }
        return degree[v];
    }

    public int getNeighbor(int a, int b) {
        return adjacencyMatrix[a][b];
    }

    public void printGraph() {
        System.out.println("Places: " + placeCount() + "    Roads: " + roadCount());
        System.out.println();
        for (int i = 0; i < placeCount(); i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < degree[i]; j++) {
                if (j > 0) {
                    sb.append(", ");
                }
                sb.append(getName(adjacencyMatrix[i][j]).replace('_', ' '));
            }
            System.out.println(getName(i).replace('_', ' ')
                    + " (" + degree[i] + "): " + sb);
        }
    }
}
