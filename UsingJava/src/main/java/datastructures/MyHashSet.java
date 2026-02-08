package datastructures;

/**
 * 해시 셋(Hash Set) 구현체입니다.
 * 중복된 요소를 허용하지 않으며, 요소의 순서는 보장하지 않습니다.
 * 내부적으로 `MyHashMap`을 사용하여 구현되었습니다.
 *
 * @param <T> 저장할 요소의 타입
 */
public class MyHashSet<T> {

    // 내부적으로 사용할 Map.
    // Set의 요소는 Map의 Key로 저장되고, Value는 더미 객체로 채웁니다.
    private final MyHashMap<T, Object> map;

    // Map의 Value 자리에 넣을 더미 객체
    private static final Object PRESENT = new Object();

    public MyHashSet() {
        this.map = new MyHashMap<>();
    }

    /**
     * 요소를 추가합니다.
     * 이미 존재하는 요소라면 추가되지 않고 유지됩니다.
     * 
     * @param element 추가할 요소
     */
    public void add(T element) {
        map.put(element, PRESENT);
    }

    /**
     * 요소가 포함되어 있는지 확인합니다.
     * 
     * @param element 찾을 요소
     * @return 포함되어 있으면 true, 아니면 false
     */
    public boolean contains(T element) {
        return map.get(element) != null;
    }

    /**
     * 요소를 제거합니다.
     * 
     * @param element 제거할 요소
     */
    public void remove(T element) {
        map.remove(element);
    }

    /**
     * 저장된 요소의 개수를 반환합니다.
     * 
     * @return 요소 개수
     */
    public int size() {
        return map.size();
    }

    /**
     * 셋이 비어있는지 확인합니다.
     * 
     * @return 비어있으면 true, 아니면 false
     */
    public boolean isEmpty() {
        return map.isEmpty();
    }
}
