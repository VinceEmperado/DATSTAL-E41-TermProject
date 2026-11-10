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
            System.out.println("============================= CAMPUS MAP CHECKER ============================");
            System.out.println("1. Load the map file");
            System.out.println("2. Show all places"); // NOTE: Sort this to A-Z order pa
            System.out.println("3. Find path between two places");
            System.out.println("4. Quit");
            System.out.println("=============================================================================");
            System.out.print("Enter your choice: ");
            int choice = readInt(scanner);
            switch (choice) {
                case 1:
                    System.out.println();
                    String filename = "src/map.txt";
                    try {
                        Campus.load(filename, Campus.graph);
                    } catch (FileNotFoundException e) {
                        System.out.println("Error: file " + filename + " not found.");
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error in map file: " + e.getMessage());
                        break;
                    } catch (IOException e) {
                        System.out.println("Error in reading file: " + e.getMessage());
                        break;
                    }
                    System.out.println("Load complete.");
                    break;
                case 2:
                    System.out.println();
                    Campus.main();
                    break;
                case 3:
                    // TO DO
                case 4:
                    running = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        CampusMain main = new CampusMain();
        main.mainLoop();
    }
}
