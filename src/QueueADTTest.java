/*
 * NAME: Zhaoyi Guo
 * PID: A15180402
 */
import static org.junit.Assert.*;

public class QueueADTTest {

    @org.junit.Test
    public void add() {
        CircularArrayQueue t1 = new CircularArrayQueue();
        t1.add(1);
        t1.add(1);
        t1.add(1);
        t1.add(1);
        t1.add(1);
        t1.add(1);
//        assertArrayEquals(new int[] {1, 1, 1, 1, 1, 1, 0, 0, 0, 0}, t1.getter());
        CircularArrayQueue t2 = new CircularArrayQueue();
        t2.add(1);
        t2.add(2);
        t2.add(1);
        t2.add(8);
        t2.add(-1);
        t2.remove();
//        assertArrayEquals(new int[] {2, 1, 8, -1, 0}, t2.getter());
        CircularArrayQueue t3 = new CircularArrayQueue(2);
        t3.add(1);
        t3.add(2);
        t3.add(8);
        t3.remove();
        t3.add(-1);
        t3.remove();
//        assertArrayEquals(new int[] {8, -1, 0, 0}, t3.getter());
    }

    @org.junit.Test
//    mm
    public void isEmpty() {
        CircularArrayQueue t1 = new CircularArrayQueue();
        t1.add(1);
        t1.add(1);
        t1.add(1);
        t1.add(1);
        t1.add(1);
        t1.add(1);
        assertEquals(false, t1.isEmpty());
        CircularArrayQueue t2 = new CircularArrayQueue();
        t2.add(1);
        t2.remove();

        assertEquals(true, t2.isEmpty());
        CircularArrayQueue t3 = new CircularArrayQueue(2);
        t3.add(1);
        t3.add(2);
        t3.add(8);
        t3.remove();
        t3.add(-1);
        t3.remove();
        t3.clear();
        assertEquals(true, t3.isEmpty());
    }

    @org.junit.Test
    public void peek() {
        CircularArrayQueue t1 = new CircularArrayQueue();
        t1.add(1);
        t1.add(1);
        t1.add(1);
        t1.add(1);
        t1.add(1);
        t1.add(1);
        assertEquals(1, t1.peek());
        CircularArrayQueue t2 = new CircularArrayQueue();
        t2.add(1);
        t2.add(2);
        t2.add(1);
        t2.add(8);
        t2.add(-1);
        t2.remove();
        assertEquals(2, t2.peek());
        CircularArrayQueue t3 = new CircularArrayQueue(2);
        t3.add(1);
        t3.add(2);
        t3.add(8);
        t3.remove();
        t3.add(-1);
        t3.remove();
        assertEquals(8, t3.peek());
    }

    @org.junit.Test
    public void remove() {
        CircularArrayQueue t1 = new CircularArrayQueue();
        t1.add(1);
        t1.add(1);
        t1.add(1);
        t1.add(1);
        t1.add(1);
        t1.add(1);
//        assertArrayEquals(new int[]{1, 1, 1, 1, 1, 1, 0, 0, 0, 0}, t1.getter());
        t1.remove();
//        assertEquals(5, t1.getter2());
//        assertArrayEquals(new int[]{1, 1, 1, 1, 1, 0, 0, 0, 0, 0}, t1.getter());
        CircularArrayQueue t2 = new CircularArrayQueue();
        t2.add(1);
        t2.add(2);
        t2.add(1);
        t2.add(8);
        t2.add(-1);
        assertEquals(1, t2.remove());
        assertEquals(2, t2.remove());
        assertEquals(1, t2.remove());
        assertEquals(8, t2.remove());
//        assertArrayEquals(new int[] {-1, 0, 0, 0, 0}, t2.getter());
        CircularArrayQueue t3 = new CircularArrayQueue(2);
        t3.add(1);
        t3.add(2);
        t3.add(8);
        t3.remove();
        t3.add(-1);
        t3.remove();
        t3.remove();
        t3.remove();
//        assertArrayEquals(new int[] {0, 0, 0, 0}, t3.getter());
    }

    @org.junit.Test
    public void clear() {
        CircularArrayQueue t1 = new CircularArrayQueue();
        t1.add(1);
        t1.add(1);
        t1.add(1);
        t1.add(1);
        t1.add(1);
        t1.add(1);
        t1.clear();
//        assertArrayEquals(new int[10], t1.getter());
        CircularArrayQueue t2 = new CircularArrayQueue(2);
        t2.add(1);
        t2.add(2);
        t2.add(1);
        t2.add(8);
        t2.add(-1);
        t2.clear();
//        assertArrayEquals(new int[8], t2.getter());
        CircularArrayQueue t3 = new CircularArrayQueue(2);
        t3.add(1);
        t3.add(2);
        t3.add(8);
        t3.clear();
//        assertArrayEquals(new int[] {0, 0, 0, 0}, t3.getter());
    }

    @org.junit.Test
    public void size() {
        CircularArrayQueue t1 = new CircularArrayQueue();
        t1.add(1);
        t1.add(1);
        t1.add(1);
        t1.add(1);
        t1.add(1);
        t1.add(1);
        assertEquals(6, t1.size());
        CircularArrayQueue t2 = new CircularArrayQueue(2);
        t2.add(1);
        t2.add(2);
        t2.add(1);
        t2.add(8);
        t2.add(-1);
        t2.clear();
        assertEquals(0, t2.size());
        CircularArrayQueue t3 = new CircularArrayQueue(2);
        t3.add(1);
        t3.add(2);
        t3.add(8);
        t3.clear();
        assertEquals(0, t3.size());
    }
}