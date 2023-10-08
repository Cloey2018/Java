import org.junit.*;
import static org.junit.Assert.*;

public class MyStackTest {

    @org.junit.Test
    public void constructorTest() {
        MyStack<String> myStack = new MyStack<String>(3);
        DelayedStack<Integer> delayedStack = new MyStack<Integer>(2);
        assertEquals(3, myStack.getMaximumDelay());
        assertEquals(2, delayedStack.getMaximumDelay());
    }

    @org.junit.Test
    public void pushResizeTest() {
        DelayedStack<Integer> s = new MyStack<Integer>(0);
        s.push(0);
        for (int i = 1; i < 1002; i ++){
            s.push(i);
        }
        assertEquals(1002, s.size());
        assertEquals(1001, Integer.parseInt(String.valueOf(s.pop())));
    }

    @org.junit.Test
    public void popTest() {
        DelayedStack<String> s = new MyStack<String>(4);
        s.push("s1");
        s.push("s2");
        assertEquals(2, s.getDelay());
        assertNull(s.pop());
        s.push("more");
        s.push("four");
        assertEquals(0, s.getDelay());
        s.push("five");
        assertEquals(0, s.getDelay());
        assertEquals("five", s.pop());
        s.push("usyd");
        assertEquals(3, s.getDelay());
        assertNull(s.pop());
        s.push("unsw");
        s.push("monash");
        s.push("uts");
        assertEquals("uts", s.pop());
        assertEquals("monash", s.pop());
        assertEquals("unsw", s.pop());
        assertEquals("usyd", s.pop());
        assertEquals("four", s.pop());
        assertFalse(s.contains("usyd"));
    }

    @org.junit.Test
    public void popNewMaxDelayTest() {
        DelayedStack<String> s = new MyStack<String>(4);
        assertEquals(4, s.getMaximumDelay());
        assertEquals(4, s.getDelay());
        // set maxDelay at the beginning without a series of push and pop
        s.setMaximumDelay(2);
        assertEquals(2, s.getMaximumDelay());
        assertEquals(4, s.getDelay());
        s.push("s1");
        s.push("s2");
        assertEquals(2, s.getDelay());
        assertNull(s.pop());
        s.push("s3");
        s.push("s4");
        assertEquals("s4", s.pop());
        assertEquals("s3", s.pop());
        assertEquals("s2", s.pop());
        s.push("first");
        assertEquals(1, s.getDelay());
        assertNull(s.pop());
        s.push("second");
        s.push("third");
        assertEquals("third", s.pop());
        // set maxDelay when poping
        s.setMaximumDelay(3);
        assertEquals(3, s.getMaximumDelay());
        assertEquals(0, s.getDelay());
        assertEquals("second", s.pop());
        assertEquals("first", s.pop());
        s.push("usyd");
        assertEquals(2, s.getDelay());
        s.setMaximumDelay(4);
        // set maxDelay when pushing
        assertEquals(4, s.getMaximumDelay());
        assertEquals(2, s.getDelay());
        s.push("uts");
        s.push("unsw");
        s.push("anu");
        assertEquals("anu", s.pop());
    }

    @org.junit.Test
    public void popExceptionTest() {
        DelayedStack<Integer> s = new MyStack<Integer>(2);
        assertEquals(0, s.size());
        String e_mess = null;
        try{
            s.pop();
        }catch (IllegalStateException e){
            e_mess = e.getMessage();
        }
        assertEquals("IllegalStateException is thrown, the stack is empty.", e_mess);
    }

    @org.junit.Test
    public void peekTest() {
        DelayedStack<Integer> s = new MyStack<Integer>(4);
        s.push(1);
        s.push(2);
        assertEquals(2, s.getDelay());
        assertEquals(2, Integer.parseInt(String.valueOf(s.peek())));
        assertTrue(s.contains(2));
        s.push(3);
        s.push(4);
        assertEquals(0, s.getDelay());
        assertEquals(4, Integer.parseInt(String.valueOf(s.pop())));
        assertEquals(3, Integer.parseInt(String.valueOf(s.peek())));
    }

    @org.junit.Test
    public void peekExceptionTest() {
        DelayedStack<Integer> s = new MyStack<Integer>(2);
        assertEquals(0, s.size());
        String e_mess = null;
        try{
            s.peek();
        }catch (IllegalStateException e){
            e_mess = e.getMessage();
        }
        assertEquals("IllegalStateException is thrown, the stack is empty.", e_mess);
    }

    @org.junit.Test
    public void clearTest() {
        MyStack<String> s = new MyStack<String>(4);
        assertFalse(s.clear());
        s.push("first");
        s.push("second");
        assertFalse(s.clear());
        s.push("third");
        s.push("fourth");
        assertEquals(4, s.size());
        assertTrue(s.clear());
        assertEquals(0, s.size());
        assertTrue(s.clear());
        s.push("s1");
        assertFalse(s.clear());
        assertEquals(1, s.size());
    }

    @org.junit.Test
    public void contains() {
        MyStack<String> s = new MyStack<String>(-1);
        // if the stack is empty
        assertFalse(s.contains("First"));
        // if no null is put in the stack
        s.push("First");
        assertFalse(s.contains(null));
        // if null is put in the stack
        s.push(null);
        s.push("third");
        assertTrue(s.contains(null));
        assertTrue(s.contains("third"));
        assertFalse(s.contains("Fourth"));
    }
}