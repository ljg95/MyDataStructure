package datastructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyTreeSetTest {

    @Test
    void testAddAndContains() {
        MyTreeSet<Integer> set = new MyTreeSet<>();
        set.add(10);
        set.add(5);
        set.add(15);

        assertTrue(set.contains(10));
        assertTrue(set.contains(5));
        assertTrue(set.contains(15));
        assertFalse(set.contains(20));
    }

    @Test
    void testDuplicateUsingMap() {
        // 중복 추가 방지 테스트
        MyTreeSet<String> set = new MyTreeSet<>();
        set.add("Apple");
        set.add("Banana");
        set.add("Apple"); // 중복

        assertEquals(2, set.size());
        assertTrue(set.contains("Apple"));
    }

    @Test
    void testSortedOrder() {
        // 정렬된 순서 유지 테스트
        MyTreeSet<Integer> set = new MyTreeSet<>();
        set.add(5);
        set.add(1);
        set.add(10);
        set.add(3);

        MyArrayList<Integer> list = set.toList();
        assertEquals(4, list.size());
        assertEquals(1, list.get(0));
        assertEquals(3, list.get(1));
        assertEquals(5, list.get(2));
        assertEquals(10, list.get(3));
    }

    @Test
    void testRemove() {
        MyTreeSet<String> set = new MyTreeSet<>();
        set.add("A");
        set.add("B");
        set.add("C");

        set.remove("B");
        assertFalse(set.contains("B"));
        assertTrue(set.contains("A"));
        assertTrue(set.contains("C"));
        assertEquals(2, set.size());
    }
}
