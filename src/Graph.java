// [REMOVED] 4 unused imports: java.util ArrayList, HashMap, List, Map
// (the handout says to write the data structures yourselves)

/**
 * Graph.java
 * Undirected graph of the campus places stored as an adjacency list
 */
public class Graph {
    private PlaceList places = new PlaceList(10);
    private int[][] adjacencyMatrix = new int[10][10];
    private int[] degree = new int[10];
    private int roads = 0;

    // Adds a place if it is not part of the graph yet.
    // Returns the vertex number.
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
        // [CHANGED] was: v == places.size()  (missed values above size)
        if (v < 0 || v >= places.size()) {
            return 0;
        }
        return degree[v];
    }

    public int getNeighbor(int a, int b) {
        return adjacencyMatrix[a][b];
    }

    public void printGraph() {
        System.out.println("Places: " + placeCount()
                + "    Roads: " + roadCount());
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

    public String[] sortedNames() {
        return places.sortedCopy();
    }

    // start and end are vertex numbers. Returns the path as vertex
    // numbers (start first), or null if there is no path.
    public int[] findPath(int start, int end) {
        int n = placeCount();
        boolean[] visited = new boolean[n];
        int[] parent = new int[n];      // where we came from, -1 = none
        for (int i = 0; i < n; i++) {
            parent[i] = -1;
        }

        IntQueue queue = new IntQueue(n);
        visited[start] = true;
        queue.enqueue(start);

        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            if (v == end) {
                break;
            }
            for (int i = 0; i < getDegree(v); i++) {
                int w = getNeighbor(v, i);
                if (!visited[w]) {
                    visited[w] = true;
                    parent[w] = v;
                    queue.enqueue(w);
                }
            }
        }

        if (!visited[end]) {
            return null;   // no path
        }

        // Walk back from end to start using parent, then fill the path.
        int length = 0;
        for (int v = end; v != -1; v = parent[v]) {
            length++;
        }
        int[] path = new int[length];
        int v = end;
        for (int i = length - 1; i >= 0; i--) {
            path[i] = v;
            v = parent[v];
        }
        return path;
    }
}
