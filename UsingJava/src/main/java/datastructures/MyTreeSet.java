package datastructures;

/**
 * 트리 셋(Tree Set) 구현체입니다.
 * 중복된 요소를 허용하지 않으며, 요소들이 정렬된 상태로 유지됩니다.
 * 내부적으로 `MyTreeMap`을 사용하여 구현되었습니다.
 *
 * @param <T> 저장할 요소의 타입 (Comparable 구현 필요)
 */
public class MyTreeSet<T extends Comparable<T>> {

    // 내부적으로 사용할 TreeMap.
    // Set의 요소는 Map의 Key로 저장됩니다.
    private final MyTreeMap<T, Object> map;

    private static final Object PRESENT = new Object();

    public MyTreeSet() {
        this.map = new MyTreeMap<>();
    }

    /**
     * 요소를 추가합니다.
     * 요소는 자동으로 정렬된 위치에 저장됩니다.
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
     * 저장된 모든 요소를 정렬된 순서로 반환합니다.
     * 
     * @return 정렬된 요소 리스트
     */
    public MyArrayList<T> toList() {
        // MyTreeMap에 추가한 keys() 메소드를 활용
        return map.keys();
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
