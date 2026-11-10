/**
 * PlaceList.java
 * Stores the names of campus places in a growable array.
 * Written by: Nash Breann C. Paragas
 */

public class PlaceList {
    private String[] places;   // storage for the place names
    private int size;          // how many places are actually stored

    /**
     * Creates an empty list with the given starting capacity.
     * A capacity below 1 is changed to 1 so the array can always grow.
     */
    public PlaceList(int capacity) {
        if (capacity < 1) {
            capacity = 1;
        }
        places = new String[capacity];
        size = 0;
    }

    /**
     * Adds a place name to the end of the list.
     * If the array is full, it is replaced by one twice as big.
     */
    public void add(String name) {
        if (size == places.length) {
            String[] bigger = new String[places.length * 2];
            for (int i = 0; i < size; i++) {
                bigger[i] = places[i];
            }
            places = bigger;
        }
        places[size] = name;
        size++;
    }

    /**
     * Returns the place at the given index, or null if the index is
     * not between 0 and size - 1.
     */
    public String get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return places[index];
    }

    /** Returns how many places are stored. */
    public int size() {
        return size;
    }

    /**
     * Linear search. Checks each place from the start and returns the
     * index of the first match, or -1 if the name is not in the list.
     * The comparison ignores upper/lower case, so "gym" finds "Gym".
     */
    public int indexOf(String name) {
        for (int i = 0; i < size; i++) {
            if (places[i].equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    // Returns a sorted COPY, so the vertex numbers of the places in this
    // list do not change.
    public String[] sortedCopy() {
        String[] copy = new String[size];
        for (int i = 0; i < size; i++) {
            copy[i] = places[i];
        }
        for (int i = 1; i < size; i++) {
            String key = copy[i];
            int j = i - 1;
            while (j >= 0 && copy[j].compareToIgnoreCase(key) > 0) {
                copy[j + 1] = copy[j];
                j--;
            }
            copy[j + 1] = key;
        }
        return copy;
    }
}