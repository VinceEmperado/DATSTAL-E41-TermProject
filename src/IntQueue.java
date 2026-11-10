/**
 * IntQueue.java
 * A circular array queue of ints, used by BFS to hold vertex numbers.
 * Vertex numbers are never negative, so -1 is used to mean "empty".
 */

public class IntQueue {
    private int[] items;   // storage for the queue
    private int front;     // index of the next item to remove
    private int rear;      // index where the next item will be added
    private int count;     // how many items are in the queue

    /**
     * Creates an empty queue that can hold up to capacity items.
     * A capacity below 1 is changed to 1.
     */
    public IntQueue(int capacity) {
        if (capacity < 1) {
            capacity = 1;
        }
        items = new int[capacity];
        front = 0;
        rear = 0;
        count = 0;
    }

    /**
     * Adds x to the back of the queue.
     * If the queue is full, prints a message and does nothing.
     */
    public void enqueue(int x) {
        if (isFull()) {
            System.out.println("Queue is full. Cannot add " + x);
            return;
        }
        items[rear] = x;
        rear = (rear + 1) % items.length;   // wrap around to 0 at the end
        count++;
    }

    /**
     * Removes and returns the item at the front of the queue.
     * Returns -1 if the queue is empty.
     */
    public int dequeue() {
        if (isEmpty()) {
            return -1;
        }
        int x = items[front];
        front = (front + 1) % items.length;  // wrap around to 0 at the end
        count--;
        return x;
    }

    /** Returns true if the queue has no items. */
    public boolean isEmpty() {
        return count == 0;
    }

    /** Returns true if the queue cannot take any more items. */
    public boolean isFull() {
        return count == items.length;
    }
}