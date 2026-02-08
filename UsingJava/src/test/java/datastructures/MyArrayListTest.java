package datastructures;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {

    @Test
    void testAddAndGet() {
        // 데이터 추가 및 조회 테스트
        MyArrayList<String> list = new MyArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
        assertEquals("C", list.get(2));
        assertEquals(3, list.size());
    }

    @Test
    void testResize() {
        // 용량 자동 증가(Resize) 테스트
        MyArrayList<Integer> list = new MyArrayList<>();
        // 초기 용량(10)보다 많이 추가
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }

        assertEquals(20, list.size());
        assertEquals(19, list.get(19)); // 데이터가 잘 유지되는지 확인
    }

    @Test
    void testRemove() {
        // 삭제 및 인덱스 이동 테스트
        MyArrayList<String> list = new MyArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        list.remove(1); // "B" 삭제

        assertEquals(2, list.size());
        assertEquals("A", list.get(0));
        assertEquals("C", list.get(1)); // "C"가 앞으로 당겨져야 함
    }

    @Test
    void testIndexOutOfBounds() {
        // 유효하지 않은 인덱스 접근 시 예외 발생 테스트
        MyArrayList<String> list = new MyArrayList<>();
        list.add("A");

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(1));
    }

    @Test
    void testContains() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("Hello");

        assertTrue(list.contains("Hello"));
        assertFalse(list.contains("World"));
    }
}
