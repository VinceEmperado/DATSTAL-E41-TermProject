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

    System.out.println();
    IntQueue q = new IntQueue(3);

    q.enqueue(1);
    q.enqueue(2);
    q.enqueue(3);
    System.out.println("Is full (true): " + q.isFull());
    q.enqueue(4);

    System.out.println("Dequeue (1): " + q.dequeue());
    q.enqueue(4);
    System.out.println("Dequeue (2): " + q.dequeue());
    System.out.println("Dequeue (3): " + q.dequeue());
    System.out.println("Dequeue (4): " + q.dequeue());

    System.out.println("Is empty (true): " + q.isEmpty());
    System.out.println("Dequeue empty (-1): " + q.dequeue());
  }
}