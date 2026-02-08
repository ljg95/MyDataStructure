package datastructures;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MyTreeMapTest {

    @Test
    void testPutAndGet() {
        // 데이터 저장(put) 및 조회(get) 테스트
        MyTreeMap<Integer, String> map = new MyTreeMap<>();
        map.put(3, "Three");
        map.put(1, "One");
        map.put(5, "Five");

        assertEquals("One", map.get(1));
        assertEquals("Three", map.get(3));
        assertEquals("Five", map.get(5));
        assertNull(map.get(99)); // 없는 키 조회
    }

    @Test
    void testUpdate() {
        // 이미 존재하는 키에 값을 저장하면 업데이트되는지 테스트
        MyTreeMap<String, Integer> map = new MyTreeMap<>();
        map.put("Key", 100);
        assertEquals(100, map.get("Key"));

        map.put("Key", 200); // 값 업데이트
        assertEquals(200, map.get("Key"));
        assertEquals(1, map.size()); // 크기는 유지
    }

    @Test
    void testRemoveLeaf() {
        // 리프 노드(자식이 없는 노드) 삭제 테스트
        MyTreeMap<Integer, String> map = new MyTreeMap<>();
        map.put(5, "Root");
        map.put(3, "Left");
        map.put(7, "Right");

        map.remove(3); // 리프 노드 삭제
        assertNull(map.get(3));
        assertEquals("Root", map.get(5));
        assertEquals("Right", map.get(7));
        assertEquals(2, map.size());
    }

    @Test
    void testRemoveOneChild() {
        // 자식이 하나인 노드 삭제 테스트
        MyTreeMap<Integer, String> map = new MyTreeMap<>();
        map.put(5, "Root");
        map.put(3, "Left");
        map.put(2, "Left-Left"); // 3의 자식

        map.remove(3); // 자식이 하나(2)인 노드 삭제
        assertNull(map.get(3));
        assertEquals("Left-Left", map.get(2)); // 자식이 위로 올라왔는지 확인
        assertEquals(2, map.size());
    }

    @Test
    void testRemoveTwoChildren() {
        // 자식이 둘인 노드 삭제 테스트
        MyTreeMap<Integer, String> map = new MyTreeMap<>();
        map.put(5, "Root");
        map.put(3, "Left");
        map.put(1, "Left-Left");
        map.put(4, "Left-Right");

        // 3을 삭제하면, 오른쪽 자식 중 가장 작은 값(4)이 올라와야 함 (BST 속성 유지)
        map.remove(3);
        assertNull(map.get(3));
        assertEquals("Left-Left", map.get(1));
        assertEquals("Left-Right", map.get(4));
        assertEquals(3, map.size());
    }

    @Test
    void testSortedOrder() {
        // 데이터가 정렬된 상태로 저장되는지 확인 (BST 구조상)
        // 여기서는 In-order 순회 대신, 작은 키부터 넣었을 때 구조가 유지되는지로 간접 확인
        MyTreeMap<Integer, String> map = new MyTreeMap<>();
        map.put(5, "Five");
        map.put(1, "One");
        map.put(3, "Three");
        map.put(7, "Seven");

        // get으로 모든 키가 잘 찾아지는지 확인 (구조가 깨졌다면 못 찾을 수 있음)
        assertEquals("One", map.get(1));
        assertEquals("Three", map.get(3));
        assertEquals("Five", map.get(5));
        assertEquals("Seven", map.get(7));
    }
}
