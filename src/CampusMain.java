import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class CampusMain {
    static Scanner scanner = new  Scanner(System.in);

    // [ADDED] default map file for menu choice 1
    static final String DEFAULT_FILE = "src/campus.txt";

    // [ADDED] reads one line, or returns "" if the input has ended
    public static String readLine() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine().trim();
        }
        return "";
    }

    public static int readInt(Scanner scanner) {
        // [CHANGED] was: return Integer.parseInt(scanner.nextLine().trim());
        // That crashed on non-numbers and when the input ended.
        if (!scanner.hasNextLine()) {
            return 4;       // [ADDED] input ended: quit
        }
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;      // [ADDED] not a number: handled by default case
        }
    }

    public void mainLoop() throws IOException {
        boolean running = true;

        while (running) {
            System.out.println("============================="
                    + " CAMPUS MAP CHECKER ============================");
            System.out.println("1. Load the map file");
            // NOTE: Sort this to A-Z order pa
            System.out.println("2. Show all places");
            System.out.println("3. Find path between two places");
            System.out.println("4. Quit");
            System.out.println("======================================"
                    + "=======================================");
            System.out.print("Enter your choice: ");
            int choice = readInt(scanner);
            switch (choice) {
                case 1:
                    System.out.println();
                    // [CHANGED] was: String filename = "src/map.txt";
                    // Now it asks for the file name (Enter = default file).
                    System.out.print("Map file (Enter for "
                            + DEFAULT_FILE + "): ");
                    String filename = readLine();
                    if (filename.isEmpty()) {
                        filename = DEFAULT_FILE;
                    }
                    // [ADDED] load into a fresh graph, so loading twice works
                    Graph fresh = new Graph();
                    try {
                        // [CHANGED] was: Campus.load(filename, Campus.graph);
                        Campus.load(filename, fresh);
                        // [ADDED] keep the new graph only if the load worked
                        Campus.graph = fresh;
                    } catch (FileNotFoundException e) {
                        System.out.println("Error: file " + filename
                                + " not found.");
                        // [CHANGED] was: return;  (it closed the program)
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error in map file: "
                                + e.getMessage());
                        // [CHANGED] was: return;  (it closed the program)
                        break;
                    } catch (IOException e) {
                        System.out.println("Error in reading file: "
                                + e.getMessage());
                        // [CHANGED] was: return;  (it closed the program)
                        break;
                    }
                    System.out.println("Load complete.");
                    break;
                case 2:
                    System.out.println();
                    // [CHANGED] was: Campus.main();  (it printed the graph)
                    showPlaces();
                    break;
                case 3:
                    // [CHANGED] was: // TO DO  (with no break, so it fell
                    // into case 4 and closed the program)
                    findPath();
                    break;
                case 4:
                    running = false;
                    break;      // [ADDED]
                default:        // [ADDED] bad menu input
                    System.out.println("Please enter 1, 2, 3 or 4.");
            }
        }
    }

    private static void showPlaces() {
        if (Campus.graph.placeCount() == 0) {
            System.out.println("Load the map first (choice 1).");
            return;
        }
        String[] sorted = Campus.graph.sortedNames();
        System.out.println("Places (A to Z):");
        for (int i = 0; i < sorted.length; i++) {
            System.out.println("  " + sorted[i].replace('_', ' '));
        }
    }

    // ===== [ADDED] menu choice 3: find a path (BFS) =====
    private static void findPath() {
        Graph graph = Campus.graph;
        if (graph.placeCount() == 0) {
            System.out.println("Load the map first (choice 1).");
            return;
        }

        System.out.print("From: ");
        // spaces are accepted too: "Henry Sy Hall" = Henry_Sy_Hall
        String from = readLine().replace(' ', '_');
        int start = graph.indexOf(from);
        if (start == -1) {
            System.out.println("Place not found: " + from);
            return;
        }

        System.out.print("To: ");
        String to = readLine().replace(' ', '_');
        int end = graph.indexOf(to);
        if (end == -1) {
            System.out.println("Place not found: " + to);
            return;
        }

        if (start == end) {
            System.out.println("You are already at "
                    + graph.getName(start).replace('_', ' ') + ".");
            return;
        }

        int[] path = graph.findPath(start, end);
        if (path == null) {
            System.out.println("No path between "
                    + graph.getName(start).replace('_', ' ') + " and "
                    + graph.getName(end).replace('_', ' ') + ".");
            return;
        }

        System.out.print("Path: ");
        for (int i = 0; i < path.length; i++) {
            if (i > 0) {
                System.out.print(" -> ");
            }
            System.out.print(graph.getName(path[i]).replace('_', ' '));
        }
        System.out.println();
        System.out.println("Number of stops: " + (path.length - 1));
    }

    public static void main(String[] args) throws IOException {
        CampusMain main = new CampusMain();
        main.mainLoop();
    }
}
