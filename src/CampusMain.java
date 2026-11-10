import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class CampusMain {
    static Scanner scanner = new  Scanner(System.in);

    public static int readInt(Scanner scanner) {
        return Integer.parseInt(scanner.nextLine().trim());
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
                        return;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error in map file: " + e.getMessage());
                        return;
                    } catch (IOException e) {
                        System.out.println("Error in reading file: " + e.getMessage());
                        return;
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
