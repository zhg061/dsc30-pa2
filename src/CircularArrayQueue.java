/*
 * NAME: Zhaoyi Guo
 * PID: A15180402
 */
import java.util.NoSuchElementException;
/**
 * This class will be a data structure that implements
 * the abstract data type (ADT) of a queue QueueADT.
 * The constructors of this class set the head, tail, length and capacity
 * (if provided) of the queue to zero, and creates a new circular Array.
 * This class consists of add(), resize(), isEmpty(), peek(), remove(),
 * clear(), size() to operate the queue.
 *
 */
public class CircularArrayQueue implements QueueADT {
    private static final int DEFAULT_SIZE = 5; // Example for declaring magic numbers
    private static final int GROWTH_FACTOR = 2; // Example for declaring magic numbers

    private int[] circularArray;
    private int length;
    private int head;
    private int tail;
    /**
     * Constructor for CircularArrayQueue
     *
     * set the head, tail, length to zero, and create a circular array
     */

    public CircularArrayQueue() {
        // The default constructor creates a new queue
        // with a default initial capacity of 5.
        head = tail = length = 0;
        circularArray = new int[DEFAULT_SIZE];
    }

    /**
     * Constructor for our banks checking accounts
     *
     * similar to the previous constructors, but with specified capacity.
     * if the capacity is smaller than 1, set the queue's capacity to 1.
     * @param capacity The specified capacity of the new queue
     *
     */

    public CircularArrayQueue(int capacity) {
        // The second constructor creates a new queue with a specified capacity.
        // If the capacity specified is less than 1, set the queue’s capacity to 1.
        if (capacity < 1) {
            circularArray = new int[1];
        }
        head = tail = length = 0;
        circularArray = new int[capacity];
    }

    /**
     * method to enqueue element
     *
     * @param elem The element we want to add to the queue
     */
    public void add(int elem) {
        // Adds a new element to the back of the queue
        if (length == circularArray.length) {
            resize();
        }
        circularArray[tail] = elem;
        tail = (tail + 1) % circularArray.length;
        length++;
    }

    /**
     * when the queue reaches its maximum capacity, this method double the capacity
     *
     */
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

    /**
     * return true if there is no element in the queue, false otherwise.
     */
    public boolean isEmpty() {
        // Determines if the queue is empty.
        // Returns true if the queue is empty and false otherwise.

        return length == 0;
    }

    /**
     * method to return the head element of the peek, without removing the
     * head element.
     *
     * @throws NoSuchElementException if there is no element left to peek
     */
    public int peek() throws NoSuchElementException {
        // Returns the front element of the queue.
        // Throws NoSuchElementException if the queue is empty.
        if (isEmpty()) {
            throw new NoSuchElementException("circularArray");
        }
        return circularArray[head];
    }

    /**
     * method to return the head element of the peek, while removing the
     * head element.
     *
     * @throws NoSuchElementException if there is no element left to peek
     */
    public int remove() throws NoSuchElementException {
        // Returns and removes the front element of the queue.
        // Throws NoSuchElementException if the queue is empty.
        if (isEmpty()) {
            throw new NoSuchElementException("circularArray");
        }
        int output = circularArray[head];
        head = (head + 1) % circularArray.length;
        length--;
        return output;
    }

    /**
     * method to remove all the elements from the queue
     *
     */
    public void clear() {
        // Removes all elements from the queue.
        head = tail = length = 0;
        circularArray = new int[circularArray.length];
    }

    /**
     * method to get the number of elements in the queue
     *
     */
    public int size() {
        // Returns the number of items in the queue.
        return length;
    }
//    public int[] getter() {
//        int[] circularArray2 = new int[circularArray.length];
//        int head2 = head;
//        for (int i = 0; i < length; i++) {
//            circularArray2[i] = circularArray[head];
//            head = (head + 1) % circularArray.length;
//        }
//        head = head2;
//        return circularArray2;
//    }
//    public int getter2() {
//        return length;
//    }
}
