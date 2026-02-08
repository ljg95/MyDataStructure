package datastructures;

/**
 * 트리 맵(Tree Map) 구현체입니다.
 * 이진 탐색 트리(Binary Search Tree) 구조를 사용하여 Key를 기준으로 정렬된 상태를 유지합니다.
 * 내부적으로 MyRedBlackTree를 사용하여 성능을 최적화했습니다.
 *
 * @param <K> Key의 타입 (Comparable 구현 필요)
 * @param <V> Value의 타입
 */
public class MyTreeMap<K extends Comparable<K>, V> {

    // 내부적으로 Red-Black Tree를 사용하여 데이터 저장
    private final MyRedBlackTree<K, V> tree;

    public MyTreeMap() {
        this.tree = new MyRedBlackTree<>();
    }

    /**
     * Key에 해당하는 Value를 저장합니다.
     * 
     * @param key   저장할 키
     * @param value 저장할 값
     */
    public void put(K key, V value) {
        tree.put(key, value);
    }

    /**
     * Key에 해당하는 Value를 반환합니다.
     * 
     * @param key 찾을 키
     * @return 키에 해당하는 값, 없으면 null
     */
    public V get(K key) {
        return tree.get(key);
    }

    /**
     * Key에 해당하는 엔트리를 삭제합니다.
     * 
     * @param key 삭제할 키
     */
    public void remove(K key) {
        tree.remove(key);
    }

    /**
     * 맵에 저장된 엔트리의 개수를 반환합니다.
     * 
     * @return 저장된 개수
     */
    public int size() {
        return tree.size();
    }

    /**
     * 맵이 비어있는지 확인합니다.
     * 
     * @return 비어있으면 true, 아니면 false
     */
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    /**
     * 저장된 모든 키를 정렬된 순서(In-Order Traversal)로 반환합니다.
     * TreeSet 구현 및 테스트에 사용됩니다.
     * 
     * @return 정렬된 키 리스트
     */
    public MyArrayList<K> keys() {
        return tree.keys();
    }
}
