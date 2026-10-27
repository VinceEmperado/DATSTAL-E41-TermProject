public class PlaceGraph {
    // A custom Linked List for neighboring IDs
    private static class Node {
        int vertexId;
        Node next;

        Node(int vertexId) {
            this.vertexId = vertexId;
            this.next = null;
        }
    }

    private final int vertexCount;
    private final Node[] adjacencyList;

    // Initializes the graph with a fixed number of vertices
    public PlaceGraph(int vertexCount) {
        this.vertexCount = vertexCount;
        this.adjacencyList = new Node[vertexCount];
    }

    // Undirected edge from point A to point B
    public void addEdge(int source, int destination) {
        if (source < 0 || source >= vertexCount || destination < 0 || destination >= vertexCount) {
            System.out.println("Error: Vertex index out of bounds.");
            return;
        }

        // Add destination to source's list
        Node newNode1 = new Node(destination);
        newNode1.next = adjacencyList[source];
        adjacencyList[source] = newNode1;

        // Add source to destination's list
        Node newNode2 = new Node(source);
        newNode2.next = adjacencyList[destination];
        adjacencyList[destination] = newNode2;
    }

    // Prints the graph
    public void printGraph() {
        for (int i = 0; i < vertexCount; i++) {
            System.out.print("Vertex " + i + " is connected to: ");
            Node current = adjacencyList[i];

            if (current == null) {
                System.out.print("None");
            }

            while (current != null) {
                System.out.print(current.vertexId + " ");
                current = current.next;
            }
            System.out.println();
        }
    }
}