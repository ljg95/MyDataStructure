# Tree Set (트리 셋)

## 개념
**Tree Set**은 **중복된 요소를 허용하지 않으며**, 요소들이 **정렬된 상태**로 유지되는 자료구조입니다.
이진 탐색 트리(BST) 구조를 기반으로 하므로, 범위 검색이나 정렬된 순서의 순회(Traversal)가 필요할 때 유용합니다.

## 구조 (TreeMap 활용)
`MyHashSet`과 마찬가지로, 내부적으로 **Tree Map**을 사용하여 구현됩니다.

1.  **Set의 요소** -> **Map의 Key**로 저장
2.  **Map의 Value** -> **더미 객체** 저장

```java
// MyTreeSet 내부 구현 예시
private final MyTreeMap<T, Object> map = new MyTreeMap<>();
// ...
public void add(T element) {
    map.put(element, PRESENT); // TreeMap이 알아서 정렬된 위치에 Key를 넣음
}
```

## 주요 연산 및 시간 복잡도

| 연산 | 설명 | 시간 복잡도 (평균) |
| :--- | :--- | :--- |
| **Add (추가)** | **Red-Black Tree**의 삽입 및 재조정 연산을 수행합니다. | **O(log n)** |
| **Contains (확인)** | **Red-Black Tree**의 탐색 연산을 수행합니다. | **O(log n)** |
| **Remove (삭제)** | **Red-Black Tree**의 삭제 및 재조정 연산을 수행합니다. | **O(log n)** |

> [!NOTE]
> **Implementation Note**
> `MyTreeSet`은 내부적으로 `MyTreeMap`을 사용하며, `MyTreeMap`은 **Red-Black Tree**로 구현되어 최악의 경우에도 O(log n) 성능을 보장합니다.
> 또한 `toList()` 메소드는 정렬된 요소들을 **`MyArrayList`**에 담아 반환합니다.

> [!TIP]
> **HashSet vs TreeSet**
> - **HashSet**: 빠름 (O(1)), 순서 없음.
> - **TreeSet**: 조금 느림 (O(log n)), 정렬됨.
