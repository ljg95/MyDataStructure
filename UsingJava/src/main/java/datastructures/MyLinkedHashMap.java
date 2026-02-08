package datastructures;

/**
 * 연결 해시 맵(Linked Hash Map) 구현체입니다.
 * HashMap의 기능에 입력된 순서(Insertion Order)를 유지하는 기능을 추가했습니다.
 * 내부적으로 해시 테이블(배열 + 체이닝)과 이중 연결 리스트(Doubly Linked List)를 함께 사용합니다.
 *
 * @param <K> Key의 타입
 * @param <V> Value의 타입
 */
public class MyLinkedHashMap<K, V> {

    /**
     * 해시 맵의 엔트리이자, 이중 연결 리스트의 노드입니다.
     */
    private static class Entry<K, V> {
        final K key;
        V value;
        Entry<K, V> next; // 해시 충돌 해결용 (HashMap의 next)

        // 순서 유지를 위한 이중 연결 리스트 포인터
        Entry<K, V> before;
        Entry<K, V> after;

        Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private static final int DEFAULT_CAPACITY = 16;
    private Entry<K, V>[] buckets;
    private int size;

    // 순서 유지를 위한 헤드(가장 오래된 항목)와 테일(가장 최근 항목)
    private Entry<K, V> head;
    private Entry<K, V> tail;

    @SuppressWarnings("unchecked")
    public MyLinkedHashMap() {
        this.buckets = (Entry<K, V>[]) new Entry[DEFAULT_CAPACITY];
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    /**
     * Key에 해당하는 Value를 저장합니다.
     * 새로운 키라면 리스트의 맨 뒤(tail)에 추가되어 순서를 유지합니다.
     * 
     * @param key   저장할 키
     * @param value 저장할 값
     */
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        int index = getIndex(key);
        Entry<K, V> current = buckets[index];

        // 1. 이미 존재하는 키인지 확인 (Update)
        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value;
                return; // 값만 업데이트하고 순서는 변경하지 않음
            }
            current = current.next;
        }

        // 2. 새로운 키라면 추가 (Insert)
        Entry<K, V> newEntry = new Entry<>(key, value, buckets[index]);
        buckets[index] = newEntry;

        // 이중 연결 리스트의 마지막(Tail)에 연결
        linkLast(newEntry);

        size++;
    }

    /**
     * Key에 해당하는 Value를 반환합니다.
     * 
     * @param key 찾을 키
     * @return 키에 해당하는 값
     */
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        int index = getIndex(key);
        Entry<K, V> current = buckets[index];

        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }

        return null;
    }

    /**
     * Key에 해당하는 엔트리를 삭제합니다.
     * 해시 테이블과 이중 연결 리스트 모두에서 제거해야 합니다.
     * 
     * @param key 삭제할 키
     */
    public void remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        int index = getIndex(key);
        Entry<K, V> current = buckets[index];
        Entry<K, V> prev = null;

        while (current != null) {
            if (current.key.equals(key)) {
                // 해시 테이블(버킷)에서 제거
                if (prev == null) {
                    buckets[index] = current.next;
                } else {
                    prev.next = current.next;
                }

                // 이중 연결 리스트에서 제거 (순서 정보 끊기)
                unlink(current);

                size--;
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    /**
     * 입력된 순서대로 모든 키를 반환합니다.
     * 
     * @return 키 리스트
     */
    public MyArrayList<K> keys() {
        MyArrayList<K> keyList = new MyArrayList<>();
        Entry<K, V> current = head;
        while (current != null) {
            keyList.add(current.key);
            current = current.after;
        }
        return keyList;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // --- 내부 헬퍼 메소드 ---

    private int getIndex(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    /**
     * 새 엔트리를 이중 연결 리스트의 tail(마지막)로 연결합니다.
     */
    private void linkLast(Entry<K, V> entry) {
        Entry<K, V> last = tail;
        tail = entry;
        if (last == null) {
            // 리스트가 비어있던 경우
            head = entry;
        } else {
            // 기존 tail 뒤에 연결
            last.after = entry;
            entry.before = last;
        }
    }

    /**
     * 엔트리를 이중 연결 리스트에서 끊어냅니다 (삭제).
     */
    private void unlink(Entry<K, V> entry) {
        Entry<K, V> b = entry.before;
        Entry<K, V> a = entry.after;

        // 이전 노드 처리
        if (b == null) {
            head = a; // 삭제된 노드가 head였음
        } else {
            b.after = a;
            entry.before = null;
        }

        // 다음 노드 처리
        if (a == null) {
            tail = b; // 삭제된 노드가 tail이었음
        } else {
            a.before = b;
            entry.after = null;
        }
    }
}
