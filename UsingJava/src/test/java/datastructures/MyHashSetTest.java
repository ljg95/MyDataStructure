package datastructures;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MyHashSetTest {

    @Test
    void testAddAndContains() {
        MyHashSet<String> set = new MyHashSet<>();
        set.add("A");
        set.add("B");

        assertTrue(set.contains("A"));
        assertTrue(set.contains("B"));
        assertFalse(set.contains("C"));
    }

    @Test
    void testDuplicateUsingMap() {
        // 중복된 요소 추가 시 동작 확인 (Set의 특성)
        MyHashSet<String> set = new MyHashSet<>();
        set.add("A");
        set.add("A"); // 중복 추가

        assertEquals(1, set.size()); // 크기가 늘어나면 안 됨
        assertTrue(set.contains("A"));
    }

    @Test
    void testRemove() {
        MyHashSet<Integer> set = new MyHashSet<>();
        set.add(1);
        set.add(2);

        set.remove(1);
        assertFalse(set.contains(1));
        assertTrue(set.contains(2));
        assertEquals(1, set.size());
    }
}
