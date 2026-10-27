public class Main {
  public static void main(String[] args) {
    PlaceList list = new PlaceList(2);

    list.add("Library");
    list.add("Cafeteria");
    list.add("Gym");

    System.out.println("Size (should be 3): " + list.size());
    System.out.println("Place 0 (Library): " + list.get(0));
    System.out.println("Place 2 (Gym): " + list.get(2));
    System.out.println("Bad index (null): " + list.get(10));

    System.out.println("Find Gym (2): " + list.indexOf("Gym"));
    System.out.println("Find gym (2): " + list.indexOf("gym"));
    System.out.println("Find Pool (-1): " + list.indexOf("Pool"));
  }
}