package datastructures;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.EmptyStackException;

class MyStackTest {

    @Test
    void testPushAndPop() {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(1);
        stack.push(2);

        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }

    @Test
    void testIsEmpty() {
        MyStack<String> stack = new MyStack<>();
        assertTrue(stack.isEmpty());

        stack.push("hello");
        assertFalse(stack.isEmpty());
    }

    @Test
    void testPeek() {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(10);
        assertEquals(10, stack.peek());
        assertEquals(10, stack.pop());
        // Ensure peek didn't remove it
    }

    @Test
    void testPopEmptyStack() {
        MyStack<Object> stack = new MyStack<>();
        assertThrows(EmptyStackException.class, stack::pop);
    }
}
