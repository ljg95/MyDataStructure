package datastructures;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;

class MyQueueTest {

    @Test
    void testOfferAndPoll() {
        // Offer(추가)와 Poll(제거) 동작을 테스트합니다.
        MyQueue<Integer> queue = new MyQueue<>();
        queue.offer(1);
        queue.offer(2);

        // FIFO 순서로 1이 먼저 나오고 2가 나중에 나와야 합니다.
        assertEquals(1, queue.poll());
        assertEquals(2, queue.poll());
    }

    @Test
    void testIsEmpty() {
        // 비어있는지 확인하는 기능을 테스트합니다.
        MyQueue<String> queue = new MyQueue<>();
        assertTrue(queue.isEmpty());

        queue.offer("hello");
        assertFalse(queue.isEmpty());
    }

    @Test
    void testPeek() {
        // Peek(조회) 기능을 테스트합니다.
        MyQueue<Integer> queue = new MyQueue<>();
        queue.offer(10);
        assertEquals(10, queue.peek());
        assertEquals(10, queue.poll());
        // Peek은 제거하지 않으므로 poll() 결과는 여전히 10이어야 하고, 그 후엔 비어있어야 합니다.
        assertTrue(queue.isEmpty());
    }

    @Test
    void testPollEmptyQueue() {
        // 빈 큐에서 Poll(제거)을 시도할 때 예외가 발생하는지 테스트합니다.
        MyQueue<Object> queue = new MyQueue<>();
        assertThrows(NoSuchElementException.class, queue::poll);
    }

    @Test
    void testPeekEmptyQueue() {
        // 빈 큐에서 Peek(조회)을 시도할 때 예외가 발생하는지 테스트합니다.
        MyQueue<Object> queue = new MyQueue<>();
        assertThrows(NoSuchElementException.class, queue::peek);
    }

    @Test
    void testSize() {
        // 사이즈(크기) 확인 기능을 테스트합니다.
        MyQueue<Integer> queue = new MyQueue<>();
        assertEquals(0, queue.size());

        queue.offer(1);
        assertEquals(1, queue.size());

        queue.offer(2);
        assertEquals(2, queue.size());

        queue.poll();
        assertEquals(1, queue.size());
    }
}
