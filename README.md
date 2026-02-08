# 자바 핵심 자료구조 요약

자바 컬렉션 프레임워크(Java Collections Framework)에서 자주 사용되는 핵심 자료구조들을 정리했습니다.

## 1. List (리스트) - 순서가 있고 중복을 허용함
- **ArrayList**:
    - **특징**: 내부적으로 배열을 사용. 인덱스로 접근이 매우 빠름(O(1)).
    - **단점**: 중간에 데이터를 삽입/삭제할 때 느림(O(n), 데이터를 밀거나 당겨야 함).
    - **용도**: 데이터 조회(검색)가 빈번할 때.
- **LinkedList**:
    - **특징**: 노드 간의 연결로 구성. 중간 삽입/삭제가 빠름(O(1), 위치만 알면).
    - **단점**: 인덱스로 접근할 때 느림(O(n), 처음부터 순회).
    - **용도**: 데이터 추가/삭제가 빈번할 때.

## 2. Set (셋) - 순서가 없고 중복을 허용하지 않음
- **HashSet**:
    - **특징**: 해시 알고리즘 사용. 데이터 추가/삭제/검색이 매우 빠름(O(1)). 순서 보장 안 됨.
    - **용도**: 중복 제거, 빠른 데이터 존재 여부 확인.
- **TreeSet**:
    - **특징**: 이진 탐색 트리(Red-Black Tree) 사용. 데이터가 정렬된 상태로 저장됨.
    - **단점**: HashSet보다 조금 느림(O(log n)).
    - **용도**: 정렬된 상태로 중복 없는 데이터를 유지해야 할 때.

## 3. Map (맵) - Key-Value 쌍으로 저장, Key는 중복 불가
- **HashMap**:
    - **특징**: 해시 알고리즘 사용. Key로 Value에 접근하는 속도가 매우 빠름(O(1)). 순서 보장 안 됨.
    - **용도**: 캐싱, Key를 이용한 빠른 데이터 검색.
- **TreeMap**:
    - **특징**: Key를 기준으로 정렬되어 저장됨.
    - **용도**: Key 순서대로 데이터 처리가 필요할 때 (예: 범위 검색).
- **LinkedHashMap**:
    - **특징**: 입력된 순서를 기억함.
    - **용도**: LRU 캐시 구현 등 순서가 중요할 때.

## 4. Queue (큐) - 들어온 순서대로 처리 (FIFO)
- **ArrayDeque**:
    - **특징**: 양쪽 끝에서 추가/삭제가 가능한 큐(Deque). Stack과 Queue의 기능을 모두 가짐.
    - **용도**: 일반적인 큐나 스택보다 성능이 우수하여 자주 권장됨.

## 5. Heap (힙) - 우선순위가 높은 데이터가 먼저 나감
- **PriorityQueue (우선순위 큐)**:
    - **특징**: 내부적으로 힙(Heap) 자료구조를 사용.
    - **Min Heap (최소 힙)**: 기본값. 가장 작은 값이 루트(Root)에 위치.
    - **Max Heap (최대 힙)**: Comparator를 사용하여 구현 가능 (`Collections.reverseOrder()`). 가장 큰 값이 루트에 위치.
    - **용도**: 작업 스케줄링, 최단 경로 알고리즘(Dijkstra).

## 6. Project Implementations (직접 구현한 자료구조)

이 프로젝트(`UsingJava`)에서 직접 구현한 자료구조들의 특징입니다.

| 자료구조 | 클래스명 | 내부 구현 | 비고 |
| :--- | :--- | :--- | :--- |
| **List** | `MyArrayList` | 동적 배열 (Dynamic Array) | `set()` 추가됨. |
| **Stack** | `MyStack` | `MyArrayList` | `java.util.Stack`과 호환. |
| **Queue** | `MyQueue` | 단순 연결 리스트 (Linked List) | |
| **Heap** | `MyHeap` | `MyArrayList` | Min/Max Heap 지원 (Generic). |
| **Map** | `MyHashMap` | 해시 테이블 (Separate Chaining) | |
| **Map** | `MyLinkedHashMap` | 해시 테이블 + 이중 연결 리스트 | 입력 순서 보장. `keys()`는 `MyArrayList` 반환. |
| **Map** | `MyTreeMap` | **Red-Black Tree** | 정렬 상태 유지. $O(\log n)$ 성능. |
| **Set** | `MyHashSet` | `MyHashMap` | |
| **Set** | `MyTreeSet` | `MyTreeMap` (`MyRedBlackTree`) | 정렬 상태 유지. `toList()` 반환. |

---
> [!TIP]
> **어떤 것을 써야 할까?**
>
> 1.  **순서가 중요하고 중복 허용?** -> `List` (`ArrayList`를 기본으로 사용)
> 2.  **중복을 제거해야 함?** -> `Set` (`HashSet`을 기본으로 사용)
> 3.  **Key-Value 매핑이 필요?** -> `Map` (`HashMap`을 기본으로 사용)
> 4.  **선입선출(FIFO) 작업?** -> `Queue` (`LinkedList` 또는 `ArrayDeque`)
> 5.  **LIFO 작업(스택)?** -> `Deque` (`ArrayDeque`)
