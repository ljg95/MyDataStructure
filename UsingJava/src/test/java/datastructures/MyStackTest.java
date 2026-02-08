package datastructures;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.EmptyStackException;

class MyStackTest {

    @Test
    void testPushAndPop() {
        // Push(추가)와 Pop(제거) 동작을 테스트합니다.
        MyStack<Integer> stack = new MyStack<>();
        stack.push(1);
        stack.push(2);

        // LIFO 순서로 2가 먼저 나오고 1이 나중애 나와야 합니다.
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }

    @Test
    void testIsEmpty() {
        // 비어있는지 확인하는 기능을 테스트합니다.
        MyStack<String> stack = new MyStack<>();
        assertTrue(stack.isEmpty()); // 처음엔 비어있어야 함

        stack.push("hello");
        assertFalse(stack.isEmpty()); // 추가 후엔 비어있지 않아야 함
    }

    @Test
    void testPeek() {
        // Peek(조회) 기능을 테스트합니다.
        MyStack<Integer> stack = new MyStack<>();
        stack.push(10);
        assertEquals(10, stack.peek()); // 값을 확인만 하고
        assertEquals(10, stack.pop()); // 실제로 제거했을 때도 값이 있어야 함
        // Peek은 제거하지 않으므로 pop() 결과는 여전히 10이어야 합니다.
    }

    @Test
    void testPopEmptyStack() {
        // 빈 스택에서 Pop을 시도할 때 예외가 발생하는지 테스트합니다.
        MyStack<Object> stack = new MyStack<>();
        assertThrows(EmptyStackException.class, stack::pop);
    }
}
