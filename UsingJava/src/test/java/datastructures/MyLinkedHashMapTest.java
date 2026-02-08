package datastructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyLinkedHashMapTest {

    @Test
    void testPutAndGet() {
        // 기본 저장 및 조회 테스트
        MyLinkedHashMap<String, Integer> map = new MyLinkedHashMap<>();
        map.put("A", 1);
        map.put("B", 2);

        assertEquals(1, map.get("A"));
        assertEquals(2, map.get("B"));
        assertNull(map.get("C"));
    }

    @Test
    void testInsertionOrder() {
        // 입력 순서가 유지되는지 테스트
        MyLinkedHashMap<String, String> map = new MyLinkedHashMap<>();
        map.put("First", "1st");
        map.put("Second", "2nd");
        map.put("Third", "3rd");

        MyArrayList<String> keys = map.keys();
        assertEquals(3, keys.size());
        assertEquals("First", keys.get(0));
        assertEquals("Second", keys.get(1));
        assertEquals("Third", keys.get(2));
    }

    @Test
    void testUpdateMaintainsOrder() {
        // 값을 업데이트해도 순서가 바뀌지 않는지 테스트
        MyLinkedHashMap<String, String> map = new MyLinkedHashMap<>();
        map.put("A", "Original");
        map.put("B", "Original");

        map.put("A", "Updated"); // A 업데이트

        MyArrayList<String> keys = map.keys();
        // 순서는 여전히 A -> B 여야 함
        assertEquals("A", keys.get(0));
        assertEquals("B", keys.get(1));
        assertEquals("Updated", map.get("A"));
    }

    @Test
    void testRemove() {
        // 삭제 후 순서 유지 테스트
        MyLinkedHashMap<String, String> map = new MyLinkedHashMap<>();
        map.put("One", "1");
        map.put("Two", "2");
        map.put("Three", "3");

        map.remove("Two"); // 중간 항목 삭제

        MyArrayList<String> keys = map.keys();
        assertEquals(2, keys.size());
        assertEquals("One", keys.get(0));
        assertEquals("Three", keys.get(1)); // Two가 빠지고 One -> Three 연결
    }
}
