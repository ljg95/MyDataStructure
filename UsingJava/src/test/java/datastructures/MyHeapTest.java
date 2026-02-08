package datastructures;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;

class MyHeapTest {

    @Test
    void testMinHeap() {
        // 최소 힙(Min Heap) 테스트 (기본 생성자)
        MyHeap<Integer> heap = new MyHeap<>();
        heap.insert(3);
        heap.insert(1);
        heap.insert(5);
        heap.insert(2);

        // 작은 값부터 순서대로 나와야 함 (1 -> 2 -> 3 -> 5)
        assertEquals(1, heap.poll());
        assertEquals(2, heap.poll());
        assertEquals(3, heap.poll());
        assertEquals(5, heap.poll());
    }

    @Test
    void testMaxHeap() {
        // 최대 힙(Max Heap) 테스트 (생성자 인자 true)
        MyHeap<Integer> heap = new MyHeap<>(true);
        heap.insert(3);
        heap.insert(1);
        heap.insert(5);
        heap.insert(2);

        // 큰 값부터 순서대로 나와야 함 (5 -> 3 -> 2 -> 1)
        assertEquals(5, heap.poll());
        assertEquals(3, heap.poll());
        assertEquals(2, heap.poll());
        assertEquals(1, heap.poll());
    }

    @Test
    void testPeek() {
        // 조회(Peek) 테스트
        MyHeap<Integer> minHeap = new MyHeap<>();
        minHeap.insert(10);
        minHeap.insert(5);
        assertEquals(5, minHeap.peek()); // Min Heap: 5

        MyHeap<Integer> maxHeap = new MyHeap<>(true);
        maxHeap.insert(10);
        maxHeap.insert(5);
        assertEquals(10, maxHeap.peek()); // Max Heap: 10
    }

    @Test
    void testIsEmpty() {
        MyHeap<String> heap = new MyHeap<>();
        assertTrue(heap.isEmpty());

        heap.insert("A");
        assertFalse(heap.isEmpty());
    }

    @Test
    void testPollEmptyHeap() {
        MyHeap<Integer> heap = new MyHeap<>();
        assertThrows(NoSuchElementException.class, heap::poll);
    }
}
