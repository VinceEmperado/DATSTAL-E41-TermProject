public class Main {
    public static void main(String[] args) {
        PlaceList list = new PlaceList(2);

        list.add("Library");
        list.add("LS Kitchen");
        list.add("Velasco Building");

        System.out.println("Size (should be 3): " + list.size());
        System.out.println("Place 0 (Library): " + list.get(0));
        System.out.println("Place 2 (Velasco): " + list.get(2));
        System.out.println("Bad index (null): " + list.get(10));

        System.out.println("Find ls kitchen (1): " + list.indexOf("ls kitchen"));
        System.out.println("Find LS Kitchen (1): " + list.indexOf("LS Kitchen"));
        System.out.println("Find Basketball Field (-1): " + list.indexOf("Basketball Field"));

    }
}