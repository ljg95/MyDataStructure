# Hash Set (해시 셋)

## 개념
**Hash Set**은 **중복된 요소를 허용하지 않는** 자료구조입니다.
요소의 저장 순서는 보장하지 않으며, 특정 요소가 집합에 포함되어 있는지 빠르게 확인(`contains`)하는 데 유용합니다.

## 구조 (HashMap 활용)
대부분의 Hash Set 구현체(Java 포함)는 내부적으로 **Hash Map**을 사용하여 구현됩니다.

1.  **Set의 요소** -> **Map의 Key**로 저장 (Key는 중복 불가하므로 Set의 속성 만족)
2.  **Map의 Value** -> 의미 없는 **더미 객체(Dummy Object)**를 저장

```java
// MyHashSet 내부 구현 예시
private final MyHashMap<T, Object> map = new MyHashMap<>();
private static final Object PRESENT = new Object(); // 더미 값

public void add(T element) {
    map.put(element, PRESENT);
}
```

## 주요 연산 및 시간 복잡도

| 연산 | 설명 | 시간 복잡도 (평균) |
| :--- | :--- | :--- |
| **Add (추가)** | Map의 `put` 연산을 수행합니다. | **O(1)** |
| **Contains (확인)** | Map의 `containsKey` (또는 get) 연산을 수행합니다. | **O(1)** |
| **Remove (삭제)** | Map의 `remove` 연산을 수행합니다. | **O(1)** |

> [!NOTE]
> **순서 보장이 필요하다면?**
> `LinkedHashSet`을 사용하면 입력된 순서대로 요소를 유지할 수 있습니다. (내부적으로 `LinkedHashMap` 사용)
