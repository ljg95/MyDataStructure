package datastructures;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MyHashMapTest {

    @Test
    void testPutAndGet() {
        // 데이터 저장(put) 및 조회(get) 테스트
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("A", 1);
        map.put("B", 2);

        assertEquals(1, map.get("A"));
        assertEquals(2, map.get("B"));
        assertNull(map.get("C")); // 없는 키 조회 시 null 반환
    }

    @Test
    void testUpdate() {
        // 이미 존재하는 키에 값을 저장하면 업데이트되는지 테스트
        MyHashMap<String, String> map = new MyHashMap<>();
        map.put("Key", "Value1");
        assertEquals("Value1", map.get("Key"));

        map.put("Key", "Value2"); // 값 업데이트
        assertEquals("Value2", map.get("Key"));
        assertEquals(1, map.size()); // 크기는 변하지 않아야 함
    }

    @Test
    void testRemove() {
        // 데이터 삭제(remove) 테스트
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("A", 10);
        map.put("B", 20);

        map.remove("A");
        assertNull(map.get("A")); // 삭제된 키는 조회되지 않아야 함
        assertEquals(20, map.get("B")); // 다른 키는 유지되어야 함
        assertEquals(1, map.size()); // 크기가 줄어야 함
    }

    @Test
    void testCollision() {
        // 해시 충돌 발생 시 처리 테스트 (Separate Chaining 동작 확인)
        // 충돌을 강제로 유발하기는 어렵으므로, 서로 다른 키를 여러 개 넣어 잘 동작하는지 확인
        MyHashMap<Integer, String> map = new MyHashMap<>();
        int capacity = 16; // 기본 용량

        // 인덱스가 겹칠 가능성이 높은 키들을 추가 (예: 1, 17, 33...)
        map.put(1, "One");
        map.put(1 + capacity, "One+Cap");
        map.put(1 + capacity * 2, "One+Cap*2");

        assertEquals("One", map.get(1));
        assertEquals("One+Cap", map.get(1 + capacity));
        assertEquals("One+Cap*2", map.get(1 + capacity * 2));
        assertEquals(3, map.size());
    }
}
