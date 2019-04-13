/*
 * NAME: Zhaoyi Guo
 * PID: A15180402
 */
import java.util.NoSuchElementException;

public class CircularArrayQueue implements QueueADT {
    private static final int DEFAULT_SIZE = 5; // Example for declaring magic numbers
    private static final int GROWTH_FACTOR = 2; // Example for declaring magic numbers

    private int[] circularArray;
    private int length;
    private int head;
    private int tail;

    public CircularArrayQueue() {
        // The default constructor creates a new queue
        // with a default initial capacity of 5.
        head = tail = length = 0;
        circularArray = new int[DEFAULT_SIZE];
        // Test
    }

    public CircularArrayQueue(int capacity) {
        // The second constructor creates a new queue with a specified capacity.
        // If the capacity specified is less than 1, set the queue’s capacity to 1.
        if (capacity < 1) {
            circularArray = new int[1];
        }
        head = tail = length = 0;
        circularArray = new int[capacity];
    }

    public void add(int elem) {
        // Adds a new element to the back of the queue
        if (length == circularArray.length) {
            resize();
        }
        circularArray[tail] = elem;
        tail = (tail + 1) % circularArray.length;
        length++;
    }

    private void resize() {
        // Doubles the capacity of the queue.
        int[] circularArray2 = new int[circularArray.length * GROWTH_FACTOR];
        for (int i = 0; i < length; i++) {
            circularArray2[i] = circularArray[head];
            head = (head + 1) % circularArray.length;
        }
        head = 0;
        tail = length;
        circularArray = circularArray2;
    }

    public boolean isEmpty() {
        // Determines if the queue is empty.
        // Returns true if the queue is empty and false otherwise.

        return length == 0;
    }


    public int peek() throws NoSuchElementException {
        // Returns the front element of the queue.
        // Throws NoSuchElementException if the queue is empty.
        if (isEmpty()) {
            throw new NoSuchElementException ("circularArray");
        }
        return circularArray[head];
    }

    public int remove() throws NoSuchElementException {
        // Returns and removes the front element of the queue.
        // Throws NoSuchElementException if the queue is empty.
        if (isEmpty()) {
            throw new NoSuchElementException ("circularArray");
        }
        int output = circularArray[head];
        head = (head + 1) % circularArray.length;
        length--;
        return output;
    }

    public void clear() {
        // Removes all elements from the queue.
        head = tail = length = 0;
        circularArray = new int[circularArray.length];
    }

    public int size() {
        // Returns the number of items in the queue.
        return length;
    }
    public int[] getter() {
        int[] circularArray2 = new int[circularArray.length];
        int head2 = head;
        for (int i = 0; i < length; i++) {
            circularArray2[i] = circularArray[head];
            head = (head + 1) % circularArray.length;
        }
        head = head2;
        return circularArray2;
    }
    public int getter2() {
        return length;
    }
}
