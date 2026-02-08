# Linked Hash Map (연결 해시 맵)

## 개념
**Linked Hash Map**은 **Hash Map**의 장점(빠른 검색)에 **입력된 순서(Insertion Order)**를 기억하는 기능을 추가한 자료구조입니다.
일반적인 HashMap은 데이터의 순서를 보장하지 않지만, LinkedHashMap은 데이터가 추가된 순서대로 반복(Iteration)할 수 있습니다.

## 구조 (Hash Table + Doubly Linked List)
내부적으로 **해시 테이블(배열)**과 **이중 연결 리스트(Doubly Linked List)** 두 가지 구조를 동시에 유지합니다.

1.  **해시 테이블**: `Entry` 배열을 사용하여 Key-Value를 저장하고 빠르게 검색(O(1))합니다.
2.  **이중 연결 리스트**: 각 `Entry`가 자신의 **이전(before)**과 **다음(after)** 노드를 가리키며, 입력된 순서대로 연결됩니다.
    -   `head`: 가장 먼저 입력된(가장 오래된) 노드
    -   `tail`: 가장 나중에 입력된(가장 최근의) 노드

```java
// 내부 Entry 클래스
private static class Entry<K, V> {
    K key;
    V value;
    Entry<K, V> next;   // 해시 충돌 해결용 (HashMap의 Chain)
    
    // 순서 유지를 위한 이중 연결 리스트 포인터
    Entry<K, V> before; // 내 바로 앞에 입력된 노드
    Entry<K, V> after;  // 내 바로 뒤에 입력된 노드
}
```

## 주요 연산 및 동작

| 연산 | 해시 테이블 동작 | 연결 리스트 동작 | 시간 복잡도 |
| :--- | :--- | :--- | :--- |
| **Put (저장)** | 해시 인덱스에 추가/업데이트 | 새로운 키라면 `tail` 뒤에 연결하여 순서 유지 | **O(1)** |
| **Get (조회)** | 해시 인덱스로 바로 접근 | 영향 없음 | **O(1)** |
| **Remove (삭제)** | 해시 버킷에서 제거 | 연결 리스트에서 `before`와 `after`를 연결하여 노드 제거 | **O(1)** |

> [!NOTE]
> **순서 유지 방식**
> - **Insertion Order (입력 순서)**: 데이터가 `put`된 순서대로 저장됩니다. (재입력 시 순서가 바뀌지 않음) - `MyLinkedHashMap`의 방식
> - **Keys()**: 입력된 순서대로 정렬된 키 목록을 **`MyArrayList`**로 반환합니다.
> - **Access Order (접근 순서)**: 데이터가 `get`될 때마다 해당 노드를 리스트의 가장 뒤(최신)로 이동시킵니다. (LRU 캐시 구현에 사용됨)

---
> [!TIP]
> **LRU (Least Recently Used) 캐시 구현**
> `LinkedHashMap`은 LRU 캐시를 구현하는 데 매우 적합합니다.
> 용량이 가득 찼을 때 `head` (가장 오래된 항목)를 제거하면 자연스럽게 LRU 알고리즘이 됩니다.
