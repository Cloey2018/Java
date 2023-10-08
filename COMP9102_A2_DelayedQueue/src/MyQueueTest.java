import static org.junit.Assert.*;

public class MyQueueTest {

    @org.junit.Test
    public void constructorTest() {
        MyQueue<String> myQueue = new MyQueue<String>(3);
        DelayedQueue<Integer> delayedQueue = new MyQueue<Integer>(2);
        assertEquals(3, myQueue.getMaximumDelay());
        assertEquals(2, delayedQueue.getMaximumDelay());
    }

    @org.junit.Test
    public void enqueueResizeTest() {
        MyQueue<Integer> q = new MyQueue<Integer>(0);
        q.enqueue(0);
        for (int i = 1; i < 1002; i ++){
            q.enqueue(i);
        }
        assertEquals(1002, q.size());
        assertEquals(0, Integer.parseInt(String.valueOf(q.dequeue())));
    }

    @org.junit.Test
    public void dequeueTest() {
        DelayedQueue<String> q = new MyQueue<String>(4);
        q.enqueue("s1");
        q.enqueue("s2");
        assertEquals(2, q.getDelay());
        assertNull(q.dequeue());
        q.enqueue("more");
        q.enqueue("four");
        assertEquals(0, q.getDelay());
        q.enqueue("five");
        assertEquals(0, q.getDelay());
        assertEquals("s1", q.dequeue());
        q.enqueue("usyd");
        assertEquals(3, q.getDelay());
        assertNull(q.dequeue());
        q.enqueue("unsw");
        q.enqueue("monash");
        q.enqueue("uts");
        assertEquals("s2", q.dequeue());
        assertEquals("more", q.dequeue());
        assertEquals("four", q.dequeue());
        assertEquals("five", q.dequeue());
        assertEquals("usyd", q.dequeue());
        assertFalse(q.contains("usyd"));
    }

    @org.junit.Test
    public void dequeueNewMaxDelayTest() {
        DelayedQueue<String> q = new MyQueue<String>(4);
        assertEquals(4, q.getMaximumDelay());
        assertEquals(4, q.getDelay());
        // set maxDelay at the beginning without a series of push and pop
        q.setMaximumDelay(2);
        assertEquals(2, q.getMaximumDelay());
        assertEquals(4, q.getDelay());
        q.enqueue("s1");
        q.enqueue("s2");
        assertEquals(2, q.getDelay());
        assertNull(q.dequeue());
        q.enqueue("s3");
        q.enqueue("s4");
        assertEquals("s1", q.dequeue());
        assertEquals("s2", q.dequeue());
        assertEquals("s3", q.dequeue());
        q.enqueue("first");
        assertEquals(1, q.getDelay());
        assertNull(q.dequeue());
        q.enqueue("second");
        q.enqueue("third");
        assertEquals("s4", q.dequeue());
        // set maxDelay when dequeueing
        q.setMaximumDelay(3);
        assertEquals(3, q.getMaximumDelay());
        assertEquals(0, q.getDelay());
        assertEquals("first", q.dequeue());
        assertEquals("second", q.dequeue());
        q.enqueue("usyd");
        assertEquals(2, q.getDelay());
        q.setMaximumDelay(4);
        // set maxDelay when enqueueing
        assertEquals(4, q.getMaximumDelay());
        assertEquals(2, q.getDelay());
        q.enqueue("uts");
        q.enqueue("unsw");
        q.enqueue("anu");
        assertEquals("third", q.dequeue());
    }

    @org.junit.Test
    public void dequeueExceptionTest() {
        DelayedQueue<Integer> q = new MyQueue<Integer>(2);
        assertEquals(0, q.size());
        String e_mess = null;
        try{
            q.dequeue();
        }catch (IllegalStateException e){
            e_mess = e.getMessage();
        }
        assertEquals("IllegalStateException is thrown, the stack is empty.", e_mess);
    }

    @org.junit.Test
    public void peekTest() {
        DelayedQueue<Integer> q = new MyQueue<Integer>(4);
        q.enqueue(1);
        q.enqueue(2);
        assertEquals(2, q.getDelay());
        assertEquals(1, Integer.parseInt(String.valueOf(q.peek())));
        assertTrue(q.contains(1));
        q.enqueue(3);
        q.enqueue(4);
        assertEquals(0, q.getDelay());
        assertEquals(1, Integer.parseInt(String.valueOf(q.dequeue())));
        assertEquals(2, Integer.parseInt(String.valueOf(q.peek())));
    }

    @org.junit.Test
    public void peekExceptionTest() {
        DelayedQueue<Integer> q = new MyQueue<Integer>(2);
        assertEquals(0, q.size());
        String e_mess = null;
        try{
            q.peek();
        }catch (IllegalStateException e){
            e_mess = e.getMessage();
        }
        assertEquals("IllegalStateException is thrown, the stack is empty.", e_mess);
    }

    @org.junit.Test
    public void clearTest() {
        MyQueue<String> q = new MyQueue<String>(4);
        assertFalse(q.clear());
        q.enqueue("first");
        q.enqueue("second");
        assertFalse(q.clear());
        q.enqueue("third");
        q.enqueue("fourth");
        assertEquals(4, q.size());
        assertTrue(q.clear());
        assertEquals(0, q.size());
        assertTrue(q.clear());
        q.enqueue("s1");
        assertFalse(q.clear());
        assertEquals(1, q.size());
    }

    @org.junit.Test
    public void containsTest() {
        MyQueue<String> q = new MyQueue<String>(-1);
        // if the queue is empty
        assertFalse(q.contains("First"));
        // if no null is put in the queue
        q.enqueue("First");
        assertFalse(q.contains(null));
        // if null is put in the queue
        q.enqueue(null);
        q.enqueue("third");
        assertTrue(q.contains(null));
        assertTrue(q.contains("third"));
        assertFalse(q.contains("Fourth"));
    }
}