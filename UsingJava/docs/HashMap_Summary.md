# Hash Map (해시 맵)

## 개념
**Hash Map**은 **Key-Value(키-값)** 쌍으로 데이터를 저장하는 자료구조입니다.
Key를 통해 Value에 매우 빠르게 접근할 수 있으며(평균 **O(1)**), Key의 중복을 허용하지 않습니다.

### 핵심 원리
1.  **Hashing**: Key를 **Hash Function(해시 함수)**에 통과시켜 고유한 정수값(Hash Code)을 얻습니다.
2.  **Indexing**: Hash Code를 배열의 크기(Capacity)로 나눈 나머지(`%`)를 인덱스로 사용하여 배열(Bucket)에 저장합니다.

## 구조 (Separate Chaining 방식)
이번 `MyHashMap` 구현은 **Separate Chaining (체이닝)** 방식을 사용하여 해시 충돌(Collision)을 해결했습니다.

### 해시 충돌 (Hash Collision)
서로 다른 Key가 같은 해시 인덱스를 배정받는 현상입니다.
(예: `Key A`와 `Key B`의 해시 코드가 우연히 같거나, 배열 크기로 나눈 나머지가 같은 경우)

### 해결 방법: Separate Chaining
각 배열의 요소(Bucket)가 **연결 리스트(Linked List)**의 헤드(Head) 역할을 합니다.
1.  데이터를 저장할 때, 해당 인덱스에 이미 데이터가 있다면 **연결 리스트의 노드**로 추가합니다.
2.  데이터를 찾을 때, 해당 인덱스로 이동한 뒤 **연결 리스트를 순회**하며 Key가 일치하는지 확인합니다.

```java
// 내부 Entry 클래스 (Linked List Node)
private static class Entry<K, V> {
    final K key;
    V value;
    Entry<K, V> next; // 다음 노드를 가리킴 (충돌 시 연결)
}
```

## 주요 연산 및 시간 복잡도

| 연산 | 설명 | 시간 복잡도 (평균) | 시간 복잡도 (최악) |
| :--- | :--- | :--- | :--- |
| **Put (저장)** | Key의 해시 인덱스를 계산하여 Bucket에 추가/업데이트합니다. | **O(1)** | O(n) (모든 키가 충돌할 때) |
| **Get (조회)** | Key의 해시 인덱스를 찾아, Bucket 내 리스트를 탐색하여 값을 반환합니다. | **O(1)** | O(n) |
| **Remove (삭제)** | Key의 해시 인덱스를 찾아, Bucket 내 리스트에서 해당 노드를 제거합니다. | **O(1)** | O(n) |

> [!NOTE]
> 최악의 경우(O(n))는 해시 함수가 매우 좋지 않아 모든 데이터가 하나의 Bucket에 몰리는 경우입니다.
> 자바의 실제 `HashMap`은 데이터가 많아지면 연결 리스트 대신 **Red-Black Tree**로 변환하여 최악의 경우에도 **O(log n)** 성능을 보장합니다.

---
> [!TIP]
> **해시 함수 (Hash Function)**
> 자바의 모든 객체는 `hashCode()` 메소드를 가지고 있습니다.
> `MyHashMap`에서는 `Math.abs(key.hashCode()) % buckets.length` 공식을 사용하여 인덱스를 결정합니다.
