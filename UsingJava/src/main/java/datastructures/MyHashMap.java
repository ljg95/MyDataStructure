package datastructures;

/**
 * 해시 맵(Hash Map) 구현체입니다.
 * Key-Value 쌍으로 데이터를 저장하며, Key의 중복은 허용하지 않습니다.
 * 해시 충돌 해결을 위해 Separate Chaining 방식(연결 리스트 사용)을 사용합니다.
 *
 * @param <K> Key의 타입
 * @param <V> Value의 타입
 */
public class MyHashMap<K, V> {

    /**
     * 해시 맵의 각 버킷에 저장될 노드(엔트리) 클래스입니다.
     * 연결 리스트의 노드 역할을 합니다.
     */
    private static class Entry<K, V> {
        final K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private static final int DEFAULT_CAPACITY = 16;
    private Entry<K, V>[] buckets;
    private int size;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        // 제네릭 배열 생성이 불가능하므로 Object 배열 생성 후 형변환
        this.buckets = (Entry<K, V>[]) new Entry[DEFAULT_CAPACITY];
        this.size = 0;
    }

    /**
     * Key에 해당하는 Value를 저장합니다.
     * 이미 Key가 존재하면 Value를 덮어씁니다.
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

        // 해당 인덱스의 연결 리스트를 순회하며 Key가 이미 존재하는지 확인
        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value; // 이미 존재하면 값 업데이트
                return;
            }
            current = current.next;
        }

        // Key가 존재하지 않으면 새 노드를 리스트의 맨 앞에 추가 (Collision 발생 시 chaining)
        Entry<K, V> newEntry = new Entry<>(key, value, buckets[index]);
        buckets[index] = newEntry;
        size++;
    }

    /**
     * Key에 해당하는 Value를 반환합니다.
     * 
     * @param key 찾을 키
     * @return 키에 해당하는 값, 없으면 null
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

        return null; // 키를 찾지 못함
    }

    /**
     * Key에 해당하는 엔트리를 삭제합니다.
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
                if (prev == null) {
                    // 삭제할 노드가 리스트의 첫 번째 노드인 경우
                    buckets[index] = current.next;
                } else {
                    // 중간이나 끝에 있는 노드인 경우
                    prev.next = current.next;
                }
                size--;
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    /**
     * 맵에 저장된 엔트리의 개수를 반환합니다.
     * 
     * @return 저장된 개수
     */
    public int size() {
        return size;
    }

    /**
     * 맵이 비어있는지 확인합니다.
     * 
     * @return 비어있으면 true, 아니면 false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Key의 해시 코드를 이용하여 배열 인덱스를 계산합니다.
     */
    private int getIndex(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }
}
