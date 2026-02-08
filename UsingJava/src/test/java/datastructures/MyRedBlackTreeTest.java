package datastructures;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MyRedBlackTreeTest {

    @Test
    void testPutAndGet() {
        MyRedBlackTree<Integer, String> rbTree = new MyRedBlackTree<>();
        rbTree.put(10, "Ten");
        rbTree.put(5, "Five");
        rbTree.put(15, "Fifteen");

        assertEquals("Ten", rbTree.get(10));
        assertEquals("Five", rbTree.get(5));
        assertEquals("Fifteen", rbTree.get(15));
        assertNull(rbTree.get(99));
    }

    @Test
    void testUpdate() {
        MyRedBlackTree<String, Integer> rbTree = new MyRedBlackTree<>();
        rbTree.put("Key", 100);
        assertEquals(100, rbTree.get("Key"));

        rbTree.put("Key", 200); // Update
        assertEquals(200, rbTree.get("Key"));
        assertEquals(1, rbTree.size());
    }

    @Test
    void testLargeInsertionAndOrder() {
        // 많은 데이터를 넣어도 트리가 정상적으로 동작하고 정렬을 유지하는지 테스트
        MyRedBlackTree<Integer, Integer> rbTree = new MyRedBlackTree<>();
        int count = 1000;

        for (int i = 0; i < count; i++) {
            rbTree.put(i, i);
        }

        assertEquals(count, rbTree.size());

        // 순서 확인 (0 ~ 999)
        MyArrayList<Integer> keys = rbTree.keys();
        assertEquals(count, keys.size());

        for (int i = 0; i < count; i++) {
            assertEquals(i, keys.get(i));
            assertEquals(i, rbTree.get(i));
        }
    }

    @Test
    void testRandomInsertion() {
        // 무작위 순서로 입력해도 정렬되는지 테스트
        MyRedBlackTree<Integer, Integer> rbTree = new MyRedBlackTree<>();
        int[] inputs = { 50, 20, 80, 10, 30, 70, 90, 5, 25, 60 };

        for (int val : inputs) {
            rbTree.put(val, val);
        }

        assertEquals(inputs.length, rbTree.size());

        MyArrayList<Integer> keys = rbTree.keys();
        // 정렬 확인
        for (int i = 0; i < keys.size() - 1; i++) {
            assertTrue(keys.get(i) < keys.get(i + 1));
        }
    }
}
